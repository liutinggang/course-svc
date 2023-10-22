package com.ltg.framework.util;

import com.ltg.framework.util.jwt.JJWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

/**
 * <p> ClassName: JwtUtil </p>
 * <p> Package: com.ltg.framework.util </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 21:14
 * @Version: v1.0
 */
public class JwtUtil {


    public static String createToken(String id) {
        JJWTUtil jjwtUtil = new JJWTUtil();
        //通过工具类提供的默认构造器直接生成默认加密算法、加密密钥、持续周期
        jjwtUtil.defaultBuilder(jjwtUtil);
        //设置传入的内容
        Claims claims = Jwts.claims();
        claims.put("id", id);
        //构造token
        return jjwtUtil.createToken(claims);
    }

    public static Jws<Claims> parseJWT(String token) {
        JJWTUtil jjwtUtil = new JJWTUtil();
        return jjwtUtil.getJws(token);
    }
}
