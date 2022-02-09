package com.miatum.blog.mapper;

import com.miatum.blog.entity.State;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StateMapper {
    @Select("select * from state")
    public List<State> selectAllState();
}
