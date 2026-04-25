# CREATE DATABASE IF NOT EXISTS gmall_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS sys_role
(
    id          varchar(36)                            NOT NULL COMMENT '角色ID' PRIMARY KEY,
    name        varchar(30)                            NOT NULL COMMENT '角色名称',
    code        varchar(100)                           NOT NULL COMMENT '角色权限字符串',
    sort        int                                    NOT NULL DEFAULT 1 COMMENT '显示顺序',
    status      tinyint      DEFAULT 0                 NOT NULL COMMENT '角色状态（0正常 1停用）',
    type        tinyint                                NOT NULL COMMENT '角色类型',
    remark      varchar(500) DEFAULT NULL              NULL COMMENT '备注',
    creator     varchar(64)  DEFAULT ''                NULL COMMENT '创建者',
    create_time datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater     varchar(64)  DEFAULT ''                NULL COMMENT '更新者',
    update_time datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted     bit          DEFAULT b'0'              NOT NULL COMMENT '是否删除',
    tenant_id   bigint       DEFAULT 0                 NOT NULL COMMENT '租户编号'
) COMMENT '角色信息表' COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS sys_dept
(
    id             varchar(36)                           NOT NULL COMMENT '部门id'
        primary key,
    name           varchar(30) DEFAULT ''                NOT NULL COMMENT '部门名称',
    parent_id      varchar(32)                           NOT NULL COMMENT '父部门id',
    sort           int         DEFAULT 0                 NOT NULL COMMENT '显示顺序',
    leader_user_id bigint                                NULL COMMENT '负责人',
    phone          varchar(11)                           NULL COMMENT '联系电话',
    email          varchar(50)                           NULL COMMENT '邮箱',
    status         tinyint     DEFAULT 0                 NOT NULL COMMENT '部门状态（0正常 1停用）',
    creator        varchar(64) DEFAULT ''                NULL COMMENT '创建者',
    create_time    datetime    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updater        varchar(64) DEFAULT ''                NULL COMMENT '更新者',
    update_time    datetime    DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted        bit         DEFAULT b'0'              NOT NULL COMMENT '是否删除',
    tenant_id      bigint      DEFAULT 0                 NOT NULL COMMENT '租户编号'
)
    comment '部门表' collate = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS sys_login_log
(
    id          varchar(36)                           not null comment '访问ID'
        primary key,
    log_type    bigint                                not null comment '日志类型',
    trace_id    varchar(64) default ''                not null comment '链路追踪编号',
    user_id     bigint      default 0                 not null comment '用户编号',
    user_type   tinyint     default 0                 not null comment '用户类型',
    username    varchar(50) default ''                not null comment '用户账号',
    result      tinyint                               not null comment '登陆结果',
    user_ip     varchar(50)                           not null comment '用户 IP',
    user_agent  varchar(512)                          not null comment '浏览器 UA',
    creator     varchar(64) default ''                null comment '创建者',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64) default ''                null comment '更新者',
    update_time datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit         default b'0'              not null comment '是否删除',
    tenant_id   bigint      default 0                 not null comment '租户编号'
)
    comment '系统访问记录' collate = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS sys_tenant
(
    id              varchar(36)      null comment '租户编号',
    name            varchar(100)     null comment '租户名',
    contact_user_id varchar(32)      null comment '租户编号',
    contact_name    varchar(32)      null comment '联系人',
    contact_mobile  varchar(32)      null comment '联系手机',
    status          tinyint          null comment '租户状态',
    website         varchar(32)      null comment '绑定域名',
    package_id      varchar(32)      null comment '租户套餐编号',
    expire_time     timestamp        null comment '过期时间',
    account_count   int              null comment '账号数量',
    create_time     timestamp        null comment '创建时间',
    update_time     timestamp        null comment '更新时间',
    creator         varchar(32)      null comment '创建人',
    updater         varchar(32)      null comment '更新人',
    deleted         bit default b'0' null comment '是否删除'
);

create table sys_user
(
    id           varchar(36)                            not null comment '用户ID'
        primary key,
    username     varchar(30)                            not null comment '用户账号',
    password     varchar(100) default ''                not null comment '密码',
    nickname     varchar(30)                            not null comment '用户昵称',
    remark       varchar(500)                           null comment '备注',
    email        varchar(50)  default ''                null comment '用户邮箱',
    mobile       varchar(11)  default ''                null comment '手机号码',
    sex          tinyint      default 0                 null comment '用户性别',
    avatar       varchar(512) default ''                null comment '头像地址',
    status       tinyint      default 0                 not null comment '帐号状态（0正常 1停用）',
    login_ip     varchar(50)  default ''                null comment '最后登录IP',
    login_date   datetime                               null comment '最后登录时间',
    creator      varchar(64)  default ''                null comment '创建者',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater      varchar(64)  default ''                null comment '更新者',
    update_time  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted      bit          default b'0'              not null comment '是否删除',
    deleted_time datetime     DEFAULT NULL              NULL comment '删除时间',
    tenant_id    bigint       default 0                 not null comment '租户编号'
)
    comment '用户信息表' collate = utf8mb4_unicode_ci;


