create table sys_dept
(
    id             varchar(32)                           not null comment '部门id'
        primary key,
    name           varchar(30) default ''                not null comment '部门名称',
    parent_id      varchar(32)                           not null comment '父部门id',
    sort           int         default 0                 not null comment '显示顺序',
    leader_user_id bigint                                null comment '负责人',
    phone          varchar(11)                           null comment '联系电话',
    email          varchar(50)                           null comment '邮箱',
    status         tinyint                               not null comment '部门状态（0正常 1停用）',
    creator        varchar(64) default ''                null comment '创建者',
    create_time    datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater        varchar(64) default ''                null comment '更新者',
    update_time    datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted        bit         default b'0'              not null comment '是否删除',
    tenant_id      bigint      default 0                 not null comment '租户编号'
)
    comment '部门表' collate = utf8mb4_unicode_ci;

create table sys_login_log
(
    id          varchar(32)                           not null comment '访问ID'
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

create table sys_tenant
(
    id              varchar(32)  null comment '租户编号',
    name            varchar(100) null comment '租户名',
    contact_user_id varchar(32)  null comment '租户编号',
    contact_name    varchar(32)  null comment '联系人',
    contact_mobile  varchar(32)  null comment '联系手机',
    status          tinyint      null comment '租户状态',
    website         varchar(32)  null comment '绑定域名',
    package_id      varchar(32)  null comment '租户套餐编号',
    expire_time     timestamp    null comment '过期时间',
    account_count   int          null comment '账号数量',
    create_time     timestamp    null comment '创建时间',
    update_time     timestamp    null comment '更新时间',
    creator         varchar(32)  null comment '创建人',
    updater         varchar(32)  null comment '更新人',
    deleted         tinyint(1)   null comment '是否删除'
);

create table sys_users
(
    id          varchar(32)                            not null comment '用户ID'
        primary key,
    username    varchar(30)                            not null comment '用户账号',
    password    varchar(100) default ''                not null comment '密码',
    nickname    varchar(30)                            not null comment '用户昵称',
    remark      varchar(500)                           null comment '备注',
    dept_id     bigint                                 null comment '部门ID',
    post_ids    varchar(255)                           null comment '岗位编号数组',
    email       varchar(50)  default ''                null comment '用户邮箱',
    mobile      varchar(11)  default ''                null comment '手机号码',
    sex         tinyint      default 0                 null comment '用户性别',
    avatar      varchar(512) default ''                null comment '头像地址',
    status      tinyint      default 0                 not null comment '帐号状态（0正常 1停用）',
    login_ip    varchar(50)  default ''                null comment '最后登录IP',
    login_date  datetime                               null comment '最后登录时间',
    creator     varchar(64)  default ''                null comment '创建者',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64)  default ''                null comment '更新者',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit          default b'0'              not null comment '是否删除',
    tenant_id   bigint       default 0                 not null comment '租户编号'
)
    comment '用户信息表' collate = utf8mb4_unicode_ci;

