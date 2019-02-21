/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : wjysushe

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 21/02/2019 18:09:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apartment
-- ----------------------------
DROP TABLE IF EXISTS `apartment`;
CREATE TABLE `apartment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '公寓唯一id',
  `manager_id` int(11) UNSIGNED NOT NULL COMMENT '管理员id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公寓基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '寝室唯一id',
  `apartment_id` int(11) UNSIGNED NOT NULL COMMENT '公寓楼id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '寝室基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dormitory_examination
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_examination`;
CREATE TABLE `dormitory_examination`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `examination_date` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '查寝日期',
  `dormitory_id` int(11) UNSIGNED NOT NULL COMMENT '寝室id',
  `people_number` int(11) UNSIGNED NOT NULL COMMENT '应到人数',
  `real_number` int(11) UNSIGNED NOT NULL COMMENT '实际人数',
  `student_ids` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '未到学生id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '查寝表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dormitory_student
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_student`;
CREATE TABLE `dormitory_student`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '寝室唯一id',
  `dormitory_id` int(11) UNSIGNED NOT NULL COMMENT '寝室id',
  `student_id` int(11) UNSIGNED NOT NULL COMMENT '学生id',
  `status` int(4) UNSIGNED NOT NULL COMMENT '状态,1入住,2迁出',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '寝室和学生的关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repairs
-- ----------------------------
DROP TABLE IF EXISTS `repairs`;
CREATE TABLE `repairs`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户id',
  `dormitory_id` int(11) UNSIGNED NOT NULL COMMENT '寝室id',
  `explanation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `reply` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回复',
  `status` int(4) UNSIGNED NOT NULL COMMENT '状态,1未处理,2已处理',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '报修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `role` int(11) NOT NULL COMMENT '用户身份',
  `sno` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户账号创建时间',
  `last_update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次更新记录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_on_username`(`name`) USING BTREE COMMENT '用户名索引'
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (9, 1, '110', '管理员', '123', '2019-02-20 12:21:16', '2019-02-20 12:21:16');

-- ----------------------------
-- View structure for view_sys_user
-- ----------------------------
DROP VIEW IF EXISTS `view_sys_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `view_sys_user` AS select `t1`.`id` AS `id`,`t1`.`login_name` AS `login_name`,`t1`.`nick_name` AS `nick_name`,`t1`.`pwd` AS `pwd`,`t1`.`salt` AS `salt`,`t1`.`email` AS `email`,`t1`.`mobile` AS `mobile`,`t1`.`addr` AS `addr`,`t1`.`company_id` AS `company_id`,`t1`.`user_type` AS `user_type`,`t1`.`status_id` AS `status_id`,`t2`.`name` AS `company_name` from (`sys_user_info` `t1` left join `company` `t2` on((`t1`.`company_id` = `t2`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
