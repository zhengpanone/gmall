CREATE TABLE member_discount_rule
(
    id          varchar(36) PRIMARY KEY COMMENT '主键',
    member_type VARCHAR(32)   NOT NULL COMMENT '会员类型',
    discount    DECIMAL(5, 2) NOT NULL COMMENT '折扣，如 0.90',
    enabled     TINYINT(1) DEFAULT 1 COMMENT '是否启用',
    priority    INT      DEFAULT 0 COMMENT '优先级，数值越大越优先',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_member_type (member_type)
);
