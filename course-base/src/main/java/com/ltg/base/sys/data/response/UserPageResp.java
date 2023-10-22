package com.ltg.base.sys.data.response;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p> ClassName: UserPageResp </p>
 * <p> Package: com.ltg.urban.domain.sys.data.response </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/3/1 - 22:30
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageResp implements Serializable {

    @Schema(title = "手机号")
    private Long id;
    @Schema(title = "创建时间")

    private LocalDateTime  createTime;
    @Schema(title = "手机号")
    private String mobile;
    @Schema(title = "用户名")
    private String username;
    @Schema(title = "密码")
    private String password;

    @Schema(title = "真实姓名")
    private String realName;
    @Schema(title = "身份证号码")
    private String identityCode;
    @Schema(title = "头像文件id")
    private Long avatarId;

    @Schema(title = "头像地址")
    @TableField(exist = false)
    private String avatarUrl;
    @Schema(title = "头像地址")
    private Long roleId;
    @Schema(title = "角色名称")
    private String roleName;
}
