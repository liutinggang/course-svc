package com.ltg.framework.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p> ClassName: loginProperties </p>
 * <p> Package: com.ltg.framework.properties </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 20:49
 * @Version: v1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "app.login")
public class LoginProperties {
    private List<String> filterExcludeUrl;
    private List<String> filterIncludeUrl;

}
