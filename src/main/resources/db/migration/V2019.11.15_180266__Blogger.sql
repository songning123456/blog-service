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
  `motto` varchar(255) NOT NULL COMMENT '座右铭',
  `profession` varchar(64) NOT NULL COMMENT '职业',
  `real_name` varchar(64) NOT NULL COMMENT '真实姓名',
  `telephone` varchar(64) NOT NULL COMMENT '电话',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `user_id` varchar(60) DEFAULT NULL COMMENT '用户ID',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogger`
--

LOCK TABLES `blogger` WRITE;
/*!40000 ALTER TABLE `blogger` DISABLE KEYS */;
INSERT INTO `blogger` VALUES ('40281a816e6e147e016e6e1746800001',25,'凌晨','1465846574@qq.com','男','/root/simple-blog/avatar/0c52fb2c-6309-4c37-b283-7d14bf904a19.png','不忘初心','程序员','宋宁','15758673827','2019-11-15 16:04:09','40281a816e6e147e016e6e173acb0000','songning123456'),('40281a816e6e147e016e6e19baf400a3',27,'安静的毛','1748574647@qq.com','女','/root/simple-blog/avatar/0c52fb2c-6309-4c37-b283-7d14bf904a19.png','厉害了','会计','风影','17464846383','2019-11-15 16:06:50','40281a816e6e147e016e6e19bac700a2','fengying123456'),('40281a816e6e147e016e6e1ae2df0145',30,'test','17364856378@qq.com','女','/root/simple-blog/avatar/0c52fb2c-6309-4c37-b283-7d14bf904a19.png','方得始终','外卖员','测试','17845647383','2019-11-15 16:08:06','40281a816e6e147e016e6e1ae2b40144','test123456'),('2c9280846e6e609c016e6e63bbd40001',31,'世界下的书','1537454735@qq.com','男','/root/simple-blog/avatar/0c52fb2c-6309-4c37-b283-7d14bf904a19.png','好好学习','工程师','世界','16454745473','2019-11-15 17:27:40','2c9280846e6e609c016e6e63bbab0000','shijie123456');
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

-- Dump completed on 2019-11-15 18:02:18
