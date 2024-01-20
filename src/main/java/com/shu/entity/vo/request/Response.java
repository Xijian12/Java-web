package com.shu.entity.vo.request;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class Response {
    private int code;
    private String message;
    private Object data;

    // 构造函数
    public Response(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public Response(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getter方法
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    // 如果需要，可以添加相应的setter方法

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}