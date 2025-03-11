package com.zp.gmall.framework.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2025/4/2 13:09
 * Version : v1.0.0
 * Description:
 */
public class DefaultDBFiledHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject)) {
            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();
            if (baseDO instanceof BaseDO) {
                LocalDateTime current = LocalDateTime.now();
                // 创建时间为空，则以当前时间为插入时间
                if (Objects.isNull(baseDO.getCreateTime())) {
                    baseDO.setCreateTime(current);
                }
                // 更新时间为空，则以当前时间为更新时间
                if (Objects.isNull(baseDO.getUpdateTime())) {
                    baseDO.setUpdateTime(current);
                }
                // TODO  创建人
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if(Objects.isNull(modifyTime)){
            setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        }
        // TODO 更新人
    }
}
