package com.shestnut.dao.entity;

import com.shestnut.dao.entity.annotation.EntityInfo;
import com.shestnut.dao.sql.SqlBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao implements IEntityDao{

    public static String dbName = null;

    public static String tableName = null;

    @Override
    public SqlBean buildSqlBean(IEntity entity) {
        SqlBean bean = new SqlBean();
        StringBuilder stringBuffer = new StringBuilder();
        List<Object> objects = new ArrayList<>();
        Class<?> clazz = entity.getClass();
        for (EntityInfo entityInfo : clazz.getAnnotationsByType(EntityInfo.class)) {
            dbName = entityInfo.dbName();
            tableName = entityInfo.tableName();
        }
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(entity) != null) {
                    if(!field.getName().equals("id")){
                        if(stringBuffer.length() == 0){
                            stringBuffer.append(field.getName()).append("= ?");
                        }else {
                            stringBuffer.append(",").append(field.getName()).append("= ?");
                        }
                        objects.add(field.get(entity));
                    }else {
                        bean.setPk((Long) field.get(entity));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        bean.setDbName(dbName);
        bean.setTableName(tableName);
        bean.setSql(stringBuffer.toString());
        bean.setArrgs(objects);
        return bean;
    }
}
