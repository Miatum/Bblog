package com.miatum.blog.mapper;

import com.miatum.blog.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface TagMapper {
    @Select("select * from tag where id = #{id}")
    Tag selectTagById (int id);
    @Select("select * from tag")
    HashSet<Tag> selectAllTag ();
    @Update("update tag set tag_name = #{tag_name} where id = #{id}")
    int updateTag (Tag tag);
    @Delete("delete from tag where id = #{id}")
    int deleteTag (int id);
    @Delete({
            "<script>",
            "delete from tag",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    int deleteTags (@Param("ids") int[] ids);
    @Insert("insert into tag values(null, #{tag_name})")
    int insertTag (Tag tag);
    @Select("select last_insert_id()")
    int lastInsertId ();
}
