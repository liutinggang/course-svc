package com.ltg.framework.util.http;


import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class PageRequest {
    private static final String PAGE = "pageNumber";
    private static final String PAGE_SIZE = "pageSize";
    private static final String SORT = "sort";
    private static final String ORDER = "order";
    private static final String ORDER_ASC = "asc";

    public static <T> Page<T> buildFromRequest(HttpServletRequest request) {
        HttpRequestWrapper requestWrapper;
        if (request instanceof HttpRequestWrapper) {
            requestWrapper = (HttpRequestWrapper) request;
        } else {
            requestWrapper = new HttpRequestWrapper(request);
        }
        Dict reqParameters = requestWrapper.getQueryDict();
        if (log.isDebugEnabled()) {
            reqParameters.forEach((key, value) -> log.debug("{} -> {}", key, value));
        }
        Page<T> resultPage = new Page<>();
        int page = Optional.ofNullable(reqParameters.getInt(PAGE)).orElse(1);
        int pageSize = Optional.ofNullable(reqParameters.getInt(PAGE_SIZE)).orElse(10);
        String sort = reqParameters.getStr(SORT);
        String order = Optional.ofNullable(reqParameters.getStr(ORDER)).orElse(ORDER_ASC);
        resultPage.setCurrent(page);
        resultPage.setSize(pageSize);
        if (StrUtil.isNotBlank(sort)) {
            if (ORDER_ASC.equalsIgnoreCase(order)) {
                resultPage.addOrder(OrderItem.asc(sort));
            } else {
                resultPage.addOrder(OrderItem.desc(sort));
            }
        }
        return resultPage;
    }


}

