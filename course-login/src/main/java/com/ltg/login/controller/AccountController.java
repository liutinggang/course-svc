package com.ltg.login.controller;

import com.ltg.base.sys.data.param.LoginParam;
import com.ltg.base.sys.data.request.ModifyPasswordReq;
import com.ltg.base.sys.data.request.ModifyUserInfoReq;
import com.ltg.base.sys.data.request.RegisterReq;
import com.ltg.base.sys.data.response.CurrentUserHolder;
import com.ltg.framework.util.http.Result;

import com.ltg.base.sys.data.response.LoginInfo;
import com.ltg.base.sys.data.response.UserInfo;
import com.ltg.base.sys.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p> ClassName: LoginController </p>
 * <p> Package: com.ltg.urban.domain.user.controller </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 19:31
 * @Version: v1.0
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "登录注册模块")
public class AccountController {

    /**
     * 1.登录注册模块:用户可以注册登录该系统，需要完善相关信息及验证。此模块下包含用户注册、用户登录、修改密码、找回密码、修改个人信息等功能;
     */
    private final LoginService loginService;


    /**
     * 登录
     *
     * @param loginParam
     * @return
     */
    @PostMapping("/api/v1/account/login")
    @Operation(summary = "登录:OK")
    public Result<LoginInfo> login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }

    @GetMapping("/api/v1/account/userInfo")
    @Operation(summary = "获取用户信息")
    public Result<UserInfo> getUserInfo( ) {
        UserInfo currentUser = CurrentUserHolder.getCurrentUser();
        return Result.success(currentUser);
    }

    @PostMapping("/api/v1/account/modify/password")
    @Operation(summary = "忘记密码/修改密码:OK")
    public Result<String> modifyPassword(@RequestBody ModifyPasswordReq req) {
        return loginService.modifyPassword(req);
    }

    @PostMapping("/api/v1/account/modify/userInfo")
    @Operation(summary = "修改个人信息:OK")
    public Result<UserInfo> modifyUserInfo(@RequestBody ModifyUserInfoReq req) {
        return loginService.modifyUserInfo(req);
    }

    @PostMapping("/api/v1/account/register")
    @Operation(summary = "注册:OK")
    public Result<Object> register(@RequestBody RegisterReq req) {
        return loginService.register(req);
    }


    @GetMapping("/loginOut")
    @Operation(summary = "退出登录:暂时不用")
    public Result<UserInfo> loginOut() {
        return Result.success();
    }


}
