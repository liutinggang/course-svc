package com.ltg.framework.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p> ClassName: StorageProperties </p>
 * <p> Package: com.ltg.framework.properties </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/14 - 21:44
 * @Version: v1.0
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.storage")
public class StorageProperties {

    private String domain;
    private String localPrefix;
    /**
     * 静态文件下载映射
     * key: 键值
     * value: 下载文件名
     */
    private Map<String, String> staticMap;
}
