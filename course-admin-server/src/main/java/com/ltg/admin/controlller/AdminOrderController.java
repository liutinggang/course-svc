package com.ltg.admin.controlller;

import com.ltg.base.course.entity.CourseOrder;
import com.ltg.base.course.mapper.CourseOrderMapper;
import com.ltg.base.course.service.CourseOrderService;
import com.ltg.framework.util.http.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> ClassName: AdminOrderController </p>
 * <p> Package: com.ltg.admin.controlller </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/30 - 22:49
 * @Version: v1.0
 */
@RestController
@RequiredArgsConstructor
public class AdminOrderController {

    private final CourseOrderService courseOrderService;

    @DeleteMapping("/api/v1/admin/order/delete/{orderId}")
    @Transactional(rollbackFor = Exception.class)
    public Result delete(@PathVariable Long orderId){
        courseOrderService.removeById(orderId);
        return Result.success();

    }
}
