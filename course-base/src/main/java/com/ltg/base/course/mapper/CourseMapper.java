package com.ltg.base.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ltg.base.course.data.vo.CourseVo;
import com.ltg.base.course.entity.Course;
import org.apache.ibatis.annotations.Param;

/**
 * <p> ClassName: CourseMapper </p>
 * <p> Package: com.ltg.course.domain.course.mapper </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 20:55
 * @Version: v1.0
 */
public interface CourseMapper extends BaseMapper<Course> {
    Page<CourseVo> list(Page<CourseVo> page,
                        @Param("status") Integer status, @Param("keyword") String keyword);

    Page<CourseVo> consumerList(Page<CourseVo> page,
                                @Param("userId") Long userId,
                                @Param("status") Integer status,
                                @Param("keyword") String keyword);

}
