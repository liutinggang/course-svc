package com.ltg.framework.util.http;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.ltg.framework.constants.SecurityRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * <p> ClassName: RequestUtils </p>
 * <p> Package: com.ltg.framework.util.http </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/12 - 20:26
 * @Version: v1.0
 */
public class RequestUtils {
    public static Boolean isGet(HttpServletRequest request) {
        return HttpMethod.GET.toString().equalsIgnoreCase(request.getMethod());
    }

    public static Boolean isPost(HttpServletRequest request) {
        return HttpMethod.POST.toString().equalsIgnoreCase(request.getMethod());
    }

    public static Boolean isPut(HttpServletRequest request) {
        return HttpMethod.PUT.toString().equalsIgnoreCase(request.getMethod());
    }

    public static Boolean isDelete(HttpServletRequest request) {
        return HttpMethod.DELETE.toString().equalsIgnoreCase(request.getMethod());
    }

    public static Boolean isOptions(HttpServletRequest request) {
        return HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
    }

    public static Boolean isJsonRequest(HttpServletRequest request) {
        return StrUtil.containsIgnoreCase(request.getContentType(), "application/json");
    }

    public static boolean isMultipart(HttpServletRequest request) {
        return StrUtil.startWithIgnoreCase(request.getContentType(), "multipart/");
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityRequestHeaders.REQUEST_AUTHORIZATION);
        if (StrUtil.isBlank(token)) {
            return null;
        }

        if (StrUtil.startWith(token, "Bearer ")) {
            token = StrUtil.subSuf(token, 7);
        }

        return token;
    }

    public static Integer getPage(HttpServletRequest request) {
        int page = 1;
        String[] pageKeys = new String[]{"page", "pageNum", "page_num", "page_index", "pageIndex"};
        for (String pageKey : pageKeys) {
            if (has(request, pageKey)) {
                page = getInteger(request, pageKey);
                page = page <= 0 ? 1 : page;
            }
        }
        return page;
    }

    public static Integer getPageSize(HttpServletRequest request) {
        int pageSize = 10;
        String[] pageKeys = new String[]{"pageSize", "page_size"};
        for (String pageKey : pageKeys) {
            if (has(request, pageKey)) {
                pageSize = getInteger(request, pageKey);
                return pageSize <= 0 ? 10 : pageSize;
            }
        }
        return pageSize;
    }


    public static boolean has(HttpServletRequest request, String key) {
        return request.getParameter(key) != null;
    }

    public static String getString(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    public static int getInteger(HttpServletRequest request, String key) {
        return Integer.parseInt(request.getParameter(key));
    }

    public static Dict getQueryDict(String queryString) {
        Dict queryMap = Dict.create();
        String query = queryString;
        if (StrUtil.isBlank(query)) {
            return queryMap;
        }
        if (query.startsWith("?")) {
            query = query.substring(1);
        }
        String[] kvList = query.split("[&]");
        for (String kv : kvList) {
            if (StrUtil.isNotBlank(kv)) {
                String[] kAndV = kv.split("[=]");
                if (kAndV.length >= 2) {
                    //修改过
                    try {
                        queryMap.put(kAndV[0], URLDecoder.decode(kAndV[1], StandardCharsets.UTF_8.toString()));
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    queryMap.put(kAndV[0], null);
                }
            }
        }
        return queryMap;
    }
}
