package com.zp.product.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 商品评价
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
public class SpuCommentExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "sku_id")
    private Long skuId;
    @Excel(name = "spu_id")
    private Long spuId;
    @Excel(name = "商品名字")
    private String spuName;
    @Excel(name = "会员昵称")
    private String memberNickName;
    @Excel(name = "星级")
    private Integer star;
    @Excel(name = "会员ip")
    private String memberIp;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "显示状态[0-不显示，1-显示]")
    private Integer showStatus;
    @Excel(name = "购买时属性组合")
    private String spuAttributes;
    @Excel(name = "点赞数")
    private Integer likesCount;
    @Excel(name = "回复数")
    private Integer replyCount;
    @Excel(name = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
    private String resources;
    @Excel(name = "内容")
    private String content;
    @Excel(name = "用户头像")
    private String memberIcon;
    @Excel(name = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
    private Integer commentType;

}