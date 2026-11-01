-- ============================================================
-- 交易模块 DDL
-- 基于 DDD 领域驱动设计，trade_order 为订单聚合根的持久化表
-- ============================================================
CREATE DATABASE IF NOT EXISTS gmall_trade CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- 订单表
CREATE TABLE IF NOT EXISTS `trade_order`
(
    `id`               BIGINT       NOT NULL COMMENT '订单ID（主键）',
    `order_no`         VARCHAR(32)  NOT NULL COMMENT '订单编号（业务号，对外展示）',
    `member_id`        BIGINT       NOT NULL COMMENT '会员ID',
    `status`           VARCHAR(32)  NOT NULL DEFAULT 'PENDING_PAYMENT' COMMENT '订单状态: PENDING_PAYMENT/PAID/SHIPPED/DELIVERED/CANCELLED/REFUNDING/REFUNDED',

    -- 收货地址（平铺存储，对应 Address 值对象）
    `receiver_province` VARCHAR(32)  NOT NULL COMMENT '收货省份',
    `receiver_city`     VARCHAR(32)  NOT NULL COMMENT '收货城市',
    `receiver_region`   VARCHAR(32)  NOT NULL COMMENT '收货区/县',
    `receiver_detail`   VARCHAR(256) NOT NULL COMMENT '收货详细地址',
    `receiver_phone`    VARCHAR(16)  NOT NULL COMMENT '收货人手机号',

    -- 金额（对应 Money 值对象，以元为单位保留两位小数）
    `total_amount`     DECIMAL(12, 2) NOT NULL DEFAULT 0.00 COMMENT '商品总金额',
    `freight_amount`   DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '运费',
    `pay_amount`       DECIMAL(12, 2) NOT NULL DEFAULT 0.00 COMMENT '实付金额',
    `discount_amount`  DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额',

    -- 支付信息
    `paid_time`        DATETIME     NULL COMMENT '支付时间',
    `payment_no`       VARCHAR(64)  NULL COMMENT '支付流水号',

    -- 物流信息
    `shipped_time`      DATETIME     NULL COMMENT '发货时间',
    `tracking_no`       VARCHAR(64)  NULL COMMENT '物流单号',
    `logistics_company` VARCHAR(64)  NULL COMMENT '物流公司',

    -- 备注与版本
    `remark`           VARCHAR(512) NULL COMMENT '订单备注',
    `version`          INT          NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',

    -- 审计字段（继承 BaseDO）
    `creator`          VARCHAR(64)  NULL COMMENT '创建者',
    `create_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`          VARCHAR(64)  NULL COMMENT '更新者',
    `update_time`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`          TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '是否删除',
    `deleted_time`     DATETIME     NULL COMMENT '删除时间',

    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_member_id` (`member_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci COMMENT ='订单表（交易聚合根）';

-- 订单项表
CREATE TABLE IF NOT EXISTS `trade_order_item`
(
    `id`            BIGINT        NOT NULL COMMENT '订单项ID（主键）',
    `order_id`      BIGINT        NOT NULL COMMENT '所属订单ID',
    `sku_id`        BIGINT        NOT NULL COMMENT '商品SKU ID',
    `product_name`  VARCHAR(256)  NOT NULL COMMENT '商品名称（快照）',
    `product_image` VARCHAR(512)  NULL COMMENT '商品图片',
    `quantity`      INT           NOT NULL COMMENT '购买数量',
    `unit_price`    DECIMAL(12, 2) NOT NULL COMMENT '单价',
    `subtotal`      DECIMAL(12, 2) NOT NULL COMMENT '小计金额',
    `create_time`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci COMMENT ='订单项表（订单聚合内部实体）';
