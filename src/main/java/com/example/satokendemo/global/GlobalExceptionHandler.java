package com.example.satokendemo.global;

import com.example.satokendemo.common.ArgumentInvalid;
import com.example.satokendemo.common.exception.MyException;
import com.example.satokendemo.common.util.ApiResult;
import com.example.satokendemo.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResult<String> handleDataStoreException(MyException ex) {
        log.error("业务异常。message={}", ex.getMessage());
        Integer code = ex.getCode();
        return ApiResult.fail(code == null ? ResultCode.FAILURE.getCode() : code, ex.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResult<String> handleMissingArgumentException(MissingServletRequestParameterException ex) {
        log.error("缺少参数。{}", ex.getMessage());
        return ApiResult.fail(ResultCode.INVALID_ARGUMENT, "缺少参数：" + ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResult<List<ArgumentInvalid>> handleArgumentValidException(MethodArgumentNotValidException ex) {
        List<ArgumentInvalid> errs = new LinkedList<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            errs.add(new ArgumentInvalid(err.getField(), err.getDefaultMessage()));
        }

        log.error("参数校验错误。{}", errs);
        return ApiResult.fail(ResultCode.INVALID_ARGUMENT, errs);
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResult<String> handleArgumentValidException(MaxUploadSizeExceededException ex) {
        return ApiResult.fail(ResultCode.INVALID_ARGUMENT, "文件过大。");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResult<String> handleJsonParseException(HttpMessageNotReadableException ex) {
        log.error("请求参数解析异常。{}", ex.getMessage());
        return ApiResult.fail(ResultCode.INVALID_ARGUMENT, "请求参数解析异常。" + ex.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResult<String> handleBindEx(BindException ex) {
        log.error("请求参数解析绑定异常。{}", ex.getMessage());
        return ApiResult.fail(ResultCode.INVALID_ARGUMENT, "请求参数解析绑定异常。");
    }

    @ExceptionHandler(value = DataAccessException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResult handleDaoException(DataAccessException ex) {
        log.error("Server DataAccessException.", ex);
        return ApiResult.fail("数据库访问异常");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResult runtimeExceptionHandler(Exception ex) {
        log.error("Server Exception.", ex);
        return ApiResult.fail(ResultCode.INTERNAL_SERVER_ERROR, "服务内部异常");
    }
}
