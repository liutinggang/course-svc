package com.ltg.base.sys.data.response;

import cn.hutool.core.thread.threadlocal.NamedInheritableThreadLocal;

/**
 * <p> ClassName: CurrentUserHolder </p>
 * <p> Package: com.ltg.urban.domain.user.data.response </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/12 - 20:01
 * @Version: v1.0
 */
public class CurrentUserHolder {

    private static final ThreadLocal<UserInfo> currUserHolder = new NamedInheritableThreadLocal<>("Current User Thread Local");

    public static void resetCurrentUser() {
        currUserHolder.remove();
    }

    public static void setCurrentUser(UserInfo currentUser) {
        currUserHolder.set(currentUser);
    }

    public static UserInfo getCurrentUser(){
        return currUserHolder.get();
    }
}
