package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.Paper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cqs
 * @date 2020/5/28
 */
@Repository
public interface UPUMapper {
    @Select("select * from paper where id in(select paper_id from user_ul where username=#{username})")
    public List<Paper> getpaperbyuser(@Param("username") String username);
    @Insert("insert into user_ul(username,paper_id,uploadtime) values(#{username},#{paper_id},#{uploadtime})")
    public int insertUPU(@Param("username")String username,@Param("paper_id")int paper_id,@Param("uploadtime")String uploadtime);
}
