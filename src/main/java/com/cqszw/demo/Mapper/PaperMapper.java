package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.Paper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cqs
 * @date 2020/5/27
 */
@Repository
public interface PaperMapper {
    @Select("select * from paper")
    List<Paper> getAll();
    @Select("select topic from paper where id=#{paper_id}")
    public String gettopicbyid(@Param("paper_id") int paper_id);
    @Select("select num(*) from paper")
    public int getnum();
}
