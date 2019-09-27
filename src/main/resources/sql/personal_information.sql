/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-09-27 17:26:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for personal_information
-- ----------------------------
DROP TABLE IF EXISTS `personal_information`;
CREATE TABLE `personal_information` (
  `id` varchar(32) NOT NULL,
  `author` varchar(50) NOT NULL COMMENT '作者',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `info_type` varchar(50) NOT NULL COMMENT '信息类型',
  `introduction` text NOT NULL COMMENT '介绍',
  `mechanism` varchar(100) NOT NULL COMMENT '机构类型',
  `position` varchar(100) NOT NULL COMMENT '在职职位',
  `start_time` datetime NOT NULL COMMENT '起始时间',
  `photo` text COMMENT '照片',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of personal_information
-- ----------------------------
INSERT INTO `personal_information` VALUES ('40281a816d1df0d7016d1df3a56e0001', 'songning', '2018-07-01 00:00:00', '教育经历', '1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。', '南京工程学院', '学生', '2014-09-01 00:00:00', null);
INSERT INTO `personal_information` VALUES ('40281a816d1df0d7016d1df3a5a70002', 'songning', '2011-09-01 00:00:00', '教育经历', '1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。', '龙岗中学', '学生', '2014-09-01 00:00:00', null);
INSERT INTO `personal_information` VALUES ('40281a816d1df0d7016d1df3a5e00003', 'songning', '2008-09-01 00:00:00', '教育经历', '1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。', '大纵湖初级中学', '学生', '2011-09-01 00:00:00', null);
INSERT INTO `personal_information` VALUES ('40281a816d1df0d7016d1df3a6010004', 'songning', '2019-08-25 00:00:00', '工作经历', '1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。', 'keda', '职工', '2018-09-02 00:00:00', null);
INSERT INTO `personal_information` VALUES ('40281a816d1df0d7016d1df3a61d0005', 'songning', '2019-08-25 00:00:00', '工作经历', '1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。', '未知1', '职工', '2018-09-02 00:00:00', null);
INSERT INTO `personal_information` VALUES ('40281a816d1df0d7016d1df3a6320006', 'songning', '2019-08-25 00:00:00', '工作经历', '1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。', '未知2', '职工', '2018-09-02 00:00:00', null);
