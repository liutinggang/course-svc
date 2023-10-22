package com.ltg.base.sys.data.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p> ClassName: CreateMenuReq </p>
 * <p> Package: com.ltg.urban.domain.user.data.request </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/24 - 10:35
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyMenuReq implements Serializable {
    @Schema(description = "菜单名称", type = "string")
    private String menuName;
    @Schema(description = "路由路劲")
    private String path;

}
