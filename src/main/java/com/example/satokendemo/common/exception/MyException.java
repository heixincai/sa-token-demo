package com.example.satokendemo.common.exception;


import com.example.satokendemo.common.enums.ResultCode;

public class MyException extends RuntimeException {
    private Integer code;

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(ResultCode rc) {
        this(rc.getCode(), rc.getDesc());
    }

    public Integer getCode() {
        return code;
    }
}
