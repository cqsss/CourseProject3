package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.Download_Record;
import com.cqszw.demo.Bean.Paper;
import com.cqszw.demo.Bean.User_Download;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cqs
 * @date 2020/5/27
 */
@Repository
public interface UPDMapper {
    @Select("select * from user_dl where username = #{username} order by downloadtime desc")
    public List<User_Download> getdlbyuser(@Param("username") String username);
    @Select("select * from paper where id in(select paper_id from user_dl where username=#{username})")
    public List<Paper> getpaperbyuser(@Param("username") String username);
    @Select("SELECT type,topic,author,keyword,downloadtime\n" +
            "FROM paper,user_dl\n" +
            "where paper.id=user_dl.paper_id and username=#{username}")
    public List<Download_Record> getDownloadRecords(@Param("username")String username);
    @Insert("insert into user_dl(username,paper_id,downloadtime) values(#{username},#{paper_id},#{downloadtime})")
    public int insertUPD(@Param("username")String username,@Param("paper_id")int paper_id,@Param("downloadtime")String downloadtime);
}
