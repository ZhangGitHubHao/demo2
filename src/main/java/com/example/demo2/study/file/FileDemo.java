package com.example.demo2.study.file;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author zhanghao
 * @date 2021-12-02
 */
public class FileDemo {
    /**
     * 从classPath下读取文件
     * @throws IOException
     */
    public void demo() throws IOException {
        ClassPathResource resource = new ClassPathResource("/test.txt");
        if (resource.exists()){
            System.out.println("文件存在");
            InputStream in = resource.getInputStream();
            byte[] bytes = new byte[1024];
            while (in.read(bytes)!=-1){
                String s = new String(bytes);
                System.out.println(s);
            }
            return;
        }
        System.out.println("文件不存在");
    }

}
