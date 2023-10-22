package com.ltg.base.course.data.vo;

import com.ltg.base.course.entity.CourseOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p> ClassName: CourseOrderDetailVo </p>
 * <p> Package: com.ltg.course.domain.course.data.vo </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 22:06
 * @Version: v1.0
 */
@Data
public class CourseOrderDetailVo extends CourseOrder {
    @Schema(description = "课程", type = "string", example = "")
    private CourseVo course;
}
