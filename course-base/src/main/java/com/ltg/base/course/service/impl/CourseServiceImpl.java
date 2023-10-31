package com.ltg.base.course.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ltg.base.course.data.dto.CreateCourseDto;
import com.ltg.base.course.data.dto.UpdateCourseDto;
import com.ltg.base.course.data.vo.CourseVo;
import com.ltg.base.course.entity.Course;
import com.ltg.base.course.entity.CourseOrder;
import com.ltg.base.course.mapper.CourseOrderMapper;
import com.ltg.base.file.entity.FileInfo;
import com.ltg.base.course.mapper.CourseMapper;
import com.ltg.base.course.service.CourseService;
import com.ltg.base.file.mapper.FileInfoMapper;
import com.ltg.base.sys.data.response.CurrentUserHolder;
import com.ltg.base.sys.data.response.UserInfo;
import com.ltg.framework.error.exception.BaseException;
import com.ltg.framework.util.http.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p> ClassName: CourseServiceImpl </p>
 * <p> Package: com.ltg.course.domain.course.service.impl </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 20:56
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private final CourseMapper courseMapper;
    private final FileInfoMapper fileInfoMapper;

    private final CourseOrderMapper courseOrderMapper;

    @Override
    public Course createCourse(CreateCourseDto courseDto) {
        String fileIds = String.join(",", courseDto.getFileIds());
        Course course = Course.builder()
                .courseName(courseDto.getCourseName())
                .price(courseDto.getPrice())
                .priceType(courseDto.getPriceType())
                .fileIds(fileIds)
                .courseStatus(0)
                .build();
        this.save(course);
        return course;
    }

    @Override
    public PageInfo<CourseVo> pageList(Page<CourseVo> page, Integer status, String keyword) {
        Page<CourseVo> courseVoPage = courseMapper.list(page, status, keyword);
        return new PageInfo<>(courseVoPage);

    }

    @Override
    public CourseVo detail(Long courseId) {
        Course course = this.getById(courseId);
        if (Objects.isNull(course)) {
            throw new BaseException("课程不存在");
        }
        CourseVo courseVo = new CourseVo();
        BeanUtils.copyProperties(course, courseVo);
        String fileIds = course.getFileIds();
        List<FileInfo> fileInfos = fileInfoMapper.selectBatchIds(Arrays.asList(fileIds.split(",")));
        courseVo.setFileInfos(fileInfos);
        return courseVo;

    }

    @Override
    public Course updateCourse(UpdateCourseDto courseDto) {
        Course course = this.getById(courseDto.getId());
        course.setCourseName(courseDto.getCourseName());
        course.setPrice(courseDto.getPrice());
        course.setFileIds(String.join(",", courseDto.getFileIds()));
        course.setPriceType(courseDto.getPriceType());
        this.updateById(course);
        return course;
    }

    @Override
    public PageInfo<CourseVo> consumerList(Page<CourseVo> page, Integer status, String keyword) {
        UserInfo currentUser = CurrentUserHolder.getCurrentUser();
        Long id = currentUser.getId();
        Page<CourseVo> courseVoPage = courseMapper.consumerList(page, id, status, keyword);
        List<CourseVo> courseVos = courseVoPage.getRecords();
        for (CourseVo courseVo : courseVos) {
            LambdaQueryWrapper<CourseOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CourseOrder::getCourseId, courseVo.getId());
            wrapper.eq(CourseOrder::getUserId,CurrentUserHolder.getCurrentUser().getId());
            CourseOrder courseOrder = courseOrderMapper.selectOne(wrapper);
            if(Objects.nonNull(courseOrder)){
                courseVo.setOrderId(courseOrder.getId());
            }
        }
        return new PageInfo<>(courseVoPage);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long courseId) {
        List<CourseOrder> courseOrders = courseOrderMapper.selectList(new LambdaQueryWrapper<CourseOrder>().eq(CourseOrder::getCourseId, courseId));
        if(Objects.nonNull(courseOrders)&&!courseOrders.isEmpty()){
            List<Long> list = courseOrders.stream().map(CourseOrder::getId).toList();
            courseOrderMapper.deleteBatchIds(list);
        }
        this.removeById(courseId);
    }
}
