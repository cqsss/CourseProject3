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
    @Insert("insert into user(username,password,is_manager,name,age,sex,telephone,introduce) " +
            "values(#{username},#{password},#{is_manager},#{name},#{age},#{sex},#{telephone},#{introduce})")
    public  int insertUser(User user);
    @Update("update user set username=#{username},password=#{password},is_manager=#{is_manager}," +
            "name=#{name},age=#{age},sex=#{sex},telephone=#{telephone},introduce=#{introduce}" +
            " where username=#{old_username}")
    public int updateUser(@Param("username") String username,@Param("password") String password,
                             @Param("is_manager") boolean is_manager,@Param("name") String name,
                             @Param("age") int age,@Param("sex") String sex,
                             @Param("telephone") String telephone,@Param("introduce") String introduce,
                             @Param("old_username") String old_name);
    @Delete("delete from user where username=#{username}")
    public  int deleteUser(@Param("username") String username);
    @Select("select id from user where username=#{username}")
    public int getuserid(@Param("username")String username);
}
