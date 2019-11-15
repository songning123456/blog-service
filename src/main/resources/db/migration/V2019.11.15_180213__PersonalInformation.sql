-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 122.51.193.191    Database: simple_blog
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `personal_information`
--

DROP TABLE IF EXISTS `personal_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `personal_information` (
  `id` varchar(32) NOT NULL,
  `author` varchar(50) NOT NULL COMMENT '作者',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `info_type` varchar(50) NOT NULL COMMENT '信息类型',
  `introduction` text NOT NULL COMMENT '介绍',
  `mechanism` varchar(100) NOT NULL COMMENT '机构类型',
  `photo` text COMMENT '照片',
  `position` varchar(100) NOT NULL COMMENT '在职职位',
  `start_time` datetime NOT NULL COMMENT '起始时间',
  `user_id` varchar(60) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_information`
--

LOCK TABLES `personal_information` WRITE;
/*!40000 ALTER TABLE `personal_information` DISABLE KEYS */;
INSERT INTO `personal_information` VALUES ('40281a816e6e51be016e6e51e5050000','凌晨','2018-07-01 00:00:00','教育经历','1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。','南京工程学院',NULL,'学生','2014-09-01 00:00:00','40281a816e6e147e016e6e173acb0000'),('40281a816e6e51be016e6e51e51b0001','凌晨','2011-09-01 00:00:00','教育经历','1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。','龙岗中学',NULL,'学生','2014-09-01 00:00:00','40281a816e6e147e016e6e173acb0000'),('40281a816e6e51be016e6e51e51c0002','凌晨','2008-09-01 00:00:00','教育经历','1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。','大纵湖初级中学',NULL,'学生','2011-09-01 00:00:00','40281a816e6e147e016e6e173acb0000'),('40281a816e6e51be016e6e51e51c0003','凌晨','2019-08-25 00:00:00','工作经历','1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。','阿里巴巴有限公司',NULL,'职工','2018-09-02 00:00:00','40281a816e6e147e016e6e173acb0000'),('40281a816e6e51be016e6e51e51c0004','凌晨','2019-08-25 00:00:00','工作经历','1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。','腾讯科技有限公司',NULL,'职工','2018-09-02 00:00:00','40281a816e6e147e016e6e173acb0000'),('40281a816e6e51be016e6e51e51c0005','凌晨','2019-08-25 00:00:00','工作经历','1985年发行首张个人专辑《只知道此刻爱你》 [16]  。1990年凭借专辑《可不可以》在歌坛获得关注 [17]  。1994年获得十大劲歌金曲最受欢迎男歌星奖。1995年在央视春晚上演唱歌曲《忘情水》 [18]  。2000年被《吉尼斯世界纪录大全》评为“获奖最多的香港男歌手” [19]  。2004年第六次获得十大劲歌金曲最受欢迎男歌星奖。2016年参与填词的歌曲《原谅我》正式发行 [20]  。','时光剪影有限公司',NULL,'职工','2018-09-02 00:00:00','40281a816e6e147e016e6e173acb0000');
/*!40000 ALTER TABLE `personal_information` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-15 18:02:20
