package com.example.demo2.template.fileupload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件分片传输表单
 *
 * @author zhanghao
 * @date 2021-12-02
 */
@Data
public class MultipartFileForm {

    /**
     * 文件传输任务ID
     */
    private String uuid;

    /**
     * 当前为第几分片
     */
    private int index;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 分片总数
     */
    private int total;

    /**
     * 分块文件传输对象
     */
    private MultipartFile data;
}
