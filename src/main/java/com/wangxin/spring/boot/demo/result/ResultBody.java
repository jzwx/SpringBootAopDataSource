package com.wangxin.spring.boot.demo.result;

/**
 * @Author:jzwx
 * @Desicription: 结果主体
 * @Date:Created in 2018-09-04 15:14
 * @Modified By:
 */
public class ResultBody<T> {
    /** 错误描述 */
    private String msg;

    /** 错误码 */
    private int    code;

    /** 错误描述 */
    private String message;

    /** 业务对象 */
    private T      data;

    /**
     * 构造函数
     * @param msg
     * @param code
     * @param message
     * @param data
     */
    public ResultBody(String msg, int code, String message, T data) {
        this.msg = msg;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
