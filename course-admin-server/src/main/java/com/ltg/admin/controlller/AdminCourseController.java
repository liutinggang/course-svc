package com.ltg.admin.controlller;

import com.ltg.base.course.data.dto.CreateCourseDto;
import com.ltg.base.course.data.dto.UpdateCourseDto;
import com.ltg.base.course.data.vo.CourseVo;
import com.ltg.base.course.entity.Course;
import com.ltg.base.course.service.CourseService;
import com.ltg.framework.util.http.PageInfo;
import com.ltg.framework.util.http.PageRequest;
import com.ltg.framework.util.http.Result;
import com.ltg.framework.annotation.DescribePage4Swagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p> ClassName: AdminCourseController </p>
 * <p> Package: com.ltg.course.domain.course.controller </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 20:57
 * @Version: v1.0
 */
@RestController
@Tag(name = "课程模块:管理端")
@RequiredArgsConstructor
public class AdminCourseController {

    private final CourseService courseService;


    @PutMapping("/api/v1/admin/course/publish")
    @Operation(summary = "课程发布")
    public Result<Course> create(@RequestBody CreateCourseDto courseDto) {
        Course course =courseService.createCourse(courseDto);
        return Result.success(course);
    }

    @PostMapping("/api/v1/admin/course/update")
    @Operation(summary = "课程修改")
    public Result<Course> update(@RequestBody UpdateCourseDto courseDto) {
        Course course =courseService.updateCourse(courseDto);
        return Result.success(course);
    }

    @Operation(summary = "课程分页查询")
    @GetMapping("/api/v1/admin/course/page")
    @DescribePage4Swagger
    public PageInfo<CourseVo> page(HttpServletRequest httpServletRequest,
                                   @RequestParam(required = false)Integer status,
                                   @RequestParam(required = false) String keyword) {
        return courseService.pageList(PageRequest.buildFromRequest(httpServletRequest),status,keyword);
    }

    @GetMapping("/api/v1/admin/course/detail/{courseId}")
    @Operation(summary = "课程详情")
    public CourseVo detail(@PathVariable Long courseId){
        return courseService.detail(courseId);
    }

    @DeleteMapping("/api/v1/admin/course/delete/{courseId}")
    @Operation(summary = "课程详情")
    public Result delete(@PathVariable Long courseId){
         courseService.delete(courseId);
         return Result.success();
    }

}
