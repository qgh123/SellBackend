package com.zhu.sellbackend.base;

/**
 * @ClassName BaseModel
 * @Description
 * @Author qgh
 * @Date 2020-02-26 19:37
 **/
public class BaseModel<T> {

    private static final String SUCCESS = "200";
    private static final String BUSINESS_ERROR = "300";
    private String status;
    private String message;
    private T data;


    public static <T> BaseModel<T> buildSuccess() {
        String successMessage = "操作成功";
        return build(null, SUCCESS, successMessage);
    }


    public static <T> BaseModel<T> buildSuccess(T data) {

        String successMessage = "操作成功";
        return build(data, SUCCESS, successMessage);
    }


    public static <T> BaseModel<T> buildError(String errorMessage, String errorCode) {
        return build(null, errorCode, errorMessage);
    }

    public static <T> BaseModel<T> buildError(Exception e, String errorCode) {
        String errorMessage = e.getMessage();
        return build(null, errorCode, errorMessage);
    }

    public static <T> BaseModel<T> buildError(String errorMessage) {
        return build(null, BUSINESS_ERROR, errorMessage);
    }

    public static <T> BaseModel<T> buildError(Exception e) {
        String errorMessage = e.getMessage();
        return build(null, BUSINESS_ERROR, errorMessage);
    }


    private static <T> BaseModel<T> build(T data, String status, String message) {
        BaseModel<T> result = new BaseModel<>();
        result.setData(data);
        result.setStatus(status);
        result.setMessage(message);
        return result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
