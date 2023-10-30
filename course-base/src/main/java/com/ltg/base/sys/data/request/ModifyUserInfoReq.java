package com.ltg.base.sys.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> ClassName: ModifyUserInfoReq </p>
 * <p> Package: com.ltg.urban.domain.sys.data.request </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/26 - 19:28
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyUserInfoReq implements Serializable {
    private String username;

    @Schema(title = "手机号")
    private String mobile;
    @Schema(title = "真实姓名")
    private String realName;
    @Schema(title = "身份证号码")
    private String identityCode;
    @Schema(title = "头像url")
    private String avatarUrl;
    @Schema(title = "头像id")
    private Long avatarId;
}
