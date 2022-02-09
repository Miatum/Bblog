package com.miatum.blog.entity;

public enum ResponseEnum {
    SUCCESS(200,"操作成功"), FAILED(300, "操作失败"), EXCEPTION(400, "未知异常");
    int code;
    String message;
    ResponseEnum(int code, String message) {
        this.code= code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String toString() {
        return "{" +
                "\"code\":" + "\"" + this.getCode() + "\"" + "," +
                "\"message\":" + "\"" + this.getMessage() + "\"" +
                "}";
    }
}
