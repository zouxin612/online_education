package com.tct.servicebase.exceptionhandler;

import com.tct.commonutils.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public ResponseResult error(Exception e) {
        e.printStackTrace();
        return ResponseResult.error().message("执行了全局异常处理..");
    }
}
