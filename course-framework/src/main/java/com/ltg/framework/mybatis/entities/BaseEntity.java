package com.ltg.framework.mybatis.entities;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p> ClassName: BaseEntity </p>
 * <p> Package: com.ltg.framework.entities </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/1/4 - 12:05
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "基础实体类")
public class BaseEntity implements Serializable {

    public static final String PROPERTY_CREATED_TIME = "createTime";
    public static final String PROPERTY_UPDATE_TIME = "updateTime";
    public static final String PROPERTY_DELETED = "deleted";

    @Schema(description = "主键id")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @JsonIgnore
    @Schema(description = "创建时间", defaultValue = "1")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


    @JsonIgnore
    @TableLogic(value = "0", delval = "1")
    @Schema(description = "软删除字段；1 - 已删除，0 - 未删除",defaultValue = "0")
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
