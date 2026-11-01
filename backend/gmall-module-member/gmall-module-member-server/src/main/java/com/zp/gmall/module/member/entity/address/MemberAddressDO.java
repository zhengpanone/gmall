package com.zp.gmall.module.member.entity.address;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.io.Serial;

/**
 *
 * Description: 用户收货地址
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@TableName(value = "member_address", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddressDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    @TableId
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 收货人
     */
    private String name;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 是否默认
     */
    private Boolean isDefault;

    /**
     * 备注
     */
    private String remark;
}
