package com.ltg.framework.util.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

public class JJWTUtil {
    //设置密钥
    //key的大小必须大于或等于256bit,需要32位英文字符，
    // 一个英文字符为：8bit（1位）,一个中文字符为16bit（2位）
    public static String SECRET_KEY = null;
    //默认密钥
    public final static String DEFAULT_SECRET_KEY = JWTDefaultSecretKey.DEFAULT_SECRET_KEY1.getValue();
    //设置加密算法
    public static SignatureAlgorithm SIGN = null;
    //默认加密算法
    public final static SignatureAlgorithm DEFAULT_SIGN = SignatureAlgorithm.HS256;
    //设置token持续时间
    public static Date EXPIRATION_TIME = null;


    /**
     * 默认构造器，用户无需自定义，直接生成使用
     */
    public JJWTUtil defaultBuilder(JJWTUtil jwtUtil) {
        jwtUtil.initSecretKey();
        jwtUtil.initExpirationTime();
        jwtUtil.initSignatureAlgorithm();
        return jwtUtil;
    }

    /**
     * 获取加密算法
     */
    public void getSignatureAlgorithm() {
        System.out.println(SIGN);
    }


    /**
     * 判断密钥是否初始化，初始化完成则返回true，否则false
     */
    public Boolean judgeSecretKeyInit() {
        boolean flag;
        if (SECRET_KEY == null) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 未编码密钥处理，用户构造或解析token时传入加密密钥
     */
    public SecretKey getSecretKey() {
        //拦截，若未设置密钥则调用默认初始化密钥
        Boolean keyJudge = judgeSecretKeyInit();
        if (!keyJudge) {
            initSecretKey();
        }
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * 初始化密钥,自定义方式
     */
    public String initSecretKey(String selfDefineSecretKey) {
        //判断是否传入值为空，赋予默认值
        if (selfDefineSecretKey == null) {
            SECRET_KEY = DEFAULT_SECRET_KEY;
            return SECRET_KEY;
        }
        //拦截用户输入，判断是否达到长度要求
        byte[] bytes = selfDefineSecretKey.getBytes(StandardCharsets.UTF_8);
        if (bytes.length < 32) {
            new Exception("密钥未达到指定长度（32位），请重设");
            return SECRET_KEY;
        } else {
            SECRET_KEY = selfDefineSecretKey;
        }
        return SECRET_KEY;
    }

    /**
     * 初始化密钥，默认构成密钥
     * 默认密钥为DEFAULT_SECRET_KEY ="self_define_secret_key_about_the_project"
     */
    public String initSecretKey() {
        SECRET_KEY = DEFAULT_SECRET_KEY;
        return SECRET_KEY;
    }

    /**
     * 初始化密钥，传入值为JWTDefaultSecretKey枚举类
     */
    public String initSecretKey(JWTDefaultSecretKey defaultSecretKey) {
        SECRET_KEY = defaultSecretKey.getValue();
        return SECRET_KEY;
    }

    /**
     * 初始化过期时间，通过JWTDefaultExpirationTime枚举设置（推荐！！！）
     */
    public Date initExpirationTime(JWTDefaultExpirationTime expirationTime) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, expirationTime.getValue());
        EXPIRATION_TIME = instance.getTime();
        return EXPIRATION_TIME;
    }

    /**
     * 初始化过期时间，默认为7天过期
     */
    public Date initExpirationTime() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, JWTDefaultExpirationTime.DEFAULT_EXPIRATION_TIME.getValue());
        EXPIRATION_TIME = instance.getTime();
        return EXPIRATION_TIME;
    }

    /**
     * 设置加密算法，自定义
     */
    public SignatureAlgorithm initSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        SIGN = signatureAlgorithm;
        return SIGN;
    }

    /**
     * 设置加密算法，默认
     */
    public SignatureAlgorithm initSignatureAlgorithm() {
        SIGN = DEFAULT_SIGN;
        return SIGN;
    }

    /**
     * 构建token
     */
    public String createToken(Claims claims) {
        //记录生成时间
        claims.put("create", System.currentTimeMillis());
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(EXPIRATION_TIME)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey(), SIGN)
                .compact();
        return token;
    }

    /**
     * 宽解析，得到Header，PayLoad，Signature的包装
     */
    public Jws<Claims> getJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token);
    }

    /**
     * 具体解析出Header
     */
    public JwsHeader getTokenHeader(String token) {
        Jws jws = getJws(token);
        return (JwsHeader) jws.getHeader();
    }

    /**
     * 具体解析出PayLoad
     */
    public Claims getTokenPayLoad(String token) {
        Jws jws = getJws(token);
        return (Claims) jws.getBody();
    }

    /**
     * 具体解析出Signature
     */
    public String getTokenSignature(String token) {
        Jws jws = getJws(token);
        return jws.getSignature();
    }

    /**
     * 具体解析出剩余时间（过期）
     */
    public Long getTokenExpirationTime(String token) {
        Claims tokenPayLoad = getTokenPayLoad(token);
        Long tokenExpirationTime = Long.valueOf((Integer) tokenPayLoad.get("exp"));
        Long createDate = (Long) tokenPayLoad.get("create");
        Long rest = tokenExpirationTime * 1000 - createDate;
        return rest;
    }

    /**
     * 判断token是否过期，返回布尔值，过期则true，否则false
     */
    public Boolean judgeExpirationTime(String token) {
        Long rest = getTokenExpirationTime(token);
        return rest <= 0;
    }


}
