/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.35.80
 Source Server Type    : MySQL
 Source Server Version : 50647
 Source Host           : 192.168.35.80:3306
 Source Schema         : shiro_test

 Target Server Type    : MySQL
 Target Server Version : 50647
 File Encoding         : 65001

 Date: 15/04/2020 11:10:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pe_permission
-- ----------------------------
DROP TABLE IF EXISTS `pe_permission`;
CREATE TABLE `pe_permission`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编号',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '权限描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_permission
-- ----------------------------
INSERT INTO `pe_permission` VALUES ('1', '添加用户', 'user-add', NULL);
INSERT INTO `pe_permission` VALUES ('2', '查询用户', 'user-find', NULL);
INSERT INTO `pe_permission` VALUES ('3', '更新用户', 'user-update', NULL);
INSERT INTO `pe_permission` VALUES ('4', '删除用户', 'user-delete', NULL);
INSERT INTO `pe_permission` VALUES ('5', '用户主页', 'user-home', NULL);

-- ----------------------------
-- Table structure for pe_role
-- ----------------------------
DROP TABLE IF EXISTS `pe_role`;
CREATE TABLE `pe_role`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_role
-- ----------------------------
INSERT INTO `pe_role` VALUES ('1', '系统管理员', '系统日常维护');
INSERT INTO `pe_role` VALUES ('2', '普通员工', '普通操作权限');

-- ----------------------------
-- Table structure for pe_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `pe_role_permission`;
CREATE TABLE `pe_role_permission`  (
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `permission_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `fk_permission_id`(`permission_id`) USING BTREE,
  INDEX `fk_role_id`(`role_id`) USING BTREE,
  CONSTRAINT `fk-pid` FOREIGN KEY (`permission_id`) REFERENCES `pe_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk-rid` FOREIGN KEY (`role_id`) REFERENCES `pe_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_role_permission
-- ----------------------------
INSERT INTO `pe_role_permission` VALUES ('1', '1');
INSERT INTO `pe_role_permission` VALUES ('1', '2');
INSERT INTO `pe_role_permission` VALUES ('2', '2');
INSERT INTO `pe_role_permission` VALUES ('1', '3');
INSERT INTO `pe_role_permission` VALUES ('1', '4');
INSERT INTO `pe_role_permission` VALUES ('1', '5');

-- ----------------------------
-- Table structure for pe_user
-- ----------------------------
DROP TABLE IF EXISTS `pe_user`;
CREATE TABLE `pe_user`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_user
-- ----------------------------
INSERT INTO `pe_user` VALUES ('1', 'zhangsan', '416266ea7acf8e44a209d2277cb2d6e1');
INSERT INTO `pe_user` VALUES ('2', 'lisi', '4d40213f2b710eae75eb44a4bcc30684');

-- ----------------------------
-- Table structure for pe_user_role
-- ----------------------------
DROP TABLE IF EXISTS `pe_user_role`;
CREATE TABLE `pe_user_role`  (
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `fk_user_id`(`user_id`) USING BTREE,
  INDEX `fk_role_id`(`role_id`) USING BTREE,
  CONSTRAINT `fk-role-id` FOREIGN KEY (`role_id`) REFERENCES `pe_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk-user-id` FOREIGN KEY (`user_id`) REFERENCES `pe_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pe_user_role
-- ----------------------------
INSERT INTO `pe_user_role` VALUES ('1', '1');
INSERT INTO `pe_user_role` VALUES ('2', '2');

SET FOREIGN_KEY_CHECKS = 1;
