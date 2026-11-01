# CREATE DATABASE IF NOT EXISTS gmall_bpm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS bpm_category
(
    id           varchar(36)                            NOT NULL COMMENT '分类ID' PRIMARY KEY,
    name         varchar(30)                            NOT NULL COMMENT '分类名称',
    code         varchar(100)                           NOT NULL COMMENT '分类编码',
    description  varchar(500) DEFAULT NULL              NULL COMMENT '分类描述',
    status       tinyint      DEFAULT 0                 NOT NULL COMMENT '分类状态（0正常 1停用）',
    sort         int(11)      DEFAULT 0                 NOT NULL COMMENT '显示顺序',
    creator      varchar(64)  DEFAULT ''                NULL COMMENT '创建者',
    create_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64)  DEFAULT ''                NULL COMMENT '更新者',
    update_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          DEFAULT b'0'              NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL comment '删除时间',
    tenant_id    bigint       DEFAULT 0                 NOT NULL COMMENT '租户编号'
) COMMENT '角色信息表' COLLATE = utf8mb4_unicode_ci;
