package com.miatum.blog.entity;

public class Type {
    private int id;
    private String type_name;
    private String type_name_en;

    public Type(){}
    public Type(int id, String type_name){
        this.id=id;
        this.type_name=type_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_name_en() {
        return type_name_en;
    }

    public void setType_name_en(String type_name_en) {
        this.type_name_en = type_name_en;
    }

    @Override
    public String toString() {
        return "{"+
                "id:"+this.id+","+
                "type_name:"+this.type_name+
                "type_name_en:"+this.type_name_en+
                "}";
    }
}
