/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-09-29 17:15:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `password` varchar(60) NOT NULL COMMENT '密码',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  `role` varchar(20) NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '123456', 'songning', 'ADMIN');
INSERT INTO `users` VALUES ('2', '123456', 'haozhou', 'USER');
INSERT INTO `users` VALUES ('3', '123456', 'shijie', 'USER');
INSERT INTO `users` VALUES ('4', '123456', 'shenkeye', 'USER');
