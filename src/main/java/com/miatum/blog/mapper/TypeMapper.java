package com.miatum.blog.mapper;

import com.miatum.blog.entity.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface TypeMapper {
    @Select("select * from type where id = #{id}")
    Type selectTypeById(int id);
    @Select("select * from type")
    HashSet<Type> selectAllType();
    @Update("update type set type_name = #{type_name}, type_name_en = #{type_name_en} where id = #{id}")
    int updateType(Type type);
    @Delete("delete from type where id = #{id}")
    int deleteType(int id);
    @Delete({
            "<script>",
            "delete from type",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    int deleteTypes(@Param("ids") int[] ids);
    @Insert("insert into type values(null, #{type_name}, #{type_name_en})")
    int insertType(Type type);
    @Select("select last_insert_id()")
    int lastInsertId();
}
