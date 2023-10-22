package com.ltg.framework.util.http;

import lombok.Getter;

/**
 * <p> ClassName: ResultEnum </p>
 * <p> Package: com.ltg.framework.util </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/7 - 21:38
 * @Version: v1.0
 */

@Getter
public enum ResultEnum {


    RESULT_SUCCESS(0, "success"),
    RESULT_ERROR(-1, "error");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
