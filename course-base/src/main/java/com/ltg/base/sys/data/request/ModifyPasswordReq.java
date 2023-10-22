package com.ltg.base.sys.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> ClassName: ModifyPasswordReq </p>
 * <p> Package: com.ltg.urban.domain.sys.data.request </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/26 - 19:21
 * @Version: v1.0
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPasswordReq implements Serializable {

    @Schema(description = "手机")
    private String mobile;
    @Schema(description = "验证码")
    private String smsCode;
    @Schema(description = "验证码")
    private String password;

}
