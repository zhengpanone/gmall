#
CREATE DATABASE IF NOT EXISTS gmall_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_role;
CREATE TABLE IF NOT EXISTS sys_role
(
    id           varchar(36)                            NOT NULL PRIMARY KEY COMMENT '角色ID',
    name         varchar(30)                            NOT NULL COMMENT '角色名称',
    code         varchar(100)                           NOT NULL COMMENT '角色权限字符串',
    sort         int          DEFAULT 1                 NOT NULL COMMENT '显示顺序',
    status       tinyint      DEFAULT 1                 NOT NULL COMMENT '角色状态（1正常 0停用）',
    type         tinyint                                NOT NULL COMMENT '角色类型',
    remark       varchar(500) DEFAULT NULL              NULL COMMENT '备注',
    creator      varchar(64)  DEFAULT ''                NULL COMMENT '创建者',
    create_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64)  DEFAULT ''                NULL COMMENT '更新者',
    update_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          DEFAULT b'0'              NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL COMMENT '删除时间',
    tenant_id    bigint       DEFAULT 0                 NOT NULL COMMENT '租户编号'
) COMMENT '角色信息表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_dept;
CREATE TABLE IF NOT EXISTS sys_dept
(
    id             varchar(36)                           NOT NULL PRIMARY KEY COMMENT '部门id',
    name           varchar(30) DEFAULT ''                NOT NULL COMMENT '部门名称',
    parent_id      varchar(32)                           NOT NULL COMMENT '父部门id',
    sort           int         DEFAULT 1                 NOT NULL COMMENT '显示顺序',
    leader_user_id bigint                                NULL COMMENT '负责人',
    phone          varchar(11)                           NULL COMMENT '联系电话',
    email          varchar(50)                           NULL COMMENT '邮箱',
    status         tinyint     DEFAULT 1                 NOT NULL COMMENT '部门状态（1正常 0停用）',
    creator        varchar(64) DEFAULT ''                NULL COMMENT '创建者',
    create_time    datetime    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater        varchar(64) DEFAULT ''                NULL COMMENT '更新者',
    update_time    datetime    DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted        bit         DEFAULT b'0'              NOT NULL COMMENT '是否删除',
    deleted_time   datetime    DEFAULT NULL              NULL COMMENT '删除时间',
    tenant_id      bigint      DEFAULT 0                 NOT NULL COMMENT '租户编号'
) COMMENT '部门表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_login_log;
CREATE TABLE IF NOT EXISTS sys_login_log
(
    id           varchar(36)                           NOT NULL primary key COMMENT '访问ID',
    log_type     bigint                                NOT NULL COMMENT '日志类型',
    trace_id     varchar(64) default ''                NOT NULL COMMENT '链路追踪编号',
    user_id      bigint      default 0                 NOT NULL COMMENT '用户编号',
    user_type    tinyint     default 0                 NOT NULL COMMENT '用户类型',
    username     varchar(50) default ''                NOT NULL COMMENT '用户账号',
    result       tinyint                               NOT NULL COMMENT '登陆结果',
    user_ip      varchar(50)                           NOT NULL COMMENT '用户 IP',
    user_agent   varchar(512)                          NOT NULL COMMENT '浏览器 UA',
    creator      varchar(64) default ''                NULL COMMENT '创建者',
    create_time  datetime    default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64) default ''                NULL COMMENT '更新者',
    update_time  datetime    default CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit         default b'0'              NOT NULL COMMENT '是否删除',
    deleted_time datetime    DEFAULT NULL              NULL COMMENT '删除时间',
    tenant_id    bigint      default 0                 NOT NULL COMMENT '租户编号'
) COMMENT '系统访问记录' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_tenant_package;
CREATE TABLE IF NOT EXISTS sys_tenant_package
(
    id           varchar(36)           NOT NULL PRIMARY KEY COMMENT '租户套餐ID',
    code         varchar(32)           NOT NULL COMMENT '套餐编码',
    name         varchar(32)           NOT NULL COMMENT '套餐名称',
    type         varchar(32)           NOT NULL COMMENT '套餐类型',
    description  varchar(32)           NOT NULL COMMENT '套餐描述',
    status       tinyint  default 0    NOT NULL COMMENT '套餐状态（1正常 0停用）',
    create_time  timestamp             NULL COMMENT '创建时间',
    update_time  timestamp             NULL COMMENT '更新时间',
    creator      varchar(32)           NULL COMMENT '创建人',
    updater      varchar(32)           NULL COMMENT '更新人',
    deleted      bit      default b'0' NULL COMMENT '是否删除',
    deleted_time datetime DEFAULT NULL COMMENT '删除时间'
) COMMENT '租户套餐' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_tenant;
CREATE TABLE IF NOT EXISTS sys_tenant
(
    id              varchar(36)           NOT NULL PRIMARY KEY COMMENT '租户ID',
    code            varchar(32)           NOT NULL COMMENT '租户编码',
    name            varchar(100)          NULL COMMENT '租户名',
    contact_user_id varchar(32)           NULL COMMENT '租户编号',
    contact_name    varchar(32)           NULL COMMENT '联系人',
    contact_phone   varchar(32)           NULL COMMENT '联系手机',
    license_number  varchar(32)           NULL COMMENT '统一社会信用代码',
    address         varchar(32)           NULL COMMENT '地址',
    domain          varchar(32)           NULL COMMENT '域名',
    intro           varchar(32)           NULL COMMENT '简介',
    description     varchar(32)           NULL COMMENT '描述',
    websites        varchar(500)          NOT NULL COMMENT '绑定域名列表',
    package_id      varchar(32)           NULL COMMENT '租户套餐编号',
    expire_time     timestamp             NULL COMMENT '过期时间',
    account_count   int                   NULL COMMENT '账号数量',
    status          tinyint               NULL COMMENT '租户状态',
    create_time     timestamp             NULL COMMENT '创建时间',
    update_time     timestamp             NULL COMMENT '更新时间',
    creator         varchar(32)           NULL COMMENT '创建人',
    updater         varchar(32)           NULL COMMENT '更新人',
    deleted         bit      default b'0' NULL COMMENT '是否删除',
    deleted_time    datetime DEFAULT NULL COMMENT '删除时间'
) COMMENT '租户信息表' COLLATE = utf8mb4_unicode_ci;


