-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: simple_blog
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `blogger`
--

DROP TABLE IF EXISTS `blogger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blogger` (
  `id` varchar(32) NOT NULL,
  `age` int(11) NOT NULL COMMENT '年龄',
  `author` varchar(64) NOT NULL COMMENT '作者',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `head_portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `profession` varchar(64) NOT NULL COMMENT '职业',
  `real_name` varchar(64) NOT NULL COMMENT '真实姓名',
  `telephone` varchar(64) NOT NULL COMMENT '电话',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  `motto` varchar(255) NOT NULL COMMENT '座右铭',
  PRIMARY KEY (`id`),
  UNIQUE KEY `author` (`author`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogger`
--

LOCK TABLES `blogger` WRITE;
/*!40000 ALTER TABLE `blogger` DISABLE KEYS */;
INSERT INTO `blogger` VALUES ('40281a816d1df0d7016d1df231680000',25,'重零开始','1457065857@qq.com','男','static/headPortrait/songning.svg','程序员','宋宁','15850682191','2019-09-11 09:31:15','songning','好好学习，天天向上'),('40281a816d7c3670016d7c368c180000',24,'zhou','1345768943@qq.com','男','static/headPortrait/haozhou.svg','程序员','郝周','14550983281','2019-09-29 16:50:13','haozhou','好好学习，天天向上'),('40281a816d7c382b016d7c3847a80000',24,'cest mom seul','1325776923@qq.com','男','static/headPortrait/shijie.svg','程序员','施杰','15850982182','2019-09-29 16:52:06','shijie','好好学习，天天向上'),('40281a816d7c39a6016d7c39c7840000',25,'吃顿好的','3123476563@qq.com','男','static/headPortrait/shenkeye.svg','公务员','沈克野','15550779134','2019-09-29 16:53:44','shenkeye','好好学习，天天向上'),('40281a816e2610e0016e2611e7db0000',21,'安静的猫','15957843289@163.com','女','C:\\Users\\songning\\simple-blog\\7b124ede-2ace-4c29-9af6-565534ddde67.png','学生','冯影','15957843289','2019-11-01 16:25:38','fengying123456','做一只躺猫');
/*!40000 ALTER TABLE `blogger` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-14 19:53:24
