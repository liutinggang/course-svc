package com.ltg.base.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ltg.base.sys.data.request.ModifyUserRoleReq;
import com.ltg.base.sys.data.response.UserInfo;
import com.ltg.base.sys.data.response.UserPageResp;
import com.ltg.base.sys.entity.SysUser;
import com.ltg.framework.util.http.Result;
import com.ltg.framework.util.http.PageInfo;

import java.util.List;

/**
 * <p> ClassName: SysUserService </p>
 * <p> Package: com.ltg.urban.domain.sys.service </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/24 - 11:52
 * @Version: v1.0
 */
public interface SysUserService extends IService<SysUser> {
    PageInfo<UserPageResp> pageList(Page<UserPageResp> page, String keyword, String roleId);

    Result<UserInfo> getUserDetail(Long userId);

    UserInfo getUserInfo(Long userId);

    Result<UserInfo> modifyUserRole(ModifyUserRoleReq req);


    Result<List<SysUser>> getUsers(Integer position);

}
