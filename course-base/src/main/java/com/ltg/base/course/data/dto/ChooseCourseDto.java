package com.ltg.base.course.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p> ClassName: ChooseCourse </p>
 * <p> Package: com.ltg.course.domain.course.data.dto </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 21:41
 * @Version: v1.0
 */
@Data
public class ChooseCourseDto {
    @Schema(description = "课程id", type = "string", example = "")
    private Long courseId;

}
