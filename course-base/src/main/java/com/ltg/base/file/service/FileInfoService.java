package com.ltg.base.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ltg.base.file.entity.FileInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p> ClassName: FileInfoService </p>
 * <p> Package: com.ltg.file.service </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/13 - 17:42
 * @Version: v1.0
 */
public interface FileInfoService extends IService<FileInfo> {
    FileInfo create(MultipartFile file);


    void show(HttpServletRequest request, HttpServletResponse response, String id);

    void download(HttpServletRequest request, HttpServletResponse response, String id);

    void downloadStatic(HttpServletRequest request, HttpServletResponse response, String staticKey);


}
