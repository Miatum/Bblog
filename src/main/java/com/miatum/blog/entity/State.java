package com.miatum.blog.entity;

public class State {
    private int id;
    private String state_name;
    public State() {};

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getState_name() {
        return state_name;
    }
}
