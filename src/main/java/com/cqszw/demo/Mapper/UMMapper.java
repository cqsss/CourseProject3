package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.Meeting;
import com.cqszw.demo.Bean.User_Meetings;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface UMMapper {
    @Select("select * from user_meetings where user_id=#{user_id}")
    public List<User_Meetings> getbyuser(@Param("user_id") int user_id);
    @Select("SELECT * from meeting where id in(" +
            " SELECT meeting_id from user_meetings where user_id=#{user_id}) and date=#{date}")
    public  List<Meeting> getbyuseranddate(@Param("user_id") int user_id,@Param("date") String date);
}
