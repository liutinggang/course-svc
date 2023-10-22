package com.ltg.framework.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> ClassName: MybatisPlusConfiguration </p>
 * <p> Package: com.ltg.framework.config </p>
 * <p> Description: MybatisPlus配置</p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 21:03
 * @Version: v1.0
 */

@Configuration
public class MybatisPlusConfiguration {


    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
