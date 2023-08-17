package com.zp.sys.model.entity;


import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类，所有实体都需要继承
 */
@Data
public abstract class BaseEntity implements Serializable {
    /**
     * id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long id;
    /**
     * 创建者
     */

    private Long creator;
    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private Date createDate;
}
