package com.wangxin.spring.boot.demo.result;

/**
 * @Author:jzwx
 * @Desicription: 用于Controller层返回给页面
 * @Date:Created in 2018-09-04 15:14
 * @Modified By:
 */
public class JsonResponseData<T> {
    /** 成功标志 */
    private Boolean     ret  = false;

    /** 结果主体 */
    private ResultBody  result;

    /** 构造函数 */
    public JsonResponseData() {

    }

    public JsonResponseData(Boolean ret, String msg, int code, String message, T data) {
        this.ret = ret;
        this.result = new ResultBody(msg,code,message,data);
    }

    public Boolean getRet() {
        return ret;
    }

    public void setRet(Boolean ret) {
        this.ret = ret;
    }

    public ResultBody getResult() {
        return result;
    }

    public void setResult(ResultBody result) {
        this.result = result;
    }
}
