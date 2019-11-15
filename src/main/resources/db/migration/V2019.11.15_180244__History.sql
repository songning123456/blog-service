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
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `history` (
  `id` varchar(32) NOT NULL,
  `article_id` varchar(60) DEFAULT NULL COMMENT '文章ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述详情',
  `time` varchar(50) NOT NULL COMMENT '时间',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES ('40281a816e6e147e016e6e179a8a00a1',NULL,'<span style=\'font-style:italic;font-weight:bold\'>songning123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 16:04:30</span>','2019-11-15 16:04:30','<span>注冊用戶信息</span>','songning123456'),('40281a816e6e147e016e6e19d2030143',NULL,'<span style=\'font-style:italic;font-weight:bold\'>fengying123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 16:06:56</span>','2019-11-15 16:06:56','<span>注冊用戶信息</span>','fengying123456'),('40281a816e6e147e016e6e1afd0701e5',NULL,'<span style=\'font-style:italic;font-weight:bold\'>test123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 16:08:12</span>','2019-11-15 16:08:12','<span>注冊用戶信息</span>','test123456'),('40281a816e6e53d3016e6e552be60000','40281a816e6e3b31016e6e3c24600162','<span style=\'font-style:italic;font-weight:bold\'>fengying123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 17:11:45</span>','2019-11-15 17:11:45','<span>阅读文章</span><span> </span><span>怎样加快电脑开机速度，教您提高电脑开机速度的方法</span>','fengying123456'),('40281a816e6e53d3016e6e55535e0001','40281a816e6e3b31016e6e3c24600162','<span style=\'font-style:italic;font-weight:bold\'>fengying123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 17:11:55</span>','2019-11-15 17:11:55','<span>阅读文章</span><span> </span><span>怎样加快电脑开机速度，教您提高电脑开机速度的方法</span>','fengying123456'),('40281a816e6e53d3016e6e5590880002',NULL,'<span style=\'font-style:italic;font-weight:bold\'>fengying123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 17:12:11</span>','2019-11-15 17:12:11','<span>休眠时钟</span>','fengying123456'),('40281a816e6e53d3016e6e55a1d70003','40281a816e6e3b31016e6e3c24600162','<span style=\'font-style:italic;font-weight:bold\'>fengying123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 17:12:16</span>','2019-11-15 17:12:16','<span>阅读文章</span><span> </span><span>怎样加快电脑开机速度，教您提高电脑开机速度的方法</span>','fengying123456'),('40281a816e6e53d3016e6e569b450004','40281a816e6e3b31016e6e3c24600162','<span style=\'font-style:italic;font-weight:bold\'>fengying123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 17:13:19</span>','2019-11-15 17:13:19','<span>阅读文章</span><span> </span><span>怎样加快电脑开机速度，教您提高电脑开机速度的方法</span>','fengying123456'),('2c9280846e6e609c016e6e63c26200a1',NULL,'<span style=\'font-style:italic;font-weight:bold\'>shijie123456</span> 提交于 <span style=\'font-style:italic;font-weight:bold\'>2019-11-15 09:27:41</span>','2019-11-15 09:27:41','<span>注冊用戶信息</span>','shijie123456');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-15 18:02:19
