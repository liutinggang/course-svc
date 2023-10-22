package com.ltg.framework.error;

import com.ltg.framework.error.exception.BaseException;
import com.ltg.framework.util.http.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * <p> ClassName: RestExceptionHandler </p>
 * <p> Package: com.ltg.framework.error </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/1/7 - 20:23
 * @Version: v1.0
 */

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {


    /**
     * 默认全局异常处理。
     *
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception e) {

        if (e instanceof BaseException baseException) {
            return Result.error(baseException.getCode(), baseException.getMessage());
        } else {
            log.info("[系统异常]{}", e);
            return Result.error(500, "未知错误");
        }
    }



}
