package com.example.demo2.controller;

import com.example.demo2.annotation.ElapsedTime;
import com.example.demo2.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhanghao
 * @date 2021-10-12
 */
@RestController
@RequestMapping("demo2")
public class WebController2 {

    @Autowired
    WebService webService;

    @GetMapping("export")
    public String demo(HttpServletRequest request, HttpServletResponse response){

//        List<ExcelEntityDemo> list = new ArrayList<ExcelEntityDemo>();
//        ExportParams params = new ExportParams("大数据测试", "测试");
//        for (int i = 0; i < 50; i++) {
//            ExcelEntityDemo client = new ExcelEntityDemo();
//            client.setBirthday(new Date());
//            client.setClientName("小明" + i);
//            client.setClientPhone("18797" + i);
//            client.setCreateBy("yanss");
//            client.setId("1" + i);
//            client.setRemark("测试" + i);
//            list.add(client);
//        }
//
//        ExcelService excelService = new ExcelServiceImpl();
//        excelService.exportExcel(params,ExcelEntityDemo.class,list,request,response);


        webService.demo1();
        return "ok";
    }
}
