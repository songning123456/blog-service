SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` varchar(32) NOT NULL,
  `content` text NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `kinds` varchar(255) NOT NULL COMMENT '种类',
  `read_times` int(11) NOT NULL DEFAULT '0' COMMENT '阅读次数',
  `summary` varchar(255) NOT NULL COMMENT '摘要',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `author` varchar(255) NOT NULL COMMENT '作者',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of blog
-- ----------------------------

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-08-12 08:56:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blogger
-- ----------------------------
DROP TABLE IF EXISTS `blogger`;
CREATE TABLE `blogger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL COMMENT '年龄',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `gender` int(11) NOT NULL COMMENT '性别',
  `introduction` varchar(255) NOT NULL COMMENT '介绍',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `profession` varchar(64) NOT NULL COMMENT '职业',
  `telephone` varchar(64) NOT NULL COMMENT '电话',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of blogger
-- ----------------------------

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for label_config
-- ----------------------------
DROP TABLE IF EXISTS `label_config`;
CREATE TABLE `label_config` (
  `id` varchar(32) NOT NULL,
  `blog_id` varchar(32) NOT NULL COMMENT '博客id',
  `label_name` varchar(50) NOT NULL COMMENT '标签名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blog_id` (`blog_id`,`label_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of label_config
-- ----------------------------

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

