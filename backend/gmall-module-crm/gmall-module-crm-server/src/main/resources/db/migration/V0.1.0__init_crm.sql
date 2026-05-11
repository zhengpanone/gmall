CREATE TABLE IF NOT EXISTS crm_product
(
    id            varchar(36)    NOT NULL COMMENT '主键ID' PRIMARY KEY,
    name          varchar(100)   NOT NULL COMMENT '产品名称',
    no            varchar(100)   NOT NULL COMMENT '产品编号',
    unit          varchar(100)   NOT NULL COMMENT '单位',
    price         decimal(10, 2) NOT NULL COMMENT '单价',
    category_id   varchar(36)    NOT NULL COMMENT '分类ID',
    description   varchar(255)   NOT NULL COMMENT '品牌描述',
    sort          int            NOT NULL COMMENT '排序',
    owner_user_id varchar(36)    NOT NULL COMMENT '所有者用户ID',
    status        TINYINT DEFAULT 1 COMMENT '状态：0停用 1正常',
    creator       varchar(64)    NOT NULL COMMENT '创建者',
    create_time   datetime       NOT NULL COMMENT '创建时间',
    updater       varchar(64)    NOT NULL COMMENT '更新者',
    update_time   datetime       NOT NULL COMMENT '更新时间',
    deleted       bit            NOT NULL COMMENT '是否删除',
    deleted_time  datetime       NOT NULL COMMENT '删除时间'
) COMMENT = 'crm 产品表';


CREATE TABLE IF NOT EXISTS crm_product_category
(
    id           varchar(36)  NOT NULL COMMENT '主键ID' PRIMARY KEY,
    name         varchar(100) NOT NULL COMMENT '分类名称',
    parent_id    varchar(36)  NOT NULL COMMENT '父分类ID',
    level        TINYINT DEFAULT 1 COMMENT '分类级别',
    sort         int          NOT NULL COMMENT '排序',
    status       TINYINT DEFAULT 1 COMMENT '状态：0停用 1正常',
    creator      varchar(64)  NOT NULL COMMENT '创建者',
    create_time  datetime     NOT NULL COMMENT '创建时间',
    updater      varchar(64)  NOT NULL COMMENT '更新者',
    update_time  datetime     NOT NULL COMMENT '更新时间',
    deleted      bit          NOT NULL COMMENT '是否删除',
    deleted_time datetime     NOT NULL COMMENT '删除时间'
) COMMENT = 'crm 产品分类表';