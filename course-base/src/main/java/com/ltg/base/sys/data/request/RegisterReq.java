package com.ltg.base.sys.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> ClassName: registReq </p>
 * <p> Package: com.ltg.urban.domain.user.data.request </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/24 - 9:33
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterReq implements Serializable {
    @Schema(title = "密码")
    private String username;
    @Schema(title = "短信验证码")
    private String smsCode;
    @Schema(title = "密码")
    private String password;
    @Schema(title = "手机号")
    private String mobile;
    @Schema(title = "真实姓名")
    private String realName;
    @Schema(title = "身份证号码")
    private String identityCode;
    @Schema(title = "头像:传id")
    private Long avatarId;

}
