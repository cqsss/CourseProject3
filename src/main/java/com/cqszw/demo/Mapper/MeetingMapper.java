package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.Meeting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingMapper {
    @Select("select * from meeting where name=#{name}")
    Meeting getbyname(@Param("name") String name);
    @Insert("insert into meeting(name,content,location,date,longitude,latitude) values(#{name},#{content},#{location},#{date},#{longitude},#{latitude})")
    public  int insertMeeting(Meeting meeting);
}
