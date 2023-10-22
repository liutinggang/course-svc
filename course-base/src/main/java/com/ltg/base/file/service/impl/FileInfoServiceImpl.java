package com.ltg.base.file.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltg.base.file.entity.FileInfo;
import com.ltg.framework.util.SnowflakeIdGenerator;
import com.ltg.base.file.mapper.FileInfoMapper;
import com.ltg.base.file.service.FileInfoService;
import com.ltg.framework.properties.StorageProperties;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <p> ClassName: FileInfoServiceImpl </p>
 * <p> Package: com.ltg.file.service.impl </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/13 - 17:42
 * @Version: v1.0
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    private final StorageProperties storageProperties;

    @Override
    public FileInfo create(MultipartFile file) {
        // 检查文件是否为空
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        long fileSize = file.getSize();
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(123456L, 123456L);
        Long mediaId = snowflakeIdGenerator.generateId();
        String domain = StrUtil.removeSuffix(storageProperties.getDomain(), "/");
        String localPath = storageProperties.getLocalPrefix();
        String contentType = file.getContentType();
        String originName = file.getOriginalFilename();
        String extension = FileNameUtil.getSuffix(originName);
        if (StringUtils.isBlank(contentType)) {
            contentType = FileUtil.getMimeType(originName);
        }
        String filename = mediaId + "." + extension;
        try {
            FileUtil.mkdir(localPath);
            IoUtil.copy(file.getInputStream(), FileUtil.getOutputStream(localPath + File.separator + filename));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("保存文件异常！请稍后再试~");
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(mediaId);
        fileInfo.setOriginName(originName);
        fileInfo.setContentType(contentType);
        fileInfo.setExtName(extension);
        fileInfo.setSize(String.valueOf(fileSize / 1024));
        fileInfo.setPath(filename);
        fileInfo.setUrl(domain + "/api/v1/file/" + mediaId);
        fileInfo.setDownloadUrl(domain + "/api/v1/file/" + mediaId + "/download");
        this.save(fileInfo);
        FileInfo mediaInfo1 = this.getById(mediaId);
        return mediaInfo1;
    }

    @Override
    public void show(HttpServletRequest request, HttpServletResponse response, String id) {
        FileInfo mediaInfo = this.getById(id);
        String absolutePath = storageProperties.getLocalPrefix() + File.separator + mediaInfo.getPath();
        File file = FileUtil.newFile(absolutePath);
        if (!file.exists()) {
            throw new RuntimeException();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
             ServletOutputStream outputStream = response.getOutputStream();) {
            response.setIntHeader("Expires", 0);
            response.setContentLength(inputStream.available());
            response.setHeader("Content-Type", mediaInfo.getContentType());
            IoUtil.copy(inputStream, outputStream);
            response.flushBuffer();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, String id) {
        FileInfo mediaInfo = this.getById(id);
        String absolutePath = storageProperties.getLocalPrefix() + File.separator + mediaInfo.getPath();
        File file = FileUtil.newFile(absolutePath);
        if (!file.exists()) {
            throw new RuntimeException();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
             ServletOutputStream outputStream = response.getOutputStream();) {
            response.setIntHeader("Expires", 0);
            response.setContentLength(inputStream.available());
            response.setHeader("Content-Type", mediaInfo.getContentType());
            response.setHeader("Content-Disposition", "attachment; filename=" + encodeChineseDownloadFileName(request, mediaInfo.getOriginName()));
            IoUtil.copy(inputStream, outputStream);
            response.flushBuffer();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void downloadStatic(HttpServletRequest request, HttpServletResponse response, String staticKey) {
    }

    /**
     * 编码格式转换
     *
     * @param request   请求
     * @param pFileName 文件名称
     * @return String
     */
    private String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName) throws UnsupportedEncodingException {
        String filename;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent) {
            if (agent.contains("Firefox")) {
                filename = "=?UTF-8?B?" + (new String(Base64.getEncoder().encode(pFileName.getBytes(StandardCharsets.UTF_8)))) + "?=";
            } else if (agent.contains("Chrome")) {
                filename = new String(pFileName.getBytes(), StandardCharsets.ISO_8859_1);
            } else {//IE7+
                filename = URLEncoder.encode(pFileName, StandardCharsets.UTF_8.name());
            }
        } else {
            filename = pFileName;
        }
        return filename;
    }
}
