package com.shestnut.dao.entity;

import com.shestnut.dao.entity.annotation.EntityInfo;
import com.shestnut.dao.sql.SqlBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface IEntityDao<T extends IEntity> {

    SqlBean buildSqlBean(IEntity entity);

}
