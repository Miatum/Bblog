package com.miatum.blog.entity;

public class User {
    private int id;
    private String name;
    private String password;
    private int level;
    public User(){}
    public User(int id,String name,String password){
        this.id=id;
        this.name=name;
        this.password=password;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    @Override
    public String toString() {
        return "{"+
                "id: " + this.id + ", " +
                "name: " + this.name + ", " +
                "password:" + this.password +
                "level:" + this.level +
                "}";
    }
}
