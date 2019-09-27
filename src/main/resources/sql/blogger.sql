/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-09-27 17:26:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blogger
-- ----------------------------
DROP TABLE IF EXISTS `blogger`;
CREATE TABLE `blogger` (
  `id` varchar(32) NOT NULL,
  `age` int(11) NOT NULL COMMENT '年龄',
  `author` varchar(64) NOT NULL COMMENT '作者',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `head_portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `introduction` varchar(255) NOT NULL COMMENT '介绍',
  `profession` varchar(64) NOT NULL COMMENT '职业',
  `real_name` varchar(64) NOT NULL COMMENT '真实姓名',
  `telephone` varchar(64) NOT NULL COMMENT '电话',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `author` (`author`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of blogger
-- ----------------------------
INSERT INTO `blogger` VALUES ('40281a816d1df0d7016d1df231680000', '35', 'songning', '1457065856@qq.com', '男', 'static/headPortrait.svg', '从警8年他硕果累累，先后荣立个人一等功1次，二等功1次，三等功3次，嘉奖2次，2006年被评为“百姓心中好交警”，2007年被评为青年执法标兵，2008年被评为奥运交通安保标兵和“微笑北京交警之星”，2009年又荣获了首都“五一”劳动奖章,受邀参加市委政法委“学习实践科学发展观巡讲团”和北京市公安局“平凡颂”演讲报告会。孟昆玉在平凡的岗位上，做出了不平凡的业绩', '程序员', '宁宁', '15850682190', '2019-09-11 09:31:15', 'test');
