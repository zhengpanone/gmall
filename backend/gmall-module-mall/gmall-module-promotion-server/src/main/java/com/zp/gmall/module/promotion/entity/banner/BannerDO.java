package com.zp.gmall.module.promotion.entity.banner;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.promotion.enums.banner.BannerPositionEnum;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:04
 * Version : v1.0.0
 * Description:
 */
@TableName("promotion_banner")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 跳转地址
     */
    private String url;
    /**
     * 图片链接
     */
    private String picUrl;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 定位 {@link BannerPositionEnum}
     */
    private Integer position;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 点击次数
     */
    private Integer browseCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发布者
     */
    private String publisherId;
    /**
     * 审核者
     */
    private String authId;
}
