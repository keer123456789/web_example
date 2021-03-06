package com.example.web_example.Domain;

public class WebResult<T> {
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    private int status;
    private T data;
    private String message;

    public WebResult() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
