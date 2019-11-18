package com.example.demo.common.model;


import java.io.Serializable;

import lombok.Data;

@Data
public class ReturnResult implements Serializable {

	private int code;
	private String message;//消息 success,error
	private Object data;//数据对象
	
	
    private ReturnResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ReturnResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static ReturnResult success() {
        return success(null);
    }

    public static ReturnResult success(Object data) {
        return new ReturnResult(200,"success",data);
    }

    public static ReturnResult fail() {
        return fail(500,"fail");
    }

    public static ReturnResult fail(int code, String message) {
        return new ReturnResult(code,message);
    }
    
    public static ReturnResult fail(int code, String message,Object data) {
        return new ReturnResult(code,message,data);
    }
	
}
