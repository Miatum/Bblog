package com.miatum.blog.controller;

import com.miatum.blog.entity.State;
import com.miatum.blog.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/api/state")
public class StateController {
    @Autowired
    private StateService stateService;
    @RequestMapping("/selectAllState")
    @ResponseBody
    public List<State> selectAllState() {
        return stateService.selectAllState();
    }
}
