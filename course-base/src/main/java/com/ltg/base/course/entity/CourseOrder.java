package com.ltg.base.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ltg.framework.mybatis.entities.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> ClassName: CourseOrder </p>
 * <p> Package: com.ltg.course.domain.course.entity </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 21:08
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("b_course_order")
public class CourseOrder extends BaseEntity {
    @Schema(description = "用户id", type = "string", example = "")
    private Long userId;
    @Schema(description = "订单状态", type = "string", example = "")
    private Integer orderStatus;
    @Schema(description = "课程id", type = "string", example = "")
    private Long courseId;
}
