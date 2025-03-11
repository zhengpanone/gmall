CREATE TABLE `system_user`
(
    `id`          bigint       NOT NULL COMMENT '用户ID',
    `username`    varchar(30)  NOT NULL COMMENT '用户账号',
    `password`    varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
    `nickname`    varchar(30)  NOT NULL COMMENT '用户昵称',
    `remark`      varchar(500)          DEFAULT NULL COMMENT '备注',
    `dept_id`     bigint                DEFAULT NULL COMMENT '部门ID',
    `post_ids`    varchar(255)          DEFAULT NULL COMMENT '岗位编号数组',
    `email`       varchar(50)           DEFAULT '' COMMENT '用户邮箱',
    `mobile`      varchar(11)           DEFAULT '' COMMENT '手机号码',
    `sex`         tinyint               DEFAULT '0' COMMENT '用户性别',
    `avatar`      varchar(512)          DEFAULT '' COMMENT '头像地址',
    `status`      tinyint      NOT NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `login_ip`    varchar(50)           DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime              DEFAULT NULL COMMENT '最后登录时间',
    `creator`     varchar(64)           DEFAULT '' COMMENT '创建者',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)           DEFAULT '' COMMENT '更新者',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint       NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户信息表';

CREATE TABLE `system_role`
(
    `id`                bigint       NOT NULL,
    `name`              varchar(32)           DEFAULT NULL,
    code                VARCHAR(100) NOT NULL COMMENT '角色标识',
    sort                INT          NOT NULL COMMENT '角色排序',
    status              TINYINT      NOT NULL COMMENT '角色状态，枚举 CommonStatusEnum',
    type                TINYINT      NOT NULL COMMENT '角色类型，枚举 RoleTypeEnum',
    remark              TEXT COMMENT '备注',
    data_scope          TINYINT      NOT NULL COMMENT '数据范围，枚举 DataScopeEnum',
    data_scope_dept_ids JSON COMMENT '数据范围(指定部门数组)，适用于 DataScopeEnum.DEPT_CUSTOM',
    tenant_id           VARCHAR(64)  NOT NULL COMMENT '租户ID',
    creator             VARCHAR(64) COMMENT '创建人',
    create_time         DATETIME     NOT NULL COMMENT '创建时间',
    updater             VARCHAR(64) COMMENT '更新人',
    update_time         DATETIME     NOT NULL COMMENT '更新时间',
    deleted             TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';


CREATE TABLE `system_dept`
(
    `id`             bigint       NOT NULL COMMENT '部门id',
    `name`           varchar(255) NOT NULL DEFAULT '' COMMENT '部门名称',
    `parent_id`      bigint       NOT NULL COMMENT '父部门id',
    `sort`           int          NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `leader_user_id` bigint                DEFAULT NULL COMMENT '负责人',
    `phone`          varchar(11)           DEFAULT NULL COMMENT '联系电话',
    `email`          varchar(50)           DEFAULT NULL COMMENT '邮箱',
    `status`         tinyint      NOT NULL COMMENT '部门状态（0正常 1停用）',
    `creator`        varchar(64)           DEFAULT '' COMMENT '创建者',
    `create_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(64)           DEFAULT '' COMMENT '更新者',
    `update_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`        bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`      bigint       NOT NULL DEFAULT '0' COMMENT '租户编号',
    `sync_status`    tinyint      NOT NULL COMMENT '同步状态',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='部门表';


CREATE TABLE `system_login_log`
(
    `id`          bigint       NOT NULL COMMENT '访问ID',
    `log_type`    bigint       NOT NULL COMMENT '日志类型',
    `trace_id`    varchar(64)  NOT NULL DEFAULT '' COMMENT '链路追踪编号',
    `user_id`     bigint       NOT NULL DEFAULT '0' COMMENT '用户编号',
    `user_type`   tinyint      NOT NULL DEFAULT '0' COMMENT '用户类型',
    `username`    varchar(50)  NOT NULL DEFAULT '' COMMENT '用户账号',
    `result`      tinyint      NOT NULL COMMENT '登陆结果',
    `user_ip`     varchar(50)  NOT NULL COMMENT '用户 IP',
    `user_agent`  varchar(512) NOT NULL COMMENT '浏览器 UA',
    `creator`     varchar(64)           DEFAULT '' COMMENT '创建者',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64)           DEFAULT '' COMMENT '更新者',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   bigint       NOT NULL DEFAULT '0' COMMENT '租户编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='系统访问记录';



CREATE TABLE `system_tenant`
(
    `id`              bigint         DEFAULT NULL COMMENT '租户编号',
    `name`            varchar(100)   DEFAULT NULL COMMENT '租户名',
    `contact_user_id` varchar(32)    DEFAULT NULL COMMENT '租户编号',
    `contact_name`    varchar(32)    DEFAULT NULL COMMENT '联系人',
    `contact_mobile`  varchar(32)    DEFAULT NULL COMMENT '联系手机',
    `status`          tinyint        DEFAULT NULL COMMENT '租户状态',
    `website`         varchar(32)    DEFAULT NULL COMMENT '绑定域名',
    `package_id`      varchar(32)    DEFAULT NULL COMMENT '租户套餐编号',
    `expire_time`     timestamp NULL DEFAULT NULL COMMENT '过期时间',
    `account_count`   int            DEFAULT NULL COMMENT '账号数量',
    `create_time`     timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`     timestamp NULL DEFAULT NULL COMMENT '更新时间',
    `creator`         varchar(32)    DEFAULT NULL COMMENT '创建人',
    `updater`         varchar(32)    DEFAULT NULL COMMENT '更新人',
    `deleted`         tinyint(1)     DEFAULT NULL COMMENT '是否删除'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;



