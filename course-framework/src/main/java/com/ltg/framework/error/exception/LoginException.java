package com.ltg.framework.error.exception;

/**
 * <p> ClassName: LoginException </p>
 * <p> Package: com.ltg.framework.error.exception </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 22:34
 * @Version: v1.0
 */
public class LoginException extends BaseException{

    private Integer code;

    public LoginException(String message) {
        super(message);
        this.code = -1;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
