package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Bean.User_Meetings;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface UMMapper {
    @Select("select * from meeting where id in(select meeting_id from user_meetings where username=#{username})")
    public List<Meeting> getbyuser(@Param("username") String username);
    @Select("SELECT * from meeting where id in(" +
            " SELECT meeting_id from user_meetings where username=#{username}) and date=#{date}")
    public  List<Meeting> getbyuseranddate(@Param("username") String username,@Param("date") String date);
    @Delete("delete from user_meetings where meeting_id=#{meeting_id} and username=#{username}")
    public  int deleteUserMeeting(@Param("meeting_id") int meeting_id,@Param("username") String username);
    @Select("select * from user_meetings where username=#{username} and meeting_id=#{meeting_id}")
    public User_Meetings get(@Param("username")String username,@Param("meeting_id")int meeting_id);
    @Insert("insert into user_meetings(username,meeting_id) values(#{username},#{meeting_id})")
    public  int insert(@Param("username")String username,@Param("meeting_id")int meeting_id);
}
