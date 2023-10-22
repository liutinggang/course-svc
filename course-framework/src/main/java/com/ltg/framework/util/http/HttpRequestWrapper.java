package com.ltg.framework.util.http;


import cn.hutool.core.lang.Dict;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p> ClassName: HttpRequestWrapper </p>
 * <p> Package: com.ltg.framework.util.http </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/12 - 20:25
 * @Version: v1.0
 */

@Slf4j
public class HttpRequestWrapper extends HttpServletRequestWrapper {
    /**
     * 保存请求体原始数据 以供后续读取请求体
     */
    private final byte[] body;

    public HttpRequestWrapper(HttpServletRequest request) {
        super(request);
        byte[] bytes = new byte[0];
        try {
            bytes = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            log.error("Read request body error! " + e.getMessage());
        }
        body = bytes;
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return ObjectUtils.isEmpty(body) ? null : new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private static Boolean isNotIpAddress(String ipAddress) {
        return (StrUtil.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress));
    }

    @Override
    public String getRemoteAddr() {
        String ipAddress = getHeader("X-Forwarded-For");
        if (isNotIpAddress(ipAddress)) {
            ipAddress = getHeader("Proxy-Client-IP");
        }
        if (isNotIpAddress(ipAddress)) {
            ipAddress = getHeader("WL-Proxy-Client-IP");
        }
        if (isNotIpAddress(ipAddress)) {
            ipAddress = super.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();

                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException ex) {
                    log.error(ex.getMessage());

                    ex.printStackTrace();
                }
            }
        }
        if (!isNotIpAddress(ipAddress)) {
            if (ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        }
        return ipAddress;
    }

    public Dict getQueryDict() {
        return RequestUtils.getQueryDict(getQueryString());
    }
}
