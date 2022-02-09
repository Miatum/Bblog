package com.miatum.blog.service;

import com.miatum.blog.entity.State;
import com.miatum.blog.mapper.StateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StateService {
    @Autowired
    private StateMapper stateMapper;
    public List<State> selectAllState() {
        return stateMapper.selectAllState();
    }
}
