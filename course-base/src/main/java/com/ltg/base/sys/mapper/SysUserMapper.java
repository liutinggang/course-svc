package com.ltg.base.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ltg.base.sys.data.response.UserPageResp;
import com.ltg.base.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> ClassName: UserMapper </p>
 * <p> Package: com.ltg.urban.user.mapper </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 18:20
 * @Version: v1.0
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser selectByUsername(@Param("username") String username);


    Page<UserPageResp> pageList(Page<UserPageResp> page,
                                @Param("keyword")String keyword,
                                @Param("roleId")String roleId);


    List<SysUser> selectUsers(@Param("position") Integer position);

}
