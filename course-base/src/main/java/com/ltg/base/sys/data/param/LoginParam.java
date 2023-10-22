package com.ltg.base.sys.data.param;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> ClassName: LoginParam </p>
 * <p> Package: com.ltg.framework.param </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 21:15
 * @Version: v1.0
 */

@Data
public class LoginParam implements Serializable {

    private String username;

    private String password;
}
