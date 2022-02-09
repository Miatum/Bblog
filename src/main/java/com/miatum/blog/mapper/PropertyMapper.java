package com.miatum.blog.mapper;

import com.miatum.blog.entity.Property;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface PropertyMapper {
    @Select("select * from properties")
    HashSet<Property> selectAllProperties();
}
