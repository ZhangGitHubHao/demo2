package com.example.demo2.service.impl;

import com.example.demo2.annotation.ElapsedTime;
import com.example.demo2.config.PropertiesConfig;
import com.example.demo2.dao.mysql.mapper.CityMapper;
import com.example.demo2.dao.mysql.model.City;
import com.example.demo2.dao.mysql.model.CityCriteria;
import com.example.demo2.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanghao
 * @date 2021-10-20
 */
@Service
public class WebServiceImpl implements WebService {
    @Autowired
    PropertiesConfig propertiesConfig;

    @Autowired
    CityMapper cityMapper;

    @Value(value = "${spring.servlet.multipart.max-file-size}")
    String name;

    @Async
    @Override
    public void demo1() {
        System.out.println(propertiesConfig.getPort());
        CityCriteria cityCriteria = new CityCriteria();
        cityCriteria.createCriteria().andCountrycodeEqualTo("AFG");
        List<City> res = cityMapper.selectByExample(cityCriteria);
        res.forEach(System.out::println);
    }
}
