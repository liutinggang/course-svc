package com.ltg.framework.error.exception;

/**
 * <p> ClassName: BaseException </p>
 * <p> Package: com.ltg.framework.error.exception </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 22:36
 * @Version: v1.0
 */
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(String message) {
        super(message);
        this.code = 500;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
