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
  `id` bigint(20) NOT NULL auto_increment,
  `summary` varchar(255) default NULL COMMENT '摘要',
  `content` text COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `kinds` varchar(255) default NULL COMMENT '种类',
  `read_times` int(11) default NULL COMMENT '阅读次数',
  `title` varchar(255) default NULL COMMENT '标题',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', null, '123', '2019-07-10 16:18:21', null, null, null, '2019-07-10 16:18:21');
INSERT INTO `blog` VALUES ('2', null, '456', '2019-07-10 16:30:13', null, null, null, '2019-07-10 16:30:13');
INSERT INTO `blog` VALUES ('3', null, '**789**', '2019-07-10 16:30:48', null, null, null, '2019-07-10 16:30:48');
INSERT INTO `blog` VALUES ('4', null, '1111', '2019-07-10 17:37:24', null, null, null, '2019-07-10 17:37:24');
INSERT INTO `blog` VALUES ('5', null, '1111', '2019-07-10 17:37:45', null, null, null, '2019-07-10 17:37:45');
INSERT INTO `blog` VALUES ('6', null, '1111', '2019-07-10 17:38:41', null, null, null, '2019-07-10 17:38:41');
INSERT INTO `blog` VALUES ('7', null, '1231232', '2019-07-10 17:38:56', null, null, null, '2019-07-10 17:38:56');
INSERT INTO `blog` VALUES ('8', null, '1231232', '2019-07-10 17:39:08', null, null, null, '2019-07-10 17:39:08');
INSERT INTO `blog` VALUES ('9', null, '1231232', '2019-07-10 17:39:10', null, null, null, '2019-07-10 17:39:10');
INSERT INTO `blog` VALUES ('10', null, '1231232', '2019-07-10 17:39:43', null, null, null, '2019-07-10 17:39:43');
INSERT INTO `blog` VALUES ('11', null, '测试', '2019-07-12 08:12:11', null, null, null, '2019-07-12 08:12:11');
