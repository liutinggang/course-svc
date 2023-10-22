package com.ltg.framework.swagger;


import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.webflux.core.configuration.SpringDocWebFluxConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ClassName: SwaggerConfig </p>
 * <p> Package: com.ltg.framework.config </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/1/4 - 10:26
 * @Version: v1.0
 */

@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SpringDocWebFluxConfiguration.class)
public class SwaggerConfiguration {


    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 根据@Tag 上的排序，写入x-order
     *
     * @return the global open api customizer
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags() != null) {
                openApi.getTags().forEach(tag -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("x-order", RandomUtil.randomInt(0, 100));
                    tag.setExtensions(map);
                });
            }
            if (openApi.getPaths() != null) {
                openApi.addExtension("x-test123", "333");
                openApi.getPaths().addExtension("x-abb", RandomUtil.randomInt(1, 100));
            }
        };
    }

    private List<SecurityRequirement> securitySchemes() {
        List<SecurityRequirement> apiKeys = new ArrayList<>();
        apiKeys.add(new SecurityRequirement());
        return apiKeys;
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName + " - API接口文档")
                        .description(applicationName + " API接口文档")
                        .version("1.0")
                        .contact(new Contact().name("刘庭港").email("2495720221@qq.com").url("www.ltg.com"))
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description(applicationName + "API文档")
                        .url("/v3/api-docs"));
    }
}
