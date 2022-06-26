package com.zp.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 商品评价回复关系
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("pms_comment_replay")
public class CommentReplayEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
	private Long commentId;
    /**
     * 回复id
     */
	private Long replyId;
}