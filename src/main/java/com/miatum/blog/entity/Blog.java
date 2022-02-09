package com.miatum.blog.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Blog implements Serializable {
    private int id;
    private String title;
    private String intro;
    private String cover;
    private int state_id;
    private String state_name;
    private int type_id;
    private String type_name;
    private int tag_id;
    private String tag_name;
    private String author;
    private String resource_path;
    private int featured;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String content;
    public Blog () {}
    public Blog (int id, String title, int type_id, int tag_id, String author, Date date, String content) {
        this.id = id;
        this.title = title;
        this.type_id = type_id;
        this.tag_id = tag_id;
        this.author = author;
        this.date = date;
        this.content = content;
    }
    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getIntro () {
        return intro;
    }

    public void setIntro (String intro) {
        this.intro = intro;
    }
    public String getCover () {
        return cover;
    }

    public void setCover (String cover) {
        this.cover = cover;
    }

    public int getState_id() {
        return state_id;
    }
    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public int getType_id () {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStringDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(this.date);
    }
    public Date getDate(){
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResource_path() {
        return resource_path;
    }
    public void setResource_path(String resurce_path) {
        this.resource_path = resurce_path;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    @Override
    public String toString() {
        return "{"+
                "id:"+this.id+","+
                "title:"+this.title+","+
                "type_id:"+this.type_id+","+
                "tag_id:"+this.tag_id+","+
                "author:"+this.author+","+
                "date:"+this.date+","+
                "content:"+this.content+
                "}";
    }
}
