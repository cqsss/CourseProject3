package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User getbyid(@Param("id") int id);
    @Select("select * from user where username=#{username}")
    User getbyusername(@Param("username") String username);
    @Select("select * from user")
    List<User> getAll();
//    @Insert("insert into meeting(name,location,date,url) values(#{name},#{location},#{date},#{url})")
//    public  int insertMeeting(Meeting meeting);
//    @Update("update meeting set name=#{name},location=#{location},date=#{date},url=#{url} where name=#{old_name} and location=#{old_location} and date=#{old_date}")
//    public int updateMeeting(@Param("name") String name,@Param("location") String location,
//                             @Param("date") String date,@Param("url") String url,
//                             @Param("old_name") String old_name,@Param("old_location") String old_location,
//                             @Param("old_date") String old_date);
//    @Delete("delete from meeting where name=#{name} and location=#{location} and date=#{date}")
//    public  int deleteMeeting(@Param("name") String name,@Param("location") String location,@Param("date") String date);
}
