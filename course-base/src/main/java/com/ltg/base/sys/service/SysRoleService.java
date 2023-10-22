package com.ltg.base.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ltg.base.sys.data.request.ModifyRoleReq;
import com.ltg.base.sys.entity.SysRole;
import com.ltg.framework.util.http.Result;

/**
 * <p> ClassName: SysRoleService </p>
 * <p> Package: com.ltg.urban.domain.user.service </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/13 - 9:44
 * @Version: v1.0
 */
public interface SysRoleService extends IService<SysRole> {

    Result<SysRole> createRole(ModifyRoleReq req);


    Result<SysRole> modify(Long roleId, ModifyRoleReq req);

    
}
