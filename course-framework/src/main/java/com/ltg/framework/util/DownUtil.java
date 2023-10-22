package com.ltg.framework.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p> ClassName: DownUtil </p>
 * <p> Package: com.ltg.framework.util </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/1/31 - 21:54
 * @Version: v1.0
 */
public class DownUtil {
    public static void download(HttpServletResponse response, HttpServletRequest request, String fileName, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename="+ encodingFileName(fileName));
        response.setHeader("Connection", "close");
        response.setHeader("Content-Type", MediaType.APPLICATION_PDF_VALUE);

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
    }

    public static String encodingFileName(String fileName) {
        String encodeFileName = fileName;
        try {
            encodeFileName = URLEncoder.encode(fileName, "UTF-8");
            encodeFileName = StringUtils.replace(encodeFileName, "+", "");
            encodeFileName = StringUtils.replace(encodeFileName, "%20", "");
            encodeFileName = StringUtils.replace(encodeFileName, "%28", "(");
            encodeFileName = StringUtils.replace(encodeFileName, "%29", ")");
        } catch (UnsupportedEncodingException var3) {

        }

        return encodeFileName;
    }
}