DROP TABLE IF EXISTS sys_user;
CREATE TABLE IF NOT EXISTS sys_user
(
    id           varchar(36)                            NOT NULL primary key COMMENT '用户ID',
    username     varchar(30)                            NOT NULL COMMENT '用户账号',
    password     varchar(100) default ''                NOT NULL COMMENT '密码',
    nickname     varchar(30)                            NOT NULL COMMENT '用户昵称',
    dept_id      varchar(36)                            NOT NULL COMMENT '部门ID',
    remark       varchar(500)                           NULL COMMENT '备注',
    email        varchar(50)  default ''                NULL COMMENT '用户邮箱',
    mobile       varchar(11)  default ''                NULL COMMENT '手机号码',
    sex          tinyint      default 0                 NULL COMMENT '用户性别',
    avatar       varchar(512) default ''                NULL COMMENT '头像地址',
    status       tinyint      default 0                 NOT NULL COMMENT '帐号状态（1正常 0停用）',
    login_ip     varchar(50)  default ''                NULL COMMENT '最后登录IP',
    login_date   datetime                               NULL COMMENT '最后登录时间',
    creator      varchar(64)  default ''                NULL COMMENT '创建者',
    create_time  datetime     default CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64)  default ''                NULL COMMENT '更新者',
    update_time  datetime     default CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          default b'0'              NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL COMMENT '删除时间',
    tenant_id    bigint       default 0                 NOT NULL COMMENT '租户编号'
) COMMENT '用户信息表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_dict_type;
CREATE TABLE sys_dict_type
(
    id           varchar(36)                            NOT NULL PRIMARY KEY COMMENT '字典主键',
    code         varchar(100) DEFAULT ''                NULL COMMENT '字典编码',
    name         varchar(100) DEFAULT ''                NULL COMMENT '字典名称',
    type         varchar(100) DEFAULT ''                NULL COMMENT '字典类型：1-系统字典 2-业务字典',
    status       smallint     DEFAULT 1                 NOT NULL COMMENT '状态：1-启用 0禁用',
    remark       varchar(500) DEFAULT NULL              NULL COMMENT '备注',
    sort         int(11)                                NOT NULL DEFAULT '1' COMMENT '排序',
    creator      varchar(64)  DEFAULT ''                NULL COMMENT '创建者',
    create_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64)  DEFAULT ''                NULL COMMENT '更新者',
    update_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          DEFAULT b'0'              NOT NULL COMMENT '逻辑删除：0-未删除 1-已删除',
    deleted_time datetime     DEFAULT NULL              NULL COMMENT '删除时间',
    UNIQUE KEY `uk_dict_code` (`code`) USING BTREE,
    KEY `idx_dict_type` (`type`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE,
    KEY `idx_deleted` (`deleted`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE,
    KEY `idx_update_time` (`update_time`) USING BTREE
) COMMENT '字典主表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_dict_data;
CREATE TABLE sys_dict_data
(
    id           varchar(36)                            NOT NULL PRIMARY KEY COMMENT '主键ID',
    type_id      varchar(32)                            NOT NULL COMMENT '字典项ID',
    type_code    varchar(100)                           NOT NULL COMMENT '字典类型编码',
    parent_id    varchar(32)  DEFAULT '0'               NOT NULL COMMENT '父ID，0表示根节点',
    data_code    varchar(100) DEFAULT ''                NULL COMMENT '字典类型编码',
    data_name    varchar(100) DEFAULT ''                NULL COMMENT '字典项名称',
    status       smallint     DEFAULT 1                 NOT NULL COMMENT '状态（1正常 0停用）',
    remark       varchar(500) DEFAULT NULL              NULL COMMENT '备注',
    sort         int(11)                                NOT NULL DEFAULT '1' COMMENT '排序',
    creator      varchar(64)  DEFAULT ''                NULL COMMENT '创建者',
    create_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater      varchar(64)  DEFAULT ''                NULL COMMENT '更新者',
    update_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      bit          DEFAULT b'0'              NOT NULL COMMENT '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL COMMENT '删除时间'
) COMMENT '字典项表' COLLATE = utf8mb4_unicode_ci;

CREATE UNIQUE INDEX `uk_dict_type_id_code` ON sys_dict_data (`type_id`, `data_code`) USING BTREE;
CREATE UNIQUE INDEX `uk_dict_type_code_code` ON sys_dict_data (`type_code`, `data_code`) USING BTREE;
CREATE INDEX `idx_dict_id` ON sys_dict_data (`type_id`) USING BTREE;
CREATE INDEX `idx_dict_code` ON sys_dict_data (`type_code`) USING BTREE;
CREATE INDEX `idx_data_code` ON sys_dict_data (`data_code`) USING BTREE;
CREATE INDEX `idx_status` ON sys_dict_data (`status`) USING BTREE;
CREATE INDEX `idx_deleted` ON sys_dict_data (`deleted`) USING BTREE;
CREATE INDEX `idx_sort` ON sys_dict_data (`sort`) USING BTREE;
CREATE INDEX `idx_create_time` ON sys_dict_data (`create_time`) USING BTREE;
CREATE INDEX `idx_update_time` ON sys_dict_data (`update_time`) USING BTREE;

-- 菜单表
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE IF NOT EXISTS sys_menu
(
    id
                  varchar(36)              NOT NULL COMMENT '菜单ID' PRIMARY KEY,
    parent_id     varchar(36) DEFAULT '0' COMMENT '父ID，0表示根节点',
    ancestor_ids  VARCHAR(500) COMMENT '祖先ID列表，逗号分隔，用于快速查询层级',
    code          VARCHAR(50) COMMENT '菜单标识（英文，唯一）',
    name          VARCHAR(50) COMMENT '菜单名称（中文）',
    title         VARCHAR(50) COMMENT '菜单标题（显示名称）',
    path          VARCHAR(255) COMMENT '路由路径',
    component     VARCHAR(255) COMMENT '组件路径（Vue组件路径）',
    icon          VARCHAR(50) COMMENT '图标名称',
    sort          INT         DEFAULT 1 COMMENT '排序号，值越小越靠前',
    type          TINYINT COMMENT '类型：1目录 2菜单 3按钮 4外链',
    permission    VARCHAR(100) COMMENT '权限标识，如：sys:user:add',
    visible       TINYINT     DEFAULT 1 COMMENT '是否显示：0隐藏 1显示',
    status        TINYINT     DEFAULT 1 COMMENT '状态：0停用 1正常',
    keep_alive    TINYINT     DEFAULT 0 COMMENT '是否缓存页面：0不缓存 1缓存',
    affix         TINYINT     DEFAULT 0 COMMENT '是否固定到标签页：0不固定 1固定',
    iframe        TINYINT     DEFAULT 0 COMMENT '是否外链：0否 1是',
    cached        TINYINT     DEFAULT 0 COMMENT '是否缓存：0否 1是',
    redirect      TINYINT     DEFAULT 0 COMMENT '是否重定向：0否 1是',
    breadcrumb    TINYINT     DEFAULT 1 COMMENT '是否显示面包屑：0隐藏 1显示',
    frame_src     VARCHAR(500) COMMENT '内嵌地址（iframe链接）',
    redirect_path VARCHAR(255) COMMENT '重定向路径',
    remark        VARCHAR(500) COMMENT '备注',
    creator       varchar(64) DEFAULT ''   NULL COMMENT '更新者',
    create_time   DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater       varchar(64) DEFAULT ''   NULL COMMENT '更新人ID',
    update_time   DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted       bit         DEFAULT b'0' NOT NULL COMMENT '是否删除',
    deleted_time  datetime    DEFAULT NULL COMMENT '删除时间'
) COMMENT = '系统菜单表' COLLATE = utf8mb4_unicode_ci;

-- 添加索引
CREATE INDEX idx_parent_id on sys_menu (parent_id);
CREATE INDEX idx_sort on sys_menu (sort);
CREATE INDEX idx_type on sys_menu (type);
CREATE INDEX idx_status on sys_menu (status);

-- 角色菜单关联表
DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE IF NOT EXISTS sys_role_menu
(
    id      varchar(36) NOT NULL PRIMARY KEY COMMENT '主键ID',
    role_id varchar(36) NOT NULL COMMENT '角色ID',
    menu_id varchar(36) NOT NULL COMMENT '菜单ID'
) COMMENT = '角色菜单关联表' COLLATE = utf8mb4_unicode_ci;

-- 添加索引
CREATE INDEX idx_role_id on sys_role_menu (role_id);
CREATE INDEX idx_menu_id on sys_role_menu (menu_id);
-- 唯一约束
CREATE UNIQUE INDEX uk_role_menu on sys_role_menu (role_id, menu_id);

DROP TABLE IF EXISTS sys_config;
CREATE TABLE IF NOT EXISTS sys_config
(
    id           varchar(36)           NOT NULL PRIMARY KEY COMMENT '主键ID',
    category     varchar(100)          NOT NULL COMMENT '配置分类',
    config_name  varchar(100)          NOT NULL COMMENT '配置名称',
    config_key   varchar(100)          NOT NULL COMMENT '配置键',
    config_value varchar(100)          NOT NULL COMMENT '配置值',
    config_type  varchar(100)          NOT NULL COMMENT '配置类型',
    remark       varchar(500)          NOT NULL COMMENT '备注',
    status       TINYINT  DEFAULT 1 COMMENT '状态：0停用 1正常',
    visible      TINYINT  DEFAULT 1 COMMENT '是否显示：0隐藏 1显示',
    creator      varchar(64)           NOT NULL COMMENT '创建者',
    create_time  datetime              NOT NULL COMMENT '创建时间',
    updater      varchar(64)           NOT NULL COMMENT '更新者',
    update_time  datetime              NOT NULL COMMENT '更新时间',
    deleted      bit      DEFAULT b'0' NOT NULL COMMENT '是否删除',
    deleted_time datetime DEFAULT NULL COMMENT '删除时间'
) COMMENT = '系统配置表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_oauth2_client;
CREATE TABLE IF NOT EXISTS sys_oauth2_client
(
    id                             varchar(36)           NOT NULL PRIMARY KEY COMMENT '主键ID',
    client_id                      varchar(36)           NOT NULL COMMENT '客户端ID',
    client_secret                  varchar(36)           NOT NULL COMMENT '客户端密钥',
    name                           varchar(100)          NOT NULL COMMENT '应用名称',
    logo                           varchar(255)          NOT NULL COMMENT '应用logo地址',
    description                    varchar(255)          NOT NULL COMMENT '应用描述',
    access_token_validity_seconds  int                   NOT NULL COMMENT '访问令牌有效期',
    refresh_token_validity_seconds int                   NOT NULL COMMENT '刷新令牌有效期',
    redirect_uris                  varchar(500)          NOT NULL COMMENT '重定向URI',
    authorized_grant_types         varchar(255)          NOT NULL COMMENT '授权类型',
    scopes                         varchar(500)          NOT NULL COMMENT '范围',
    auto_approve_scopes            varchar(500)          NOT NULL COMMENT '自动批准范围',
    authorities                    varchar(36)           NOT NULL COMMENT '权限',
    resource_ids                   varchar(36)           NOT NULL COMMENT '资源ID',
    additional_information         varchar(36)           NOT NULL COMMENT '附加信息',
    status                         tinyint  DEFAULT 1    NOT NULL COMMENT '角色状态（1正常 0停用）',
    creator                        varchar(64)           NOT NULL COMMENT '创建者',
    create_time                    datetime              NOT NULL COMMENT '创建时间',
    updater                        varchar(64)           NOT NULL COMMENT '更新者',
    update_time                    datetime              NOT NULL COMMENT '更新时间',
    deleted                        bit      DEFAULT b'0' NOT NULL COMMENT '是否删除',
    deleted_time                   datetime DEFAULT NULL COMMENT '删除时间'

) COMMENT ='OAuth2 客户端信息表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_oauth2_refresh_token;
CREATE TABLE IF NOT EXISTS sys_oauth2_refresh_token
(
    id            varchar(36)  NOT NULL PRIMARY KEY COMMENT '主键ID',
    user_id       varchar(36)  NOT NULL COMMENT '用户ID',
    refresh_token varchar(32)  NOT NULL COMMENT '刷新令牌',
    user_type     int2         NOT NULL COMMENT '用户类型：1-用户 2-客户端',
    client_id     varchar(255) NOT NULL COMMENT '客户端ID',
    scopes        varchar(255) NULL     DEFAULT NULL COMMENT '范围',
    expire_time   datetime     NOT NULL COMMENT '过期时间',
    creator       varchar(64)  NULL     DEFAULT '' COMMENT '创建者',
    create_time   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater       varchar(64)  NULL     DEFAULT '' COMMENT '更新者',
    update_time   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted       bit          NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id     varchar(36)  NOT NULL DEFAULT 0 COMMENT '租户ID'
) COMMENT ='OAuth2 刷新令牌信息表' COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS sys_oauth2_access_token;
CREATE TABLE sys_oauth2_access_token
(
    id            varchar(36)               NOT NULL PRIMARY KEY COMMENT '主键ID',
    user_id       varchar(36)               NOT NULL COMMENT '用户ID',
    user_type     smallint                  NOT NULL COMMENT '用户类型：1-用户 2-客户端',
    user_info     varchar(512)              NOT NULL COMMENT '用户信息',
    access_token  varchar(255)              NOT NULL COMMENT '访问令牌',
    refresh_token varchar(32)               NOT NULL COMMENT '刷新令牌',
    client_id     varchar(255)              NOT NULL COMMENT '客户端ID',
    scopes        varchar(255) DEFAULT NULL NULL COMMENT '范围',
    expires_time  datetime                  NOT NULL COMMENT '过期时间',
    creator       varchar(64)  DEFAULT ''   NULL COMMENT '创建者',
    create_time   datetime                  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater       varchar(64)  DEFAULT ''   NULL COMMENT '更新者',
    update_time   datetime                  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted       bit          DEFAULT b'0' NOT NULL COMMENT '是否删除',
    tenant_id     varchar(36)               NOT NULL DEFAULT 0 COMMENT '租户ID'
) COMMENT ='OAuth2 访问令牌信息表' COLLATE = utf8mb4_unicode_ci;

