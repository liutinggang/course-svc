package com.ltg.base.sys.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> ClassName: ModifyUserRoleReq </p>
 * <p> Package: com.ltg.urban.domain.sys.data.request </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/26 - 19:39
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyUserRoleReq implements Serializable {

    @NotNull
    @Schema(description = "用户id")
    private Long userId;
    @NotNull
    @Schema(description = "角色id")
    private Long roleId;
}
