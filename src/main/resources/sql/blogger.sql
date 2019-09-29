/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-09-29 17:22:51
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
INSERT INTO `blogger` VALUES ('40281a816d1df0d7016d1df231680000', '25', '重零开始', '1457065857@qq.com', '男', 'static/headPortrait/songning.svg', '好好学习，天天向上', '程序员', '宋宁', '15850682191', '2019-09-11 09:31:15', 'songning');
INSERT INTO `blogger` VALUES ('40281a816d7c3670016d7c368c180000', '24', 'zhou', '1345768943@qq.com', '男', 'static/headPortrait/haozhou.svg', '好好学习，天天向上', '程序员', '郝周', '14550983281', '2019-09-29 16:50:13', 'haozhou');
INSERT INTO `blogger` VALUES ('40281a816d7c382b016d7c3847a80000', '24', 'cest mom seul', '1325776923@qq.com', '男', 'static/headPortrait/shijie.svg', '好好学习，天天向上', '程序员', '施杰', '15850982182', '2019-09-29 16:52:06', 'shijie');
INSERT INTO `blogger` VALUES ('40281a816d7c39a6016d7c39c7840000', '25', '吃顿好的', '3123476563@qq.com', '男', 'static/headPortrait/shenkeye.svg', '好好学习，天天向上', '公务员', '沈克野', '15550779134', '2019-09-29 16:53:44', 'shenkeye');
