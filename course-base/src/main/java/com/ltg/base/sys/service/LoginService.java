package com.ltg.base.sys.service;


import com.ltg.base.sys.data.param.LoginParam;
import com.ltg.base.sys.data.request.ModifyPasswordReq;
import com.ltg.base.sys.data.request.ModifyUserInfoReq;
import com.ltg.base.sys.data.request.RegisterReq;
import com.ltg.base.sys.data.response.LoginInfo;
import com.ltg.base.sys.data.response.UserInfo;
import com.ltg.framework.util.http.Result;

/**
 * <p> ClassName: LoginService </p>
 * <p> Package: com.ltg.urban.domain.user.service </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 19:33
 * @Version: v1.0
 */
public interface LoginService {
    Result<LoginInfo> login(LoginParam loginParam);



    Result<Object> register(RegisterReq req);

    Result<String> modifyPassword(ModifyPasswordReq req);


    Result<UserInfo> modifyUserInfo(ModifyUserInfoReq req);


}
