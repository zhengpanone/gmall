# CREATE DATABASE IF NOT EXISTS gmall_infra CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE TABLE infra_config
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
    deleted      bit          DEFAULT '0'               NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL COMMENT '删除时间',
    tenant_id    bigint       DEFAULT 0                 NOT NULL COMMENT '租户编号'
) COLLATE = utf8mb4_unicode_ci COMMENT '参数配置表';

