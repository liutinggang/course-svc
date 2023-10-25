package com.ltg.login;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * <p> ClassName: LoginApplication </p>
 * <p> Package: com.ltg.course.login </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/10/22 - 15:15
 * @Version: v1.0
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {
        "com.ltg.base",
        "com.ltg.login",
        "com.ltg.framework"
})

@EnableScheduling
public class LoginApplication {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class);
    }
}