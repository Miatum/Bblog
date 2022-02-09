package com.miatum.blog.mapper;

import com.miatum.blog.entity.Email;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailMapper {
    @Select("select * from email")
    List<Email> selectAllEmail();
    @Insert("insert into email values(null, #{address})")
    int insertEmail(Email email);
}
