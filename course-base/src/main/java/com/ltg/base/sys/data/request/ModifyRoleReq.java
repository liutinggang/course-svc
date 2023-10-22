package com.ltg.base.sys.data.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> ClassName: CreateRoleReq </p>
 * <p> Package: com.ltg.urban.domain.user.data.request </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/13 - 9:46
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyRoleReq implements Serializable {
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色编号")
    private String roleCode;

}
