package com.miatum.blog.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.miatum.blog.entity.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user where name = #{name}")
    User selectUserByName (String name);
    @Select("select * from user")
    HashSet<User> selectAllUser ();
    @Update("update user set name = #{name}, password = #{password} where id = #{id}")
    int updateUser (User user);
    @Delete("delete from user where id = #{id}")
    int deleteUser (int id);
    @Insert("insert into user values(null, #{name}, #{password})")
    int insertUser(User user);
}
