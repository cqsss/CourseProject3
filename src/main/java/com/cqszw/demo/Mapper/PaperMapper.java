package com.cqszw.demo.Mapper;

import com.cqszw.demo.Bean.Paper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cqs
 * @date 2020/5/27
 */
@Repository
public interface PaperMapper {
    @Select("select * from paper")
    List<Paper> getAll();
    @Select("select topic from paper where id=#{id}")
    public String gettopicbyid(@Param("id") int id);
    @Select("select * from paper where id=#{id}")
    public Paper getpaperbyid(@Param("id") int id);
    @Select("select * from paper where type=#{type}")
    public List<Paper> getPaperByType(@Param("type") String type);
    @Select("select type from paper")
    public List<String> getType();
    @Select("select id from paper where topic=#{topic} and type=#{type} and author=#{author} and keyword=#{keyword}")
    public int getidByPaper(@Param("topic") String topic,@Param("type") String type,@Param("author") String author,@Param("keyword") String keyword);
    @Select("select * from paper where topic like CONCAT(CONCAT('%', #{keyword}), '%') " +
            "or author like CONCAT(CONCAT('%', #{keyword}), '%') " +
            "or type like CONCAT(CONCAT('%', #{keyword}), '%')" +
            "or keyword like CONCAT(CONCAT('%', #{keyword}), '%')")
    List<Paper> search(@Param("keyword") String keyword);
    @Select("select * from paper where type=#{type} and (topic like CONCAT(CONCAT('%', #{keyword}), '%') " +
            "or author like CONCAT(CONCAT('%', #{keyword}), '%') " +
            "or type like CONCAT(CONCAT('%', #{keyword}), '%')" +
            "or keyword like CONCAT(CONCAT('%', #{keyword}), '%'))")
    List<Paper> searchandtype(@Param("type") String type,@Param("keyword") String keyword);
    @Insert("insert into paper(topic,type,author,keyword) values(#{topic},#{type},#{author},#{keyword})")
    public int insertPaper(Paper paper);
    @Delete("delete from paper where id = #{id}")
    public int deletePaper(int id);
}
