package com.ltg.base.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ltg.framework.mybatis.entities.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> ClassName: Course </p>
 * <p> Package: com.ltg.course.domain.course.entity </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 20:54
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("b_course")
public class Course extends BaseEntity {
    @Schema(description = "课程名称", type = "string", example = "")
    private String courseName;
    @Schema(description = "课程状态 0正常 1禁用", type = "number", example = "")
    private Integer courseStatus;
    @Schema(description = "课程价格", type = "string", example = "")
    private String price;
    @Schema(description = "价格类型", type = "number", example = "1,2,3")
    private Integer priceType;
    @Schema(description = "文件", type = "string", example = "1,2,3")
    private String fileIds;
}
