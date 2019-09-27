/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-09-27 17:26:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for label_relation
-- ----------------------------
DROP TABLE IF EXISTS `label_relation`;
CREATE TABLE `label_relation` (
  `id` varchar(32) NOT NULL,
  `label_group_name` varchar(50) NOT NULL COMMENT '标签分组名',
  `label_name` varchar(50) NOT NULL COMMENT '标签名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `label_name` (`label_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of label_relation
-- ----------------------------
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a2e0000', 'discipline', 'WebSocket');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3a0001', 'discipline', 'Vuex');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3a0002', 'browser', 'Chrome');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3a0003', 'discipline', 'jQuery');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3a0004', 'discipline', '正则表达式');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3a0005', 'network', 'HTTP');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3a0006', 'database', 'MySQL');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3b0007', 'discipline', 'ECMAScript 6');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3b0008', 'discipline', 'Git');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3b0009', 'language', 'HTML');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3b000a', 'discipline', '设计模式');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3b000b', 'codeLife', '代码规范');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3b000c', 'design', '图片资源');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3c000d', 'system', 'Linux');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3c000e', 'AI', '机器学习');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3c000f', 'system', 'Android');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3c0010', 'system', 'iOS');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3c0011', 'language', 'Java');
INSERT INTO `label_relation` VALUES ('40281a816c64558a016c64602a3c0012', 'language', 'JavaScript');
