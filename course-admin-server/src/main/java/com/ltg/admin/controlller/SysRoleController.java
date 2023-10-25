package com.ltg.admin.controlller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ltg.base.sys.data.request.ModifyRoleReq;
import com.ltg.base.sys.entity.SysRole;
import com.ltg.framework.util.http.PageRequest;
import com.ltg.framework.util.http.Result;
import com.ltg.framework.annotation.DescribePage4Swagger;
import com.ltg.base.sys.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> ClassName: SysRoleController </p>
 * <p> Package: com.ltg.urban.domain.user.controller </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/12 - 20:07
 * @Version: v1.0
 */


@RestController
@RequiredArgsConstructor
@Tag(name = "角色模块")
public class SysRoleController {

    private final SysRoleService sysRoleService;


    /**
     * 2角色模块:系统中包含多个角色信息例如:系统管理员 项目管理员 项目经理 普通员工，每个角色具有不同的权限操作。
     * @param request
     * @param roleName
     * @param roleId
     * @return
     */

    @Operation(summary = "分页查询:OK")
    @GetMapping("/api/v1/sysRole/list")
    @DescribePage4Swagger
    public Result<Page<SysRole>> pageList(HttpServletRequest request,
                                          @RequestParam(required = false) String roleName,

                                          @RequestParam(required = false)String roleId) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Strings.isNotBlank(roleName),SysRole::getRoleName,roleName);
        return Result.success(sysRoleService.page(PageRequest.buildFromRequest(request),wrapper));
    }

    @Operation(summary = "角色创建:OK")
    @PostMapping("/api/v1/sysRole/create")
    public Result<SysRole> create(@RequestBody ModifyRoleReq req) {
        return sysRoleService.createRole(req);
    }

    @Operation(summary = "角色修改:OK")
    @PutMapping("/api/v1/sysRole/{roleId}/modify")
    public Result<SysRole> modify(@PathVariable Long roleId, @RequestBody ModifyRoleReq req) {
        return sysRoleService.modify(roleId, req);
    }



    @Operation(summary = "角色删除:OK")
    @DeleteMapping("/api/v1/sysRole/{roleId}/delete")
    public Result<Void> delete(@PathVariable Long roleId) {
        sysRoleService.removeById(roleId);
        return Result.success();
    }

    @Operation(summary = "下拉列表:OK")
    @GetMapping("/api/v1/sysRole/getAll")
    public Result<List<SysRole>> getAll() {
        return Result.success(sysRoleService.list());
    }


}
