package com.ltg.framework.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * <p> ClassName: DescribePage4Swagger </p>
 * <p> Package: com.ltg.framework.annotation </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/12 - 21:47
 * @Version: v1.0
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Parameter(name = "pageNumber", in = ParameterIn.QUERY, description = "当前页,不输入默认第1页", schema = @Schema(type = "integer"), example = "1")
@Parameter(name = "pageSize", in = ParameterIn.QUERY, description = "每页数据量,不输入默认10条记录", schema = @Schema(type = "integer"), example = "10")
public @interface DescribePage4Swagger {
}
