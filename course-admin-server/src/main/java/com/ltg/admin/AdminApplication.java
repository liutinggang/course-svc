package com.ltg.admin;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

/**
 * <p> ClassName: UrbanApplication </p>
 * <p> Package: com.ltg.urban </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 18:17
 * @Version: v1.0
 */

@Slf4j
@SpringBootApplication(scanBasePackages = {
        "com.ltg.base",
        "com.ltg.admin",
        "com.ltg.framework"
})

@EnableScheduling
public class AdminApplication {
    @SneakyThrows
    public static void main(String[] args) {

        SpringApplication.run(AdminApplication.class);
    }
}
