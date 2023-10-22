package com.ltg.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p> ClassName: DataSourceConfig </p>
 * <p> Package: com.ltg.course.base.config </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/22 - 15:02
 * @Version: v1.0
 */
@Configuration
@MapperScan(basePackages = {
        "com.ltg.base.sys.mapper",
        "com.ltg.base.course.mapper",
        "com.ltg.base.file.mapper",
})
public class DataSourceConfig {
}
