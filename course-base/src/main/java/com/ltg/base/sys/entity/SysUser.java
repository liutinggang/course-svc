package com.ltg.base.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltg.framework.mybatis.entities.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> ClassName: User </p>
 * <p> Package: com.ltg.urban.user.entity </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 18:20
 * @Version: v1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {
    @Schema(title = "手机号")
    private String mobile;
    @Schema(title = "用户名")
    private String username;
    @Schema(title = "密码")
    @JsonIgnore
    private String password;

    @Schema(title = "真实姓名")
    private String displayName;
    @Schema(title = "身份证号码")
    private String identityCode;
    @Schema(title = "头像文件id")
    private Long avatarId;

    @Schema(title = "头像地址")
    @TableField(exist = false)
    private String avatarUrl;
    @Schema(title = "头像地址")
    private Long roleId;

}
