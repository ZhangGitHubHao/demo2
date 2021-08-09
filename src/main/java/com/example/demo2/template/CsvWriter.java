package com.example.demo2.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * csv文件写入
 *
 * @author 860120014
 * @date 2021-08-09
 */
public class CsvWriter {
    public static final String FILE_PATH = "E:\\CsvDirectory";
    public static final String MINUTE_FILE_NAME = "client_id.csv";
    private void createCSV(List<String> clientList) {
        File csvFile = null;
        BufferedWriter csvWriter = null;

        try {
            csvFile = new File(FILE_PATH + File.separator + MINUTE_FILE_NAME);

            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile, true), "GB2312"), 1024);

            // 写入csv文件头
            csvWriter.write("client_id");
            // 写入文件内容
            for (String client: clientList) {
                csvWriter.newLine();
                csvWriter.write(client);
            }
            csvWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
