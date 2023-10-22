package com.ltg.framework.util.http;

import lombok.Data;

/**
 * <p> ClassName: Result </p>
 * <p> Package: com.ltg.framework.util </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/1/7 - 20:26
 * @Version: v1.0
 */

@Data
public class Result<T> {


    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String message;

    /**
     * 具体的内容.
     */
    private T data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> Result<T> success(ResultEnum resultEnum) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMessage());
    }


    public static <T> Result<T> success(T data) {
        Result<T> result = success();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.RESULT_SUCCESS.getCode(), ResultEnum.RESULT_SUCCESS.getMessage());
    }



    public static <T> Result<T> error(String message) {
        return new Result<T>(-1, message);
    }

    public static <T> Result<T> error(ResultEnum resultEnum, String message) {
        Result<T> result = error(resultEnum);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        return new Result<T>(resultEnum.getCode(), resultEnum.getMessage());
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<T>(code, message);
    }

}
