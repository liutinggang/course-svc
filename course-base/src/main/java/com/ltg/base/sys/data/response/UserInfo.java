package com.ltg.base.sys.data.response;

import com.ltg.base.sys.entity.SysRole;
import com.ltg.base.sys.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <p> ClassName: UserInfo </p>
 * <p> Package: com.ltg.urban.domain.user.data.response </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 21:20
 * @Version: v1.0
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends SysUser {

    private SysRole sysRole;



}
