package com.ltg.login.controller;

import com.ltg.base.file.entity.FileInfo;
import com.ltg.base.file.service.FileInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p> ClassName: FileController </p>
 * <p> Package: com.ltg.file.controller </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/13 - 17:43
 * @Version: v1.0
 */


@RestController
@Tag(name = "文件")
public class FileController {


    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping("/api/v1/file/upload")
    @Operation(summary = "上传")
    public FileInfo upload(@RequestPart("file") MultipartFile file) {
        return fileInfoService.create(file);
    }

    @GetMapping("/api/v1/file/{id}")
    @Operation(summary = "查看")
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
        fileInfoService.show(request, response, id);
    }

    @GetMapping("/api/v1/file/{id}/download")
    @Operation(summary = "下载")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
        fileInfoService.download(request, response, id);
    }

    @GetMapping("/api/v1/file/static/{staticKey}/download")
    @Operation(summary = "下载静态文件")
    public void downloadStatic(HttpServletRequest request, HttpServletResponse response, @PathVariable("staticKey") String staticKey) {
        fileInfoService.downloadStatic(request, response, staticKey);
    }


}
