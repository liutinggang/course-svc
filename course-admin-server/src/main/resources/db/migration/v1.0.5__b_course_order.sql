CREATE TABLE `b_course_order`
(
    `id`           bigint    NOT NULL COMMENT '主键',
    `create_time`  timestamp NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `update_time`  timestamp NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
    `deleted`      smallint  NOT NULL DEFAULT 0 COMMENT '软删除字段；1 - 已删除，0 - 未删除',
    `user_id`      bigint    NOT NULL COMMENT '用户id',
    `course_id`    bigint    NOT NULL COMMENT '课程id',
    `order_status` tinyint   NULL     DEFAULT NULL COMMENT '订单状态:0提交 1学习中 2学习完成',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;
