package com.ltg.base.course.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * <p> ClassName: CreateCourseDto </p>
 * <p> Package: com.ltg.course.domain.course.data.dto </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 20:59
 * @Version: v1.0
 */
@Data
public class CreateCourseDto {
    @Schema(description = "课程名称", type = "string", example = "")
    private String courseName;
    @Schema(description = "课程价格", type = "string", example = "")
    private String price;
    @Schema(description= "价格类型", type = "number", example = "1,2,3")
    private Integer priceType;
    @Schema(description = "价格类型", type = "string", example = "1,2,3")
    @NotNull
    private List<String> fileIds;

}
