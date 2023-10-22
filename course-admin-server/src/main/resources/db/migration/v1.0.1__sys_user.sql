CREATE TABLE `sys_user`
(
    `id`            bigint                                                        NOT NULL COMMENT '主键',
    `create_time`   datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `update_time`   datetime                                                      NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
    `deleted`       smallint                                                      NULL DEFAULT 0 COMMENT '软删除字段；1 - 已删除，0 - 未删除',
    `username`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'LOCAL' COMMENT '用户名 就是账号',
    `password`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码MD5(加密)',
    `display_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
    `avatar_id`     bigint                                                        NULL DEFAULT NULL COMMENT '头像id',
    `role_id`       bigint                                                        NULL DEFAULT NULL COMMENT '角色id',
    `identity_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
    `mobile`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;


INSERT INTO `sys_user`
VALUES (1630022714263441409, '2023-02-27 09:51:14', '2023-02-27 21:19:06', 0, 'admin',
        '99445B5B9643A1BED5C42BB25FB6A092F37B463010511C435A4032AE', '362951261674536960', 1630023499424534530,
        513030199707012630, '18962604168', '超级管理员');
