package com.ltg.order.controller;

/**
 * <p> ClassName: OrderContorller </p>
 * <p> Package: com.ltg.course.domain.course.controller </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 21:43
 * @Version: v1.0
 */

import com.ltg.base.course.data.dto.ChooseCourseDto;
import com.ltg.base.course.data.vo.CourseOrderDetailVo;
import com.ltg.base.course.data.vo.CourseOrderVo;
import com.ltg.base.course.entity.CourseOrder;
import com.ltg.base.course.service.CourseOrderService;
import com.ltg.framework.annotation.DescribePage4Swagger;
import com.ltg.framework.util.http.PageInfo;
import com.ltg.framework.util.http.PageRequest;
import com.ltg.framework.util.http.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "订单模块:用户端")
@RequiredArgsConstructor
public class ConsumerOrderController {

    private final CourseOrderService courseOrderService;


    @PutMapping("/api/v1/consumer/order/choose")
    @Operation(summary = "选课")
    public Result<CourseOrder> chooseCourse(@RequestBody ChooseCourseDto chooseCourseDto) {
        CourseOrder courseOrder = courseOrderService.chooseCourse(chooseCourseDto);
        return Result.success(courseOrder);
    }

    @GetMapping("/api/v1/consumer/order/list")
    @DescribePage4Swagger
    @Operation(summary = "我的选课")
    public PageInfo<CourseOrderVo> pageList(HttpServletRequest httpServletRequest,
                                                    @RequestParam(required = false) Integer status,
                                                    @RequestParam(required = false) String keyword) {
        return courseOrderService.myOrderList(PageRequest.buildFromRequest(httpServletRequest), status, keyword);
    }



}
