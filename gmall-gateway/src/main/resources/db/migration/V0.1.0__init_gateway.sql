CREATE TABLE gateway_rate_limit
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    route_id     VARCHAR(255) NOT NULL,
    window_size  BIGINT    DEFAULT 1000, -- 窗口时间，单位毫秒
    max_requests INT       DEFAULT 5,    -- 最大请求数
    create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='限流表';

CREATE TABLE gateway_route
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    route_id    VARCHAR(64)  NOT NULL COMMENT '路由标识',
    uri         VARCHAR(255) NOT NULL COMMENT '目标服务URI',
    predicates  TEXT COMMENT '断言规则（JSON格式）',
    filters     TEXT COMMENT '过滤器链（JSON格式）',
    orders      INT        DEFAULT 0 COMMENT '路由优先级',
    enabled     TINYINT(1) DEFAULT 1 COMMENT '是否启用，1-启用，0-禁用',
    create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='网关路由表';
