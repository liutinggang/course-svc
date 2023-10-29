package com.ltg.base.course.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ltg.base.course.data.dto.ChooseCourseDto;
import com.ltg.base.course.data.vo.CourseOrderDetailVo;
import com.ltg.base.course.data.vo.CourseOrderVo;
import com.ltg.base.course.entity.CourseOrder;
import com.ltg.framework.util.http.PageInfo;

/**
 * <p> ClassName: CourseOrderService </p>
 * <p> Package: com.ltg.course.domain.course.service </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 21:44
 * @Version: v1.0
 */
public interface CourseOrderService extends IService<CourseOrder> {
    CourseOrder chooseCourse(ChooseCourseDto chooseCourseDto);


    PageInfo<CourseOrderVo> pageList(Page<CourseOrderVo> objectPage, Integer status, String keyword);


    CourseOrderDetailVo orderDetail(Long orderId);


    PageInfo<CourseOrderVo> myOrderList(Page<CourseOrderVo> objectPage, Integer status, String keyword);


}
