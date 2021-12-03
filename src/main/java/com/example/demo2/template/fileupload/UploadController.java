package com.example.demo2.template.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhanghao
 * @date 2021-12-02
 */
@RestController
@RequestMapping("/fileUpload")
@Slf4j
public class UploadController {
    /**
     * 临时文件夹
     */
    private static final String FILE_UPLOAD_TEMP_DIR = "E:/portalupload/fileuploaddir";
    /**
     * 最终文件存放文件夹(需提前创建)
     */
    private static final String FILE_UPLOAD_DIR = "E:/portalupload/file";

    @PostMapping("/shardsToUpload")
    @CrossOrigin
    public Map fragmentation(MultipartFileForm multipartFileForm) {
        Map<String, Object> returnResult = new HashMap<>();

        // 分片文件
        MultipartFile file = multipartFileForm.getData();
        // 分片索引从1开始
        int index = multipartFileForm.getIndex();
        // 总分片数
        int total = multipartFileForm.getTotal();
        // 文件名
        String fileName = multipartFileForm.getName();
        // 去除文件格式(后缀)
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        // 分片上传任务ID,将多个请求关联,最终组合成一个文件
        String uuid = multipartFileForm.getUuid();

        File uploadFile = new File(FILE_UPLOAD_TEMP_DIR + "/" + uuid, uuid + name + "_" + index + ".tem");

        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }

        try {
            // 将上传的文件内容传输到临时文件中
            file.transferTo(uploadFile);
            // 添加文件上传的分片记录,如果index从0开始,这里就不减1了
            UploadUtils.addFileChunkRecord(uuid, total, index - 1);
        } catch (IOException e) {
            e.printStackTrace();
            // 502重传一次
            returnResult.put("status", 502);
            return returnResult;
        }

        // 最后一个文件分片上传时需要合并所有文件
        if (index == total) {
            try {
                returnResult = merge(uuid, fileName);
            } catch (InterruptedException e) {
                e.printStackTrace();
                returnResult.put("status", 500);
                return returnResult;
            }
        }

        return returnResult;

    }

    public Map<String, Object> merge(String uuid, String newFileName) throws InterruptedException {
        while (!UploadUtils.isUploaded(uuid)) {
            //表示还有文件没有上传完,先阻塞等待文件上传完毕
            // 也可以不sleep,空转,但如果等待时间过长感觉不好,还是sleep一下吧
            Thread.sleep(100);
        }
        Map<String, Object> retMap = new HashMap<>();
        try {
            File dirFile = new File(FILE_UPLOAD_TEMP_DIR + "/" + uuid);
            if (!dirFile.exists()) {
                throw new RuntimeException("文件不存在！");
            }
            //分片上传的文件已经位于同一个文件夹下，方便寻找和遍历（当文件数大于十的时候需要排序,保证文件顺序）
            List<String> fileNames = Arrays.stream(dirFile.list()).sorted((o1, o2) -> {
                int o1start = o1.lastIndexOf("_") + 1;
                int o1last = o1.lastIndexOf(".");
                int o1value = Integer.valueOf(o1.substring(o1start, o1last));

                int o2start = o2.lastIndexOf("_") + 1;
                int o2last = o2.lastIndexOf(".");
                int o2value = Integer.valueOf(o2.substring(o2start, o2last));
                return o1value - o2value;
            }).collect(Collectors.toList());

            // 创建空的合并文件
            File targetFile = new File(FILE_UPLOAD_DIR, newFileName);
            RandomAccessFile writeFile = new RandomAccessFile(targetFile, "rw");

            int position = 0;
            for (String fileName : fileNames) {
                System.out.println(fileName);
                File sourceFile = new File(FILE_UPLOAD_TEMP_DIR + "/" + uuid, fileName);
                RandomAccessFile readFile = new RandomAccessFile(sourceFile, "rw");
                int chunksize = 1024 * 3;
                byte[] buf = new byte[chunksize];
                writeFile.seek(position);
                int byteCount = 0;
                while ((byteCount = readFile.read(buf)) != -1) {
                    if (byteCount != chunksize) {
                        byte[] tempBytes = new byte[byteCount];
                        System.arraycopy(buf, 0, tempBytes, 0, byteCount);
                        buf = tempBytes;
                    }
                    writeFile.write(buf);
                    position = position + byteCount;
                }
                readFile.close();
            }
            writeFile.close();
            retMap.put("code", "200");
            // 删除文件分片上传记录
            UploadUtils.removeKey(uuid);
            // 删除缓存的临时文件及目录
            deleteDir(dirFile);
        } catch (IOException e) {
            e.printStackTrace();
            retMap.put("code", "500");
        }
        return retMap;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
