-- CREATE DATABASE IF NOT EXISTS gmall_infra CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
DROP TABLE IF EXISTS infra_config;
CREATE TABLE IF NOT EXISTS infra_config
(
    id           varchar(36)               NOT NULL PRIMARY KEY COMMENT '参数主键',
    category     varchar(50)               NOT NULL COMMENT '参数分组',
    type         smallint                  NOT NULL COMMENT '参数类型',
    name         varchar(100) DEFAULT ''   NULL COMMENT '参数名称',
    config_key   varchar(100) DEFAULT ''   NULL COMMENT '参数键名',
    value        varchar(500) DEFAULT ''   NULL COMMENT '参数键值',
    visible      bit                       NOT NULL COMMENT '是否可见',
    remark       varchar(500) DEFAULT NULL NULL COMMENT '备注',
    creator      varchar(64)  DEFAULT ''   NULL COMMENT '创建者',
    create_time  datetime                  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater      varchar(64)  DEFAULT ''   NULL COMMENT '更新者',
    update_time  datetime                  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          DEFAULT b'0' NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL NULL COMMENT '删除时间',
    tenant_id    bigint       DEFAULT 0    NOT NULL COMMENT '租户编号'
) COLLATE = utf8mb4_unicode_ci COMMENT '参数配置表';

DROP TABLE IF EXISTS infra_file_content;
CREATE TABLE IF NOT EXISTS infra_file_content
(
    id           varchar(36)  NOT NULL PRIMARY KEY COMMENT '编号',
    config_id    varchar(36)  NOT NULL COMMENT '配置编号',
    path         varchar(512) NOT NULL COMMENT '文件路径',
    content      blob         NOT NULL COMMENT '文件内容',
    create_time  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    creator      varchar(32)  NULL COMMENT '创建人',
    updater      varchar(32)  NULL COMMENT '更新人',
    deleted      bit                   default b'0' NULL COMMENT '是否删除',
    deleted_time datetime              DEFAULT NULL COMMENT '删除时间'
) COMMENT '文件表' COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_infra_file_content ON infra_file_content (config_id, path);


DROP TABLE IF EXISTS infra_file_config;
CREATE TABLE IF NOT EXISTS infra_file_config
(
    id           varchar(36)   NOT NULL PRIMARY KEY COMMENT '编号',
    name         varchar(63)   NOT NULL COMMENT '配置名',
    storage      smallint      NOT NULL COMMENT '存储器',
    remark       varchar(255)           DEFAULT NULL NULL COMMENT '备注',
    master       bit           NOT NULL COMMENT '是否为主配置',
    config       varchar(4096) NOT NULL COMMENT '存储配置',
    create_time  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    creator      varchar(32)   NULL COMMENT '创建人',
    updater      varchar(32)   NULL COMMENT '更新人',
    deleted      bit                    default b'0' NULL COMMENT '是否删除',
    deleted_time datetime               DEFAULT NULL COMMENT '删除时间'
) COMMENT '文件配置表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS infra_data_source_config;
CREATE TABLE IF NOT EXISTS infra_data_source_config
(
    id           varchar(36)  NOT NULL PRIMARY KEY COMMENT '主键编号',
    name         varchar(100) NULL     DEFAULT '' COMMENT '创建者',
    create_time  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    creator      varchar(64)  NULL COMMENT '创建人',
    updater      varchar(64)  NULL     DEFAULT '' COMMENT '更新者',
    update_time  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    deleted_time datetime              DEFAULT NULL COMMENT '删除时间'
) COMMENT = '数据源配置表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS infra_attendance_calendar;
CREATE TABLE IF NOT EXISTS infra_attendance_calendar
(
    id            varchar(36) NOT NULL PRIMARY KEY COMMENT '主键ID',
    calendar_date DATE        NOT NULL COMMENT '日期',
    date_type     TINYINT     NOT NULL COMMENT '日期类型：1工作日 2普通休息日 3法定假日 4调休补班 5企业假',
    holiday_name  VARCHAR(64) COMMENT '假期名称，如春节、国庆节',
    year          INT         NOT NULL COMMENT '年份，用于快速筛选',
    month         INT         NOT NULL COMMENT '月份',
    week_day      TINYINT COMMENT '星期几 1-7',
    need_checkin  TINYINT              DEFAULT 1 COMMENT '是否需要打卡：0否 1是',
    overtime_rate DECIMAL(2, 1)        DEFAULT 1.0 COMMENT '加班薪资倍率',
    remark        VARCHAR(255) COMMENT '备注',
    create_time   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    creator       varchar(64) NULL COMMENT '创建人',
    updater       varchar(64) NULL     DEFAULT '' COMMENT '更新者',
    update_time   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted       bit(1)      NOT NULL DEFAULT b'0' COMMENT '是否删除',
    deleted_time  datetime             DEFAULT NULL COMMENT '删除时间',
    UNIQUE KEY uk_calendar_date (calendar_date),
    INDEX idx_year_month (year, month)
) COMMENT '企业工作日历表';
