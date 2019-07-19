/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50087
Source Host           : 127.0.0.1:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 50087
File Encoding         : 65001

Date: 2019-07-18 08:28:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` varchar(32) NOT NULL auto_increment,
  `summary` varchar(255)  NOT NULL COMMENT '摘要',
  `content` text  NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `kinds` varchar(255)  NOT NULL  COMMENT '种类',
  `read_times` int(11) NOT NULL default 0 COMMENT '阅读次数',
  `title` varchar(255)  NOT NULL COMMENT '标题',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

