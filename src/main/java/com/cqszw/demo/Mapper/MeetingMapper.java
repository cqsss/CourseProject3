package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.Meeting;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingMapper {
    @Select("select * from meeting where name=#{name}")
    List<Meeting> getbyname(@Param("name") String name);
    @Select("select * from meeting where date=#{date}")
    List<Meeting> getbydate(@Param("date") String date);
    @Select("select * from meeting")
    List<Meeting> getAll();
    @Insert("insert into meeting(name,content,location,date,url) values(#{name},#{content},#{location},#{date},#{url})")
    public  int insertMeeting(Meeting meeting);
    @Select("select * from meeting where name=#{name} and location=#{location} and date=#{date}")
    Meeting getMeeting(@Param("name") String name,@Param("location") String location,@Param("date") String date);
    @Update("update meeting set name=#{name},location=#{location},date=#{date},url=#{url} where name=#{old_name} and location=#{old_location} and date=#{old_date}")
    public int updateMeeting(@Param("name") String name,@Param("location") String location,
                             @Param("date") String date,@Param("url") String url,
                             @Param("old_name") String old_name,@Param("old_location") String old_location,
                             @Param("old_date") String old_date);
    @Delete("delete from meeting where name=#{name} and location=#{location} and date=#{date}")
    public  int deleteMeeting(@Param("name") String name,@Param("location") String location,@Param("date") String date);
}
