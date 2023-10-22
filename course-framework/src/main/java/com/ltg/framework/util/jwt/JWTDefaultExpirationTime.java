package com.ltg.framework.util.jwt;

/**
 * JWTUtil默认持续时间枚举类,单位秒
 */
public enum JWTDefaultExpirationTime {
    //默认为7天：7*24*60
    DEFAULT_EXPIRATION_TIME(604800),
    WEEK(604800),
    DAY(86400),
    HaLF_MONTH(1296000);


    private Integer value;

    JWTDefaultExpirationTime(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
