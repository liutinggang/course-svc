package com.ltg.base.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ltg.base.course.data.dto.CreateCourseDto;
import com.ltg.base.course.data.dto.UpdateCourseDto;
import com.ltg.base.course.data.vo.CourseVo;
import com.ltg.base.course.entity.Course;
import com.ltg.framework.util.http.PageInfo;

/**
 * <p> ClassName: CourseService </p>
 * <p> Package: com.ltg.course.domain.course.service </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 20:55
 * @Version: v1.0
 */
public interface CourseService extends IService<Course> {
    Course createCourse(CreateCourseDto courseDto);

    PageInfo<CourseVo> pageList(Page<CourseVo> objectPage, Integer status, String keyword);


    CourseVo detail(Long courseId);

    Course updateCourse(UpdateCourseDto courseDto);

    PageInfo<CourseVo> consumerList(Page<CourseVo> objectPage, Integer status, String keyword);


    void delete(Long courseId);
}
