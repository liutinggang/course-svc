package com.ltg.framework.constants;

/**
 * <p> ClassName: Constant </p>
 * <p> Package: com.ltg.framework.param </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/11 - 21:14
 * @Version: v1.0
 */
public enum Constant {
    TOKEN_HEADER_NAME("Authorization"),
    REDIS_USER_PREFIX("userId");

    private final String key;

    Constant(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }


}
