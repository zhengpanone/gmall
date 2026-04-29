package com.zp.gmall.module.infra.entity.file;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

@TableName("infra_file")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDO extends BaseDO {

    @TableId
    private String id;
    private String fileName;
    private String filePath;
    private String fileType;
    private String fileSize;
    private String fileMd5;
    private String fileSuffix;
    private String fileUrl;
    private String fileStatus;
    private String fileCreateTime;
    private String fileUpdateTime;
}
