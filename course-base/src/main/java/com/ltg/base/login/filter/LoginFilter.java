package com.ltg.base.login.filter;



import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltg.base.sys.data.response.CurrentUserHolder;
import com.ltg.base.sys.data.response.UserInfo;
import com.ltg.framework.constants.Constant;
import com.ltg.framework.properties.LoginProperties;
import com.ltg.framework.util.JwtUtil;
import com.ltg.framework.util.http.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p> ClassName: LoginFilter </p>
 * <p> Package: com.ltg.urban.filter </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 20:20
 * @Version: v1.0
 */

@Slf4j
@Component
public class LoginFilter implements Filter , Ordered {
    private final RedisTemplate<String, Object> redisTemplate;
    private final LoginProperties loginProperties;

    private final ObjectMapper objectMapper;

    // 2.2 创建路径匹配器对象
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();


    public LoginFilter(RedisTemplate<String, Object> redisTemplate, LoginProperties loginProperties, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.loginProperties = loginProperties;
        this.objectMapper = objectMapper;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 过滤路径
        String requestURI = httpServletRequest.getRequestURI();
        boolean contains = checkUrl(loginProperties.getFilterExcludeUrl(), requestURI);
        if (!contains) {
            // 获取token
            String token = httpServletRequest.getHeader(Constant.TOKEN_HEADER_NAME.getKey());
            if (StringUtils.isBlank(token)) {
                returnNoLogin(servletResponse);
                return;
            }
            //解码
            Jws<Claims> jws = JwtUtil.parseJWT(token);
            Map<String, Object> body = jws.getBody();
            // 从redis中拿token对应user
            String key = String.format("%s:%s", Constant.REDIS_USER_PREFIX.getKey(), body.get("id"));
            String json = (String) redisTemplate.opsForValue().get(key);
            UserInfo userInfo = objectMapper.readValue(json, UserInfo.class);
            if (userInfo == null) {
                returnNoLogin(servletResponse);
                return;
            }
            CurrentUserHolder.setCurrentUser(userInfo);
            // token续期
            redisTemplate.expire(Constant.REDIS_USER_PREFIX + token, 30, TimeUnit.MINUTES);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     *
     * @param urls a
     * @param requestURI a
     * @return 判断某个请求是否在不登录的时候就可以放行
     */
    private boolean checkUrl(List<String> urls, String requestURI) {
        for (String url : urls) {
            // 匹配 本次请求的requestURI  是否符合 url
            if (antPathMatcher.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 返回未登录的错误信息
     *
     * @param response ServletResponse
     */
    private void returnNoLogin(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        // 设置返回401 和响应编码
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("Application/json;charset=utf-8");
        // 构造返回响应体
        Result<String> result = Result.error(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        String resultString = JSONUtil.toJsonStr(result);
        outputStream.write(resultString.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void destroy() {
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
