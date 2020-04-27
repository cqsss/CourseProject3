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
    @Select("select * from meeting where id in(select meeting_id from user_meetings where user_id=#{user_id})")
    public List<Meeting> getbyuser(@Param("user_id") int user_id);
    @Select("SELECT * from meeting where id in(" +
            " SELECT meeting_id from user_meetings where user_id=#{user_id}) and date=#{date}")
    public  List<Meeting> getbyuseranddate(@Param("user_id") int user_id,@Param("date") String date);
    @Delete("delete from user_meetings where meeting_id=#{meeting_id} and user_id=#{user_id}")
    public  int deleteUserMeeting(@Param("meeting_id") int meeting_id,@Param("user_id") int user_id);
    @Select("select * from user_meetings where user_id=#{user_id} and meeting_id=#{meeting_id}")
    public User_Meetings get(@Param("user_id")int user_id,@Param("meeting_id")int meeting_id);
    @Insert("insert into user_meetings(user_id,meeting_id) values(#{user_id},#{meeting_id})")
    public  int insert(@Param("user_id")int user_id,@Param("meeting_id")int meeting_id);
}
