package com.ltg.order;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

/**
 * <p> ClassName: OrderApplication </p>
 * <p> Package: com.ltg.course.order </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/22 - 14:31
 * @Version: v1.0
 */

@Slf4j
@SpringBootApplication(scanBasePackages = {
        "com.ltg.base",
        "com.ltg.order",
        "com.ltg.framework"
})

@EnableScheduling
public class OrderApplication {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);

    }
}
