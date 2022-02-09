package com.miatum.blog.entity;

public class Tag {
    private int id;
    private String tag_name;

    public Tag(){}
    public Tag(int id, String tag_name){
        this.id=id;
        this.tag_name=tag_name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    @Override
    public String toString() {
        return "{"+
                "id:"+this.id+","+
                "tag_name:"+this.tag_name+
                "}";
    }
}
