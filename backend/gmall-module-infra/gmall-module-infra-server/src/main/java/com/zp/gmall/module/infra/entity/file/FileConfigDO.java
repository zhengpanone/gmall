package com.zp.gmall.module.infra.entity.file;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.file.core.enums.FileStorageEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 文件配置表
 */
@TableName("infra_file_config")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileConfigDO extends BaseDO {

    /**
     * 配置编号，数据库自增
     */
    private String id;
    /**
     * 配置名
     */
    private String name;
    /**
     * 存储器
     * <p>
     * 枚举 {@link FileStorageEnum}
     */
    private Integer storage;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否为主配置
     * <p>
     * 由于我们可以配置多个文件配置，默认情况下，使用主配置进行文件的上传
     */
    private Boolean master;
}
