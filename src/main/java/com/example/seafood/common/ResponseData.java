package com.example.seafood.common;

import lombok.Data;

@Data
public class ResponseData<T> {
    //一、属性（结果对象的数据组成）
    //1.返回状态码
    private Integer code;
    //2.返回消息
    private String message;
    //3.返回数据
    private T data;

    public ResponseData() {
    }

    //二、构造数据的方法
    //1.有数据+无数据
    protected static <T> ResponseData<T> build(T data) {
        ResponseData<T> result = new ResponseData<T>();
        if (data != null)
            result.setData(data);
        return result;
    }

    //2.有数据+自定义状态码+自定义消息
    public static <T> ResponseData<T> build(T body, Integer code, String message) {
        ResponseData<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    //3.有数据+枚举型的（状态码+消息）
    public static <T> ResponseData<T> build(T body, SysResponseEnum resultCodeEnum) {
        ResponseData<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    //三、成功或失败操作
    //1.成功
    //有数据的ok
    public static <T> ResponseData<T> ok(T data) {
        //构造数据（有数据+枚举型状态码和消息）
        return build(data, SysResponseEnum.SUCCESS);
    }

    //无数据的ok
    public static <T> ResponseData<T> ok() {
        return ResponseData.ok(null);
    }

    //2.失败
    //有数据的fail
    public static <T> ResponseData<T> fail(T data) {
        return build(data, SysResponseEnum.FAILED);
    }

    //无数据的fail
    public static <T> ResponseData<T> fail() {
        return ResponseData.fail(null);
    }


    public ResponseData<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public ResponseData<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}
