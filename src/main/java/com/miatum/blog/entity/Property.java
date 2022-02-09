package com.miatum.blog.entity;

/**
 * @Description
 * @ClassName Property
 * @Author Miatum
 * @date 2022.02.09 17:20
 */
public class Property {
    private int id;
    private String key;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
