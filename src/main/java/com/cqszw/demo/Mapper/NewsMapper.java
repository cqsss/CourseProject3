package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  NewsMapper {
    @Select("select * from news where id=#{id}")
     News getbyid(@Param("id") int id);
    @Select("select * from news order by publishdate desc")
     List<News> getAll();
    @Select("select * from news where type=#{type}")
    List<News> getNewsByType(@Param("type") String type);
    @Insert("insert into news(title,content,publishdate,type) values(#{title},#{content},#{publishdate},#{type})")
    int insertNews(News news);
    @Select("select * from news where title like CONCAT(CONCAT('%', #{keyword}), '%') " +
            "or publishdate like CONCAT(CONCAT('%', #{keyword}), '%') or type like CONCAT(CONCAT('%', #{keyword}), '%')")
    List<News> search(@Param("keyword") String keyword);
    @Select("select * from news where type=#{type} and (title like CONCAT(CONCAT('%', #{keyword}), '%') " +
            "or publishdate like CONCAT(CONCAT('%', #{keyword}), '%') or type like CONCAT(CONCAT('%', #{keyword}), '%'))")
    List<News> searchadntype(@Param("type") String type,@Param("keyword") String keyword);
    @Update("update news set title=#{title},content=#{content},type=#{type} where id=#{id}")
    public int updateNews(@Param("title") String title,@Param("content") String content,
                             @Param("type") String type,
                             @Param("id") int id);
    @Update("update news set viewcount=#{viewcount}+1 where id=#{id}")
    public int viewcount(@Param("viewcount") int viewcount,@Param("id")int id);
    @Delete("delete from news where id=#{id}")
    int deleteNews(@Param("id") int id);
}
