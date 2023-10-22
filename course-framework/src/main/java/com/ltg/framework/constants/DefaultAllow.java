package com.ltg.framework.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * nekoimi  2022/1/12 20:08
 */
public interface DefaultAllow {
    // 默认开放的路由
    List<String> ALLOW = new ArrayList<String>() {{
        add("/error");
        add("/favicon.ico");
        add("/doc.html");
        add("/v2/api-docs");
        add("/swagger-resources");
        add("/swagger-resources/**");
    }};
}
