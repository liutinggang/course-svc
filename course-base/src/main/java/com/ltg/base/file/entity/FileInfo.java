package com.ltg.base.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ltg.framework.mybatis.entities.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> ClassName: FileInfo </p>
 * <p> Package: com.ltg.file.entity </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/13 - 17:39
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_file_info")
public class FileInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // 默认本地
    public static final String FIELD_DRIVE = "drive";
    // 文件原始名称, 带后缀
    public static final String FIELD_ORIGIN_NAME = "origin_name";
    // 文件后缀名称 不要[.]符号
    public static final String FIELD_EXT_NAME = "ext_name";
    // 文件MimeType
    public static final String FIELD_CONTENT_TYPE = "content_type";
    // 文件大小 单位 kb
    public static final String FIELD_SIZE = "size";
    // 文件存放的相对路径
    public static final String FIELD_PATH = "path";
    // 文件对外访问地址
    public static final String FIELD_URL = "url";
    // 文件对外下载地址
    public static final String FIELD_DOWNLOAD_URL = "download_url";

    @TableField
    @Schema(name = "默认本地")
    private String drive;
    @TableField
    @Schema(name = "文件原始名称, 带后缀")
    private String originName;
    @TableField
    @Schema(name = "文件后缀名称 不要[.]符号")
    private String extName;
    @TableField
    @Schema(name = "文件MimeType")
    private String contentType;
    @TableField
    @Schema(name = "文件大小 单位 kb")
    private String size;
    @TableField
    @Schema(name = "文件存放的相对路径")
    private String path;
    @TableField
    @Schema(name = "文件对外访问地址")
    private String url;
    @TableField
    @Schema(name = "文件对外下载地址")
    private String downloadUrl;

}
