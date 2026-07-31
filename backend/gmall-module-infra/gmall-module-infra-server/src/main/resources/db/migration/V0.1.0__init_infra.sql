-- CREATE DATABASE IF NOT EXISTS gmall_infra CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
DROP TABLE IF EXISTS infra_config;
CREATE TABLE IF NOT EXISTS infra_config
(
    id           varchar(36)                            NOT NULL PRIMARY KEY COMMENT '参数主键',
    category     varchar(50)                            NOT NULL COMMENT '参数分组',
    type         smallint                               NOT NULL COMMENT '参数类型',
    name         varchar(100) DEFAULT ''                NULL COMMENT '参数名称',
    config_key   varchar(100) DEFAULT ''                NULL COMMENT '参数键名',
    value        varchar(500) DEFAULT ''                NULL COMMENT '参数键值',
    visible      bit                                    NOT NULL COMMENT '是否可见',
    remark       varchar(500) DEFAULT NULL              NULL COMMENT '备注',
    creator      varchar(64)  DEFAULT ''                NULL COMMENT '创建者',
    create_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64)  DEFAULT ''                NULL COMMENT '更新者',
    update_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          DEFAULT b'0'               NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL COMMENT '删除时间',
    tenant_id    bigint       DEFAULT 0                 NOT NULL COMMENT '租户编号'
) COLLATE = utf8mb4_unicode_ci COMMENT '参数配置表';

DROP TABLE IF EXISTS infra_config;
CREATE TABLE IF NOT EXISTS infra_file_content
(
    id           varchar(36)           NOT NULL PRIMARY KEY COMMENT '编号',
    config_id    varchar(36)           NOT NULL COMMENT '配置编号',
    path         varchar(512)          NOT NULL COMMENT '文件路径',
    content      blob                  NOT NULL COMMENT '文件内容',
    create_time  timestamp             NULL COMMENT '创建时间',
    update_time  timestamp             NULL COMMENT '更新时间',
    creator      varchar(32)           NULL COMMENT '创建人',
    updater      varchar(32)           NULL COMMENT '更新人',
    deleted      bit      default b'0' NULL COMMENT '是否删除',
    deleted_time datetime DEFAULT NULL COMMENT '删除时间'
) COMMENT '文件表' COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_infra_file_content_01 ON infra_file_content (config_id, path);


DROP TABLE IF EXISTS infra_file_config;
CREATE TABLE IF NOT EXISTS infra_file_config
(
    id           varchar(36)               NOT NULL PRIMARY KEY COMMENT '编号',
    name         varchar(63)               NOT NULL COMMENT '配置名',
    storage      smallint                  NOT NULL COMMENT '存储器',
    remark       varchar(255) DEFAULT NULL NULL COMMENT '备注',
    master       bit                       NOT NULL COMMENT '是否为主配置',
    config       varchar(4096)             NOT NULL COMMENT '存储配置',
    create_time  timestamp                 NULL COMMENT '创建时间',
    update_time  timestamp                 NULL COMMENT '更新时间',
    creator      varchar(32)               NULL COMMENT '创建人',
    updater      varchar(32)               NULL COMMENT '更新人',
    deleted      bit          default b'0' NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL COMMENT '删除时间'
) COMMENT '文件配置表' COLLATE = utf8mb4_unicode_ci;

