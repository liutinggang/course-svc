package com.ltg.admin.controlller;

import com.ltg.base.sys.data.request.ModifyUserRoleReq;
import com.ltg.base.sys.data.response.UserInfo;
import com.ltg.base.sys.data.response.UserPageResp;
import com.ltg.framework.util.http.PageRequest;
import com.ltg.framework.util.http.Result;
import com.ltg.framework.annotation.DescribePage4Swagger;
import com.ltg.framework.util.http.PageInfo;

import com.ltg.base.sys.entity.SysUser;
import com.ltg.base.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> ClassName: SysUserController </p>
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
@Tag(name = "用户模块")
public class SysUserController {

    /**
     * 4.用户模块:用户模块可以实现对用户信息的管理和维护，在用户模块可以给用户设置不同的角色
     */

    /**
     * 5.管理员模块:管理员可对系统中所有角色，权限、用户信息进行管理
     */
    private final SysUserService sysUserService;

    @GetMapping("/api/v1/sysUser/list")
    @Operation(summary = "分页查询:OK")
    @DescribePage4Swagger
    public PageInfo<UserPageResp> query(HttpServletRequest httpServletRequest,
                                        @RequestParam(required = false) String keyword,
                                        @RequestParam(required = false)String roleId) {
        return sysUserService.pageList(PageRequest.buildFromRequest(httpServletRequest),keyword,roleId);
    }

    @GetMapping("/api/v1/sysUser/detail/{userId}")
    @Operation(summary = "后台管理员获取用户详情:页面单开:OK")
    public Result<UserInfo> detail(@PathVariable Long userId) {
        return sysUserService.getUserDetail(userId);
    }

    @PutMapping("/api/v1/sysUser/modify/role")
    @Operation(summary = "修改:OK")
    public Result<UserInfo> modifyUserRole(@RequestBody ModifyUserRoleReq req) {
        return sysUserService.modifyUserRole(req);
    }


    @GetMapping("/api/v1/sysUser/findAll")
    @Operation(summary = "下来菜单:Ok")
    public Result<List<SysUser>> getUsers(@RequestParam Integer position) {
        return sysUserService.getUsers(position);
    }

}
