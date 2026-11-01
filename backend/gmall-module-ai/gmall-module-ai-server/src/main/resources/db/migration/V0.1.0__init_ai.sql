# CREATE DATABASE IF NOT EXISTS gmall_ai CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS ai_api_key
(
    id           varchar(36)                            NOT NULL COMMENT 'Api Key ID' PRIMARY KEY,
    name         varchar(30)                            NOT NULL COMMENT '名称',
    api_key      varchar(100)                           NOT NULL COMMENT '密钥',
    platform     varchar(30)                            NOT NULL COMMENT '平台',
    status       tinyint      DEFAULT 0                 NOT NULL COMMENT '状态（0正常 1停用）',
    url          varchar(200)                           NOT NULL COMMENT 'API地址',
    remark       varchar(500) DEFAULT NULL              NULL COMMENT '备注',
    creator      varchar(64)  DEFAULT ''                NULL COMMENT '创建者',
    create_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64)  DEFAULT ''                NULL COMMENT '更新者',
    update_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          DEFAULT b'0'              NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL comment '删除时间',
    tenant_id    bigint       DEFAULT 0                 NOT NULL COMMENT '租户编号'
) COMMENT '角色信息表' COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS ai_model
(
    id           varchar(36)                            NOT NULL COMMENT '模型ID' PRIMARY KEY,
    key_id       varchar(36)                            NOT NULL COMMENT 'Api Key ID',
    name         varchar(30)                            NOT NULL COMMENT '模型名称',
    model        varchar(100)                           NOT NULL COMMENT '模型标志',
    platform     varchar(30)                            NOT NULL COMMENT '平台',
    type         varchar(30)                            NOT NULL COMMENT '模型类型',
    sort         int(11)                                NOT NULL COMMENT '排序',
    status       tinyint      DEFAULT 0                 NOT NULL COMMENT '状态（0正常 1停用）',
    temperature  float(11)                              NOT NULL COMMENT '温度参数',
    max_tokens   int(11)                                NOT NULL COMMENT '最大token数',
    max_contexts int(11)                                NOT NULL COMMENT '最大Message数量',
    remark       varchar(500) DEFAULT NULL              NULL COMMENT '备注',
    creator      varchar(64)  DEFAULT ''                NULL COMMENT '创建者',
    create_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64)  DEFAULT ''                NULL COMMENT '更新者',
    update_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          DEFAULT b'0'              NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL comment '删除时间',
    tenant_id    bigint       DEFAULT 0                 NOT NULL COMMENT '租户编号'
) COMMENT '模型信息表' COLLATE = utf8mb4_unicode_ci;