/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-09-27 17:26:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for label_group
-- ----------------------------
DROP TABLE IF EXISTS `label_group`;
CREATE TABLE `label_group` (
  `id` varchar(32) NOT NULL,
  `description` varchar(50) NOT NULL COMMENT '标签分组描述',
  `label_group_name` varchar(50) NOT NULL COMMENT '标签分组名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `description` (`description`),
  UNIQUE KEY `label_group_name` (`label_group_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of label_group
-- ----------------------------
INSERT INTO `label_group` VALUES ('40281a816c644c24016c644c55d30000', '代码人生', 'codeLife');
INSERT INTO `label_group` VALUES ('40281a816c644c24016c644c55ff0001', '人工智能', 'AI');
INSERT INTO `label_group` VALUES ('40281a816c644c24016c644c56000002', '设计', 'design');
INSERT INTO `label_group` VALUES ('40281a816c644c24016c644c56000003', '学科', 'discipline');
INSERT INTO `label_group` VALUES ('40281a816c644c24016c644c56000004', '编程语言', 'language');
INSERT INTO `label_group` VALUES ('40281a816c644c24016c644c56010005', '浏览器', 'browser');
INSERT INTO `label_group` VALUES ('40281a816c644c24016c644c56010006', '网络', 'network');
INSERT INTO `label_group` VALUES ('40281a816c644c24016c644c56040007', '数据库', 'database');
INSERT INTO `label_group` VALUES ('40281a816c64558a016c6461add60013', '操作系统', 'system');
