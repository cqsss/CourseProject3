package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.meeting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface meetingMapper {
    @Select("select * from meeting where name=#{name}")
    meeting getbyname(@Param("name") String name);

}
