package com.ltg.login.test;

import com.ltg.base.sys.data.param.LoginParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

/**
 * @Version 1.0
 * @Author LTG
 * @ClassName TestLogin
 * @Date 2023/10/28 15:20
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void testAdd() {

        // 准备请求体
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername("admin");
        loginParam.setPassword("123456");
        String loginUrl = "http://18.142.149.25/course-login-api/api/v1/account/login";
        ResponseEntity<String> response = restTemplate.postForEntity(loginUrl,loginParam, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String body = response.getBody();
        System.out.println(body);

    }
}
