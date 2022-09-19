package com.shestnut.dao.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityInfo {

    /**
     * 数据库名, 必填
     *
     * @return
     */
    String dbName();

    /**
     * 表名, 可空, 默认类名
     *
     * @return
     */
    String tableName();
}
