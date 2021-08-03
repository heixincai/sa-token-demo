package com.example.satokendemo.common.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "API返回参数")
@NoArgsConstructor
@Getter
@Setter
public class ApiResult<T> {
    @ApiModelProperty(value = "响应消息", required = false)
    private String message;

    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应数据", required = false)
    private T data;

    // 过期
    public static ApiResult<String> expired(String msg) {
        return new ApiResult<>(ResultCode.UN_LOGIN, msg);
    }

    public static ApiResult<String> fail(String msg) {
        return new ApiResult<>(ResultCode.FAILURE, msg);
    }

    public static <T> ApiResult<T> fail(String msg, T e) {
        return new ApiResult<>(ResultCode.FAILURE.getCode(), msg, e);
    }

    public static <T> ApiResult<T> fail(ResultCode r, T e) {
        return new ApiResult<>(r.getCode(), r.getDesc(), e);
    }

    // 自定义错误返回码
    public static ApiResult<String> fail(Integer code, String msg) {
        return new ApiResult<>(code, msg, null);
    }

    public static ApiResult<String> ok() {
        return new ApiResult<>(ResultCode.SUCCESS, "OK");
    }

    public static <T> ApiResult<T> build(Integer code, String msg, T data) {
        return new ApiResult<>(code, msg, data);
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), data);
    }

    public static <T> ApiResult<T> ok(String msg, T data) {
        return new ApiResult<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static ApiResult ok(Integer code, String message) {
        return new ApiResult(code, message);
    }

    public static <T> ApiResult<T> ok(Integer code, String message, T data) {
        return new ApiResult<>(code, message, data);
    }

    public static ApiResult build(Integer code, String msg) {
        return new ApiResult<>(code, msg, null);
    }


    public ApiResult(ResultCode result) {
        this(result.getCode(), result.getDesc(), null);
    }

    public ApiResult(ResultCode result, String msg) {
        this(result.getCode(), msg, null);
    }

    public ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ApiResult(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = "OK";
        this.data = data;
    }

    public ApiResult(String message) {
        this(ResultCode.SUCCESS.getCode(), message, null);
    }

    public ApiResult(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
