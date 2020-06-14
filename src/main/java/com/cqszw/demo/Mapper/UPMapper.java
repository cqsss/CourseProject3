package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.User_Publish;
import com.cqszw.demo.Bean.User_Publish_Record;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UPMapper {
    @Insert("insert into user_publish(username,meeting_id) values(#{username},#{meeting_id})")
    public int insert(User_Publish user_publish);
    @Update("update user_publish set is_checked=1 where meeting_id=#{meeting_id}")
    public int pass(@Param("meeting_id") int id);
    @Update("update user_publish set is_checked=2 where meeting_id=#{meeting_id}")
    public int confuse(@Param("meeting_id") int id);
    @Update("update user_publish set is_checked=0 where meeting_id=#{meeting_id}")
    public int clear(@Param("meeting_id") int id);
    @Select("select name,location,date,url,type,is_checked,id from user_publish,meeting " +
            "where user_publish.meeting_id=meeting.id and username=#{username}")
    public List<User_Publish_Record> getAll(@Param("username")String username);
    @Select("select * from user_publish where meeting_id=#{meeting_id}")
    public User_Publish getById(@Param("meeting_id") int meeting_id);
    @Delete("delete from user_publish where meeting_id=#{meeting_id}")
    public  int deleteById(@Param("meeting_id") int meeting_id);
}
