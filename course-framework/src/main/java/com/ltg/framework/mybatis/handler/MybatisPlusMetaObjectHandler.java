package com.ltg.framework.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ltg.framework.mybatis.entities.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p> ClassName: MybatisPlusMetaObjectHandler </p>
 * <p> Package: com.ltg.framework.mybatis.handler </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/1/4 - 12:09
 * @Version: v1.0
 */

@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(BaseEntity.PROPERTY_CREATED_TIME, LocalDateTime.now(), metaObject);
        this.setFieldValByName(BaseEntity.PROPERTY_UPDATE_TIME, LocalDateTime.now(), metaObject);
        this.setFieldValByName(BaseEntity.PROPERTY_DELETED, 0, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(BaseEntity.PROPERTY_UPDATE_TIME, LocalDateTime.now(), metaObject);
    }
}
