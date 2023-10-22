package com.ltg.base.sys.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> ClassName: LoginInfo </p>
 * <p> Package: com.ltg.urban.domain.user.data.response </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/13 - 16:47
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {

    private String token;
    private UserInfo userInfo;
}
