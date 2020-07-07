package com.eale.auth.common;

/**
 * @Author Admin
 * @Date 2020/7/7
 * @Description
 * @Version 1.0
 **/
public class Result<T> {


    private Integer code;

    private String message;

    private T data;


    /**
     * 成功调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T>Result<T> success(T data){
        return new Result<T>(data);
    }


    public static <T>Result<T> error(CodeMsg codeMsg){
        return new  Result<T>(codeMsg);

    }


    public Result(T data) {
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(CodeMsg codeMsg) {
        if (codeMsg != null){
            this.code = codeMsg.getCode();
            this.message = codeMsg.getMsg();

        }
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
