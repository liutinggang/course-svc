package com.ltg.course.controller;

import com.ltg.base.course.data.vo.CourseVo;
import com.ltg.base.course.service.CourseService;
import com.ltg.framework.annotation.DescribePage4Swagger;
import com.ltg.framework.util.http.PageInfo;
import com.ltg.framework.util.http.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p> ClassName: ConsumerOrderController </p>
 * <p> Package: com.ltg.course.domain.course.controller </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 22:12
 * @Version: v1.0
 */

@RestController
@Tag(name = "订单模块:用户端")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "分页查询:OK")
    @GetMapping("/api/v1/consumer/course/list")
    @DescribePage4Swagger
    public PageInfo<CourseVo> page(HttpServletRequest httpServletRequest,
                                   @RequestParam(required = false)Integer status,
                                   @RequestParam(required = false) String keyword) {
        return courseService.consumerList(PageRequest.buildFromRequest(httpServletRequest),status,keyword);
    }

    @GetMapping("/api/v1/consumer/course/detail/{courseId}")
    @Operation(summary = "课程详情")
    public CourseVo detail(@PathVariable Long courseId){
        return courseService.detail(courseId);
    }


}
