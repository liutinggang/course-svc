package com.ltg.base.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltg.base.sys.data.request.ModifyRoleReq;
import com.ltg.base.sys.entity.SysRole;
import com.ltg.base.sys.mapper.SysRoleMapper;
import com.ltg.base.sys.service.SysRoleService;
import com.ltg.framework.util.http.Result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> ClassName: SysRoleServiceImpl </p>
 * <p> Package: com.ltg.urban.domain.user.service.impl </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/13 - 9:44
 * @Version: v1.0
 */

@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<SysRole> createRole(ModifyRoleReq req) {
        //创建角色
        SysRole sysRole = SysRole.builder()
                .roleName(req.getRoleName())
                .roleCode(req.getRoleCode())
                .build();
        this.save(sysRole);
        return Result.success(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<SysRole> modify(Long roleId, ModifyRoleReq req) {
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        sysRole.setRoleName(req.getRoleName());
        sysRole.setRoleCode(req.getRoleCode());
        sysRoleMapper.updateById(sysRole);
        return Result.success(sysRole);
    }
}
