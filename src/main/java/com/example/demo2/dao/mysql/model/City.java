package com.example.demo2.dao.mysql.model;

import java.io.Serializable;

/**
 * 城市
 * 
 * @author 860120014
 * @date 2021-07-22
 */
public class City implements Serializable {
    /**
     * 对应字段：ID
     * 字段含义：自增ID
     */
    private Integer id;

    /**
     * 对应字段：Name
     * 字段含义：城市名
     */
    private String name;

    /**
     * 对应字段：CountryCode
     * 字段含义：国家代码
     */
    private String countrycode;

    private String district;

    private Integer population;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", countrycode=").append(countrycode);
        sb.append(", district=").append(district);
        sb.append(", population=").append(population);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}