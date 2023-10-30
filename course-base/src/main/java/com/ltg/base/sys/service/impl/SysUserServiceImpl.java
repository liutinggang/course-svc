package com.ltg.base.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltg.base.file.entity.FileInfo;
import com.ltg.base.file.service.FileInfoService;
import com.ltg.base.sys.data.request.ModifyUserRoleReq;
import com.ltg.base.sys.data.response.UserInfo;
import com.ltg.base.sys.data.response.UserPageResp;
import com.ltg.base.sys.entity.SysRole;
import com.ltg.base.sys.entity.SysUser;
import com.ltg.base.sys.mapper.SysRoleMapper;
import com.ltg.base.sys.mapper.SysUserMapper;
import com.ltg.base.sys.service.SysUserService;
import com.ltg.framework.properties.StorageProperties;
import com.ltg.framework.util.http.PageInfo;
import com.ltg.framework.util.http.Result;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p> ClassName: SysUserServiceImpl </p>
 * <p> Package: com.ltg.urban.domain.sys.service.impl </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/24 - 11:52
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final FileInfoService fileInfoService;



    @Override
    public PageInfo<UserPageResp> pageList(Page<UserPageResp> page, String keyword, String roleId) {
        Page<UserPageResp> sysUserPage = sysUserMapper.pageList(page,keyword,roleId);
        return new PageInfo<>(sysUserPage);
    }

    @Override
    public Result<UserInfo> getUserDetail(Long userId) {
        return Result.success(this.getUserInfo(userId));
    }

    @Override
    public UserInfo getUserInfo(Long userId) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        SysRole sysRole = sysRoleMapper.selectById(sysUser.getRoleId());
        sysUser.setAvatarId(sysUser.getAvatarId());
        FileInfo fileInfo = fileInfoService.getById(sysUser.getAvatarId());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(sysUser,userInfo);
        if(Objects.nonNull(fileInfo)){
            userInfo.setAvatarUrl(fileInfo.getUrl());
        }
        userInfo.setSysRole(sysRole);
        return userInfo;
    }


    @Override
    public Result<UserInfo> modifyUserRole(ModifyUserRoleReq req) {
        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SysUser::getId, req.getUserId());
        wrapper.set(SysUser::getRoleId, req.getRoleId());
        sysUserMapper.update(null, wrapper);
        UserInfo userInfo = getUserInfo(req.getUserId());
        String key = String.format("userId:%s", req.getUserId().toString());
        String userInfoJson = null;
        try {
            userInfoJson = objectMapper.writeValueAsString(userInfo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        redisTemplate.opsForValue().set(key, userInfoJson, 3, TimeUnit.HOURS);
        return Result.success(userInfo);
    }

    @Override
    public Result<List<SysUser>> getUsers(Integer position) {
        return Result.success(sysUserMapper.selectUsers(position));
    }
}