CREATE TABLE sys_dict
(
    id           varchar(36)                            NOT NULL comment '字典主键' PRIMARY KEY,
    dict_code    varchar(100) DEFAULT ''                NULL comment '字典编码',
    dict_name    varchar(100) DEFAULT ''                NULL comment '字典名称',
    dict_type    varchar(100) DEFAULT ''                NULL comment '字典类型：1-系统字典 2-业务字典',
    status       smallint     DEFAULT 0                 NOT NULL comment '状态：0-启用 1禁用',
    remark       varchar(500) DEFAULT NULL              NULL comment '备注',
    sort         int(11)                                NOT NULL DEFAULT '0' COMMENT '排序',
    creator      varchar(64)  DEFAULT ''                NULL comment '创建者',
    create_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL comment '创建时间',
    updater      varchar(64)  DEFAULT ''                NULL comment '更新者',
    update_time  datetime     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    deleted      bit          DEFAULT b'0'              NOT NULL comment '逻辑删除：0-未删除 1-已删除',
    deleted_time datetime     DEFAULT NULL              NULL comment '删除时间',
    UNIQUE KEY `uk_dict_code` (`dict_code`) USING BTREE,
    KEY `idx_dict_type` (`dict_type`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE,
    KEY `idx_deleted` (`deleted`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE,
    KEY `idx_update_time` (`update_time`) USING BTREE
) comment '字典主表' collate = utf8mb4_unicode_ci;

CREATE TABLE sys_dict_item
(
    id           varchar(36)                             NOT NULL comment '主键ID' PRIMARY KEY,
    dict_id      varchar(32)                             NOT NULL COMMENT '字典ID',
    item_code    varchar(100)  DEFAULT ''                NULL comment '字典项编码',
    item_name    varchar(100)  DEFAULT ''                NULL comment '字典项名称',
    item_value   varchar(500)                            NOT NULL COMMENT '字典项值',
    extend_info  varchar(1000) DEFAULT NULL COMMENT '扩展信息',
    status       smallint      DEFAULT 0                 NOT NULL comment '状态（0正常 1停用）',
    remark       varchar(500)  DEFAULT NULL              NULL comment '备注',
    sort         int(11)                                 NOT NULL DEFAULT '0' COMMENT '排序',
    creator      varchar(64)   DEFAULT ''                NULL comment '创建者',
    create_time  datetime      DEFAULT CURRENT_TIMESTAMP NOT NULL comment '创建时间',
    updater      varchar(64)   DEFAULT ''                NULL comment '更新者',
    update_time  datetime      DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    deleted      bit           DEFAULT b'0'              NOT NULL comment '是否删除',
    deleted_time datetime      DEFAULT NULL              NULL comment '删除时间',
    UNIQUE KEY `uk_dict_item_code` (`dict_id`, `item_code`) USING BTREE,
    KEY `idx_dict_id` (`dict_id`) USING BTREE,
    KEY `idx_item_code` (`item_code`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE,
    KEY `idx_deleted` (`deleted`) USING BTREE,
    KEY `idx_sort` (`sort`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE,
    KEY `idx_update_time` (`update_time`) USING BTREE
) comment '字典项表' collate = utf8mb4_unicode_ci;

-- 优化后的菜单表
CREATE TABLE IF NOT EXISTS sys_menu
(
    id           varchar(36) not null comment '菜单ID' PRIMARY KEY,
    parent_id    varchar(36) DEFAULT '0' COMMENT '父ID，0表示根节点',
    ancestor_ids VARCHAR(500) COMMENT '祖先ID列表，逗号分隔，用于快速查询层级',
    name         VARCHAR(50) COMMENT '菜单名称（英文标识，用于路由name）',
    title        VARCHAR(50) COMMENT '菜单标题（显示名称）',
    path         VARCHAR(255) COMMENT '路由路径',
    component    VARCHAR(255) COMMENT '组件路径（Vue组件路径）',
    icon         VARCHAR(50) COMMENT '图标名称',
    sort         INT         DEFAULT 0 COMMENT '排序号，值越小越靠前',
    type         TINYINT COMMENT '类型：0目录 1菜单 2按钮 3外链',
    permission   VARCHAR(100) COMMENT '权限标识，如：sys:user:add',
    visible      TINYINT     DEFAULT 1 COMMENT '是否显示：0隐藏 1显示',
    status       TINYINT     DEFAULT 1 COMMENT '状态：0停用 1正常',
    keep_alive   TINYINT     DEFAULT 0 COMMENT '是否缓存页面：0不缓存 1缓存',
    affix        TINYINT     DEFAULT 0 COMMENT '是否固定到标签页：0不固定 1固定',
    frame_src    VARCHAR(500) COMMENT '内嵌地址（iframe链接）',
    redirect     VARCHAR(255) COMMENT '重定向路径',
    active_menu  VARCHAR(255) COMMENT '激活的菜单，用于隐藏菜单时设置',
    breadcrumb   TINYINT     DEFAULT 1 COMMENT '是否显示面包屑：0隐藏 1显示',
    remark       VARCHAR(500) COMMENT '备注',
    create_by    BIGINT COMMENT '创建人ID',
    update_by    BIGINT COMMENT '更新人ID',
    create_time  DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    -- 添加索引
    INDEX idx_parent_id (parent_id),
    INDEX idx_sort (sort),
    INDEX idx_type (type),
    INDEX idx_status (status)

) COMMENT = '系统菜单表';

-- 优化后的角色菜单关联表
CREATE TABLE IF NOT EXISTS sys_role_menu
(
    id      varchar(36) not null comment '主键ID' PRIMARY KEY,
    role_id varchar(36) NOT NULL COMMENT '角色ID',
    menu_id varchar(36) NOT NULL COMMENT '菜单ID',

    -- 添加索引
    INDEX idx_role_id (role_id),
    INDEX idx_menu_id (menu_id),

    -- 唯一约束
    UNIQUE uk_role_menu (role_id, menu_id)
) COMMENT = '角色菜单关联表';