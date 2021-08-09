package com.example.demo2.dao.mysql.mapper;

import com.example.demo2.dao.mysql.model.City;
import com.example.demo2.dao.mysql.model.CityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CityMapper {
    @SelectProvider(type=CitySqlProvider.class, method="countByExample")
    long countByExample(CityCriteria example);

    @DeleteProvider(type=CitySqlProvider.class, method="deleteByExample")
    int deleteByExample(CityCriteria example);

    @Delete({
        "delete from city",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into city (ID, Name, CountryCode, ",
        "District, Population)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=CHAR}, #{countrycode,jdbcType=CHAR}, ",
        "#{district,jdbcType=CHAR}, #{population,jdbcType=INTEGER})"
    })
    int insert(City record);

    @InsertProvider(type=CitySqlProvider.class, method="insertSelective")
    int insertSelective(City record);

    @SelectProvider(type=CitySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="CountryCode", property="countrycode", jdbcType=JdbcType.CHAR),
        @Result(column="District", property="district", jdbcType=JdbcType.CHAR),
        @Result(column="Population", property="population", jdbcType=JdbcType.INTEGER)
    })
    List<City> selectByExample(CityCriteria example);

    @Select({
        "select",
        "ID, Name, CountryCode, District, Population",
        "from city",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="CountryCode", property="countrycode", jdbcType=JdbcType.CHAR),
        @Result(column="District", property="district", jdbcType=JdbcType.CHAR),
        @Result(column="Population", property="population", jdbcType=JdbcType.INTEGER)
    })
    City selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CitySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") City record, @Param("example") CityCriteria example);

    @UpdateProvider(type=CitySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") City record, @Param("example") CityCriteria example);

    @UpdateProvider(type=CitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(City record);

    @Update({
        "update city",
        "set Name = #{name,jdbcType=CHAR},",
          "CountryCode = #{countrycode,jdbcType=CHAR},",
          "District = #{district,jdbcType=CHAR},",
          "Population = #{population,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(City record);
}