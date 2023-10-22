package com.ltg.framework.util.jwt;

/**
 * JWTUtil的密钥默认枚举
 */
public enum JWTDefaultSecretKey{
    //默认密钥
    DEFAULT_SECRET_KEY1("self_define_secret_key_about_the_project"),
    //默认密钥2
    DEFAULT_SECRET_KEY2("init_define_secret_key_about_project_self");

    private String value;

    JWTDefaultSecretKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
