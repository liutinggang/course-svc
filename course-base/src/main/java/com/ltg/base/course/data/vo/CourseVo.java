package com.ltg.base.course.data.vo;

import com.ltg.base.course.entity.Course;
import com.ltg.base.file.entity.FileInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


/**
 * <p> ClassName: CouseVo </p>
 * <p> Package: com.ltg.course.domain.course.data.vo </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/20 - 21:18
 * @Version: v1.0
 */
@Data
public class CourseVo extends Course {
    @Schema(description = "文件信息", type = "string", example = "")
    private List<FileInfo> fileInfos;
    private Long orderId;


}
