CREATE TABLE `sys_role`
(
    `id`          bigint                                                        NOT NULL COMMENT '主键',
    `create_time` datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `update_time` datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',

    `deleted`     smallint                                                      NULL DEFAULT 0 COMMENT '软删除字段；1 - 已删除，0 - 未删除',
    `role_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'LOCAL' COMMENT '默认本地',
    `role_code`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

INSERT INTO `sys_role`
VALUES (1715373545258491906, '2023-10-20 22:24:58', '2023-10-20 22:26:15', 0, '超级管理员', 'admin');
INSERT INTO `sys_role`
VALUES (1715374223729180674, '2023-10-20 22:27:40', '2023-10-20 22:27:52', 0, '普通会员', 'consumer');
