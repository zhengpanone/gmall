package com.zp.gmall.module.infra.entity.codegen;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.infra.entity.db.DataSourceConfigDO;
import lombok.*;

import java.io.Serial;

@TableName("infra_codegen_table")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodegenTableDO extends BaseDO {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 数据源编号 {@link DataSourceConfigDO#getId()}
     */
    private String dataSourceId;
    /**
     * 表名 {@link TableInfo#getName()}
     */
    private String tableName;

    /**
     * 表注释 {@link TableInfo#getComment()}
     */
    private String tableComment;

    private String tableEngine;

    private String tableCollation;

    private String tableCharset;
}
