package com.ltg.base.course.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ltg.base.course.data.vo.CourseOrderVo;
import com.ltg.base.course.entity.CourseOrder;
import org.apache.ibatis.annotations.Param;

/**
 * <p> ClassName: OrderMapper </p>
 * <p> Package: com.ltg.course.domain.course.mapper </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 21:44
 * @Version: v1.0
 */
public interface CourseOrderMapper extends BaseMapper<CourseOrder> {
    Page<CourseOrderVo> pageList(Page<CourseOrderVo> page,
                                 @Param("status") Integer status,
                                 @Param("keyword") String keyword);

    Page<CourseOrderVo> myOrderList(Page<CourseOrderVo> objectPage,
                                    @Param("userId") Long userId,
                                    @Param("status") Integer status,
                                    @Param("keyword") String keyword);


}
