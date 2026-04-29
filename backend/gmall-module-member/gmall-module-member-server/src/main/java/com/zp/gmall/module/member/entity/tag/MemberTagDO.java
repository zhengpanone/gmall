package com.zp.gmall.module.member.entity.tag;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:03
 * Version : v1.0.0
 * Description: 会员标签 DO
 */
@TableName("member_tag")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberTagDO extends BaseDO {
    /**
     * 编号
     */
    @TableId
    private String id;
    /**
     * 标签名称
     */
    private String name;
}
