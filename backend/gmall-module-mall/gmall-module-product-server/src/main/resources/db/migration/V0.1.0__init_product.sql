CREATE TABLE IF NOT EXISTS product_brand
(
    id           varchar(36)          NOT NULL COMMENT '主键ID' PRIMARY KEY,
    name         varchar(100)         NOT NULL COMMENT '品牌名称',
    logo_url     varchar(255)         NULL COMMENT '品牌logo',
    big_pic_url  varchar(255)         NULL COMMENT '品牌大图',
    description  varchar(255)         NULL COMMENT '品牌描述',
    sort         int                  NULL COMMENT '排序',
    status       TINYINT DEFAULT 1 COMMENT '状态：0停用 1正常',
    creator      varchar(64)          NOT NULL COMMENT '创建者',
    create_time  datetime             NOT NULL COMMENT '创建时间',
    updater      varchar(64)          NOT NULL COMMENT '更新者',
    update_time  datetime             NOT NULL COMMENT '更新时间',
    deleted      bit     DEFAULT b'0' NOT NULL COMMENT '是否删除',
    deleted_time datetime             NULL COMMENT '删除时间'
) COMMENT = '商品品牌表';


CREATE TABLE IF NOT EXISTS product_category
(
    id           varchar(36)          NOT NULL COMMENT '主键ID' PRIMARY KEY,
    name         varchar(100)         NOT NULL COMMENT '分类名称',
    parent_id    varchar(36)          NOT NULL COMMENT '父分类ID',
    level        TINYINT DEFAULT 1 COMMENT '分类级别',
    pic_url      varchar(255)         NULL COMMENT '分类图片',
    sort         int     DEFAULT 1    NOT NULL COMMENT '排序',
    status       TINYINT DEFAULT 1 COMMENT '状态：0停用 1正常',
    creator      varchar(64)          NOT NULL COMMENT '创建者',
    create_time  datetime             NOT NULL COMMENT '创建时间',
    updater      varchar(64)          NOT NULL COMMENT '更新者',
    update_time  datetime             NOT NULL COMMENT '更新时间',
    deleted      bit     DEFAULT b'0' NOT NULL COMMENT '是否删除',
    deleted_time datetime             NULL COMMENT '删除时间'
) COMMENT = '商品分类表';