package com.ltg.base.sys.entity;

import com.ltg.framework.mybatis.entities.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> ClassName: SysRole </p>
 * <p> Package: com.ltg.urban.domain.user.entity </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 21:51
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRole extends BaseEntity {
    @Schema(name = "角色姓名", type = "string", example = "")
    private String roleName;
    @Schema(name = "角色编号", type = "string", example = "")
    private String roleCode;

}
