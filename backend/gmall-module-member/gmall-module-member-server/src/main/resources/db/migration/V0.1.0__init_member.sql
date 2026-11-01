CREATE TABLE IF NOT EXISTS member_user
(
    id                  varchar(36) PRIMARY KEY COMMENT '主键',
    mobile              varchar(11)  NOT NULL COMMENT '手机号',
    password            varchar(32)  NOT NULL COMMENT '密码',
    nickname            varchar(32)  NOT NULL COMMENT '昵称',
    avatar_url          varchar(255) NOT NULL COMMENT '头像',
    sex                 TINYINT DEFAULT 0 COMMENT '性别：0未知 1男 2女',
    birthday            datetime COMMENT '生日',
    country             varchar(32) COMMENT '国家',
    city                varchar(32) COMMENT '城市',
    province            varchar(32) COMMENT '省份',
    area                varchar(32) COMMENT '区域',
    address             varchar(255) COMMENT '地址',
    remark              varchar(255) COMMENT '备注',
    register_ip         varchar(32)  NOT NULL COMMENT '注册IP',
    register_time       datetime     NOT NULL COMMENT '注册时间',
    register_terminal   varchar(32)  NOT NULL COMMENT '注册终端',
    last_login_ip       varchar(32)  NOT NULL COMMENT '最后登录IP',
    last_login_time     datetime     NOT NULL COMMENT '最后登录时间',
    last_login_terminal varchar(32)  NOT NULL COMMENT '最后登录终端',
    status              TINYINT DEFAULT 1 COMMENT '状态：0停用 1正常',
    creator             varchar(64)  NOT NULL COMMENT '创建者',
    create_time         datetime     NOT NULL COMMENT '创建时间',
    updater             varchar(64)  NOT NULL COMMENT '更新者',
    update_time         datetime     NOT NULL COMMENT '更新时间',
    deleted             bit          NOT NULL COMMENT '是否删除',
    deleted_time        datetime     NOT NULL COMMENT '删除时间'
) COMMENT '会员用户表';

CREATE TABLE IF NOT EXISTS member_address
(
    id           varchar(36) PRIMARY KEY COMMENT '主键',
    user_id      varchar(36)  NOT NULL COMMENT '用户ID',
    name         varchar(32)  NOT NULL COMMENT '收件人',
    mobile       varchar(11)  NOT NULL COMMENT '手机号',
    country      varchar(32)  NOT NULL COMMENT '国家',
    city         varchar(32)  NOT NULL COMMENT '城市',
    province     varchar(32)  NOT NULL COMMENT '省份',
    area         varchar(32)  NOT NULL COMMENT '区域',
    address      varchar(255) NOT NULL COMMENT '地址',
    is_default   TINYINT DEFAULT 0 COMMENT '是否默认：0否 1是',
    remark       varchar(255) COMMENT '备注',
    status       TINYINT DEFAULT 1 COMMENT '状态：0停用 1正常',
    creator      varchar(64)  NOT NULL COMMENT '创建者',
    create_time  datetime     NOT NULL COMMENT '创建时间',
    updater      varchar(64)  NOT NULL COMMENT '更新者',
    update_time  datetime     NOT NULL COMMENT '更新时间',
    deleted      bit          NOT NULL COMMENT '是否删除',
    deleted_time datetime     NOT NULL COMMENT '删除时间'
) COMMENT '用户收货地址表';

CREATE TABLE IF NOT EXISTS member_tag
(
    id           varchar(36) PRIMARY KEY COMMENT '主键',
    name         varchar(32) NOT NULL COMMENT '标签名称',
    type         TINYINT DEFAULT 0 COMMENT '标签类型：0未知 1用户标签 2商品标签',
    status       TINYINT DEFAULT 1 COMMENT '状态：0停用 1正常',
    creator      varchar(64) NOT NULL COMMENT '创建者',
    create_time  datetime    NOT NULL COMMENT '创建时间',
    updater      varchar(64) NOT NULL COMMENT '更新者',
    update_time  datetime    NOT NULL COMMENT '更新时间',
    deleted      bit         NOT NULL COMMENT '是否删除',
    deleted_time datetime    NOT NULL COMMENT '删除时间'
);
