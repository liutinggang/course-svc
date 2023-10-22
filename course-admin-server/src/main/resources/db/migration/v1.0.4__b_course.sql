CREATE TABLE `b_course`
(
    `id`            bigint                                                        NOT NULL COMMENT '主键',
    `create_time`   timestamp                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `update_time`   timestamp                                                     NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
    `deleted`       smallint                                                      NOT NULL DEFAULT 0 COMMENT '软删除字段；1 - 已删除，0 - 未删除',
    `course_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '项目名称',
    `price`         double                                                        NULL     DEFAULT 0 COMMENT '价格',
    `price_type`    tinyint                                                       NULL     DEFAULT 0 COMMENT '价格',
    `file_ids`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '文件id',
    `course_status` tinyint                                                       NULL     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;
