package com.bootcamp.secondtrialexcel.service;

import com.bootcamp.secondtrialexcel.domain.Excel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExcelMapper {

    @Select("SELECT * FROM excel where manager = #{manager}")
    Excel findExcelByManager(@Param("manager") String manager);

    @Insert("INSERT INTO excel(workCode, workName, company, manager, rank, category, phone" +
            " VALUES(#{workCode}, #{workName}, #{company}, #{manager}, #{rank}, #{category}, #{phone}")
    int insert(@Param("workCode") String workCode,
               @Param("workName") String workName,
               @Param("company") String company,
               @Param("manager") String manager,
               @Param("rank") String rank,
               @Param("category") String category,
               @Param("phone") String phone);
}
