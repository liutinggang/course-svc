package com.ltg.base.sys.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltg.base.file.entity.FileInfo;
import com.ltg.base.file.service.FileInfoService;
import com.ltg.base.sys.data.param.LoginParam;
import com.ltg.base.sys.data.request.ModifyPasswordReq;
import com.ltg.base.sys.data.request.ModifyUserInfoReq;
import com.ltg.base.sys.data.request.RegisterReq;
import com.ltg.base.sys.data.response.LoginInfo;
import com.ltg.base.sys.data.response.UserInfo;
import com.ltg.base.sys.entity.SysRole;
import com.ltg.base.sys.entity.SysUser;
import com.ltg.base.sys.mapper.SysRoleMapper;
import com.ltg.base.sys.mapper.SysUserMapper;
import com.ltg.base.sys.service.LoginService;
import com.ltg.base.sys.service.SysUserService;
import com.ltg.framework.error.exception.BusinessException;
import com.ltg.framework.error.exception.LoginException;
import com.ltg.framework.util.JwtUtil;
import com.ltg.framework.util.Md5Util;
import com.ltg.framework.util.http.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p> ClassName: LoginServiceImpl </p>
 * <p> Package: com.ltg.urban.domain.user.service.impl </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 19:33
 * @Version: v1.0
 */

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final SysUserMapper sysUserMapper;
    private final SysUserService sysUserService;
    private final SysRoleMapper sysRoleMapper;

    private final RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper;

    private final FileInfoService fileInfoService;
    public SysUser getByUsername(String username) {
        return sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public Result<LoginInfo> login(LoginParam loginParam) {
        try {
            SysUser user = getByUsername(loginParam.getUsername());
            if (user == null) {
                throw new LoginException("账号不存在");
            }
            boolean isTrue = Md5Util.validPassword(loginParam.getPassword(), user.getPassword());
            if (!isTrue) {
                throw new LoginException("密码错误");
            }
            UserInfo userInfo = sysUserService.getUserInfo(user.getId());

            //存入redis
            String userId = userInfo.getId().toString();
            String key = String.format("userId:%s", userId);
            String jsonString = JSON.toJSONString(userInfo);
            redisTemplate.opsForValue().set(key, jsonString, 3, TimeUnit.HOURS);
            //生产token
            String token = JwtUtil.createToken(userId);
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setToken(token);
            loginInfo.setUserInfo(userInfo);
            return Result.success(loginInfo);
        } catch (NullPointerException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new LoginException("密码错误");
        }
    }


    @Override
    public Result<Object> register(RegisterReq req) {
        if (!req.getSmsCode().equals("123456")) {
            throw new BusinessException("验证码不正确");
        }
        if (sysUserMapper.exists(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, req.getUsername()))) {
            throw new BusinessException("用户名已经存在");
        }
        if (sysUserMapper.exists(new LambdaQueryWrapper<SysUser>().eq(SysUser::getMobile, req.getMobile()))) {
            throw new BusinessException("手机已经注册了");
        }
        try {
            String password = Md5Util.getEncryptedPwd(req.getPassword());
            //注册默认普通员工
            LambdaQueryWrapper<SysRole> wrapper =
                    new LambdaQueryWrapper<SysRole>()
                            .eq(SysRole::getRoleCode, "consumer");
            SysRole sysRole = sysRoleMapper.selectOne(wrapper);
            SysUser sysUser = SysUser.builder()
                    .username(req.getUsername())
                    .password(password)
                    .avatarId(req.getAvatarId())
                    .identityCode(req.getIdentityCode())
                    .displayName(req.getRealName())
                    .mobile(req.getMobile())
                    .roleId(sysRole.getId())
                    .build();
            sysUserMapper.insert(sysUser);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

    @Override
    public Result<String> modifyPassword(ModifyPasswordReq req) {
        if (!req.getSmsCode().equals("123456")) {
            throw new LoginException("验证码不正确");
        }
        LambdaQueryWrapper<SysUser> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,req.getMobile());
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        String password = null;
        try {
            password = Md5Util.getEncryptedPwd(req.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        sysUser.setPassword(password);
        sysUserMapper.updateById(sysUser);
        return Result.success(req.getPassword());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<UserInfo> modifyUserInfo(ModifyUserInfoReq req) {
        LambdaQueryWrapper<SysUser> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,req.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        sysUser.setMobile(req.getMobile());
        sysUser.setDisplayName(req.getRealName());
        sysUser.setIdentityCode(req.getIdentityCode());
        sysUser.setAvatarId(req.getAvatarId());
        sysUserMapper.updateById(sysUser);
        UserInfo userInfo = sysUserService.getUserInfo(sysUser.getId());
        FileInfo fileInfo = fileInfoService.getById(req.getAvatarId());
        userInfo.setAvatarUrl(fileInfo.getUrl());
        String key = String.format("userId:%s", sysUser.getId().toString());
        String userInfoJson = null;
        try {
            userInfoJson = objectMapper.writeValueAsString(userInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        redisTemplate.opsForValue().set(key, userInfoJson, 3, TimeUnit.HOURS);
        return Result.success(userInfo);
    }


}
