package com.ltg.order.controller;

import com.ltg.base.course.data.vo.CourseOrderDetailVo;
import com.ltg.base.course.data.vo.CourseOrderVo;
import com.ltg.base.course.service.CourseOrderService;
import com.ltg.framework.annotation.DescribePage4Swagger;
import com.ltg.framework.util.http.PageInfo;
import com.ltg.framework.util.http.PageRequest;
import com.ltg.framework.util.http.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> ClassName: OrderController </p>
 * <p> Package: com.ltg.order.controller </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/29 - 21:13
 * @Version: v1.0
 */

@RestController
@Tag(name = "订单模块:用户端")
@RequiredArgsConstructor
public class OrderController {
    private final CourseOrderService courseOrderService;


    @GetMapping("/api/v1/order/list")
    @DescribePage4Swagger
    @Operation(summary = "我的选课")
    public PageInfo<CourseOrderVo> pageList(HttpServletRequest httpServletRequest,
                                            @RequestParam(required = false) Integer status,
                                            @RequestParam(required = false) String keyword) {
        return courseOrderService.pageList(PageRequest.buildFromRequest(httpServletRequest), status, keyword);
    }



    @GetMapping("/api/v1/order/detail/{orderId}")
    @Operation(summary = "订单详情")
    public Result<CourseOrderDetailVo> orderDetail(@PathVariable Long orderId) {
        CourseOrderDetailVo courseOrderDetailVo = courseOrderService.orderDetail(orderId);
        return Result.success(courseOrderDetailVo);
    }
}
