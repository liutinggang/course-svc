package com.ltg.base.course.data.vo;

import com.ltg.base.course.entity.CourseOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p> ClassName: CourseOrderVo </p>
 * <p> Package: com.ltg.course.domain.course.data.vo </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 21:56
 * @Version: v1.0
 */
@Data
public class CourseOrderVo extends CourseOrder {
    @Schema(description = "课程名称", type = "string", example = "")
    private String courseName;
}
