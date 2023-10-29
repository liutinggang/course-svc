package com.ltg.base.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltg.base.course.data.dto.ChooseCourseDto;
import com.ltg.base.course.data.vo.CourseOrderDetailVo;
import com.ltg.base.course.data.vo.CourseOrderVo;
import com.ltg.base.course.data.vo.CourseVo;
import com.ltg.base.course.entity.CourseOrder;
import com.ltg.base.course.mapper.CourseOrderMapper;
import com.ltg.base.course.service.CourseService;
import com.ltg.base.course.service.CourseOrderService;
import com.ltg.framework.error.exception.BaseException;
import com.ltg.framework.util.http.PageInfo;
import com.ltg.base.sys.data.response.CurrentUserHolder;
import com.ltg.base.sys.data.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p> ClassName: CourseOrderServiceImpl </p>
 * <p> Package: com.ltg.course.domain.course.service.impl </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 21:45
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
public class CourseOrderServiceImpl extends ServiceImpl<CourseOrderMapper, CourseOrder> implements CourseOrderService {
    private final CourseOrderMapper courseOrderMapper;
    private final CourseService courseService;

    @Override
    public CourseOrder chooseCourse(ChooseCourseDto chooseCourseDto) {
        UserInfo currentUser = CurrentUserHolder.getCurrentUser();
        Long userId = currentUser.getId();
        LambdaQueryWrapper<CourseOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseOrder::getCourseId, chooseCourseDto.getCourseId());
        wrapper.eq(CourseOrder::getUserId, userId);
        boolean exists = this.exists(wrapper);
        if (exists) {
            throw new BaseException("改用户已经选过此科目了!");
        }
        CourseOrder courseOrder = CourseOrder.builder()
                .courseId(chooseCourseDto.getCourseId())
                .userId(userId)
                .orderStatus(0)
                .build();
        this.save(courseOrder);
        return courseOrder;
    }

    @Override
    public PageInfo<CourseOrderVo> pageList(Page<CourseOrderVo> objectPage, Integer status, String keyword) {
        Page<CourseOrderVo> courseOrderVoIPage = courseOrderMapper.pageList(objectPage, status, keyword);
        return new PageInfo<>(courseOrderVoIPage);

    }


    @Override
    public CourseOrderDetailVo orderDetail(Long orderId) {
        CourseOrderDetailVo courseOrderDetailVo = new CourseOrderDetailVo();
        CourseOrder courseOrder = this.getById(orderId);
        BeanUtils.copyProperties(courseOrder, courseOrderDetailVo);
        CourseVo detail = courseService.detail(courseOrder.getCourseId());
        courseOrderDetailVo.setCourse(detail);
        return courseOrderDetailVo;

    }

    @Override
    public PageInfo<CourseOrderVo> myOrderList(Page<CourseOrderVo> objectPage, Integer status, String keyword) {
        Long id = CurrentUserHolder.getCurrentUser().getId();
        Page<CourseOrderVo> courseOrderVoIPage = courseOrderMapper.myOrderList(objectPage,id, status, keyword);
        return new PageInfo<>(courseOrderVoIPage);
    }
}
