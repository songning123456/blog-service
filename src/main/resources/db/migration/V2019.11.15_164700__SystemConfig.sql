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
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `system_config` (
  `id` varchar(32) NOT NULL,
  `config_key` varchar(255) NOT NULL COMMENT '配置项key',
  `config_value` varchar(255) NOT NULL COMMENT '配置项value',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  `value_description` varchar(255) DEFAULT NULL COMMENT '配置项描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_config`
--

LOCK TABLES `system_config` WRITE;
/*!40000 ALTER TABLE `system_config` DISABLE KEYS */;
INSERT INTO `system_config` VALUES ('40281a816db53eb5016db53ed2fa0000','dataBase','MYSQL','songning','动态切换数据源'),('40281a816db53eb5016db53ed3540001','dataBase','MYSQL','shenkeye','动态切换数据源'),('40281a816db53eb5016db53ed36f0002','dataBase','MYSQL','shijie','动态切换数据源'),('40281a816db53eb5016db53ed3830003','dataBase','MYSQL','haozhou','动态切换数据源'),('40281a816e3b254d016e3b25adec0009','dataBase','MYSQL','songning','动态切换数据源'),('40281a816e2610e0016e2611e8070001','dataBase','MYSQL','fengying123456','动态切换数据源'),('40281a816e3b254d016e3b25ae2f000a','dataBase','MYSQL','shenkeye','动态切换数据源'),('40281a816e3b254d016e3b25ae43000b','dataBase','MYSQL','shijie','动态切换数据源'),('40281a816e3b254d016e3b25ae57000c','dataBase','MYSQL','haozhou','动态切换数据源'),('40281a816e3b268f016e3b26c9690009','dataBase','MYSQL','songning','动态切换数据源'),('40281a816e3b268f016e3b26c9ab000a','dataBase','MYSQL','shenkeye','动态切换数据源'),('40281a816e3b268f016e3b26c9bf000b','dataBase','MYSQL','shijie','动态切换数据源'),('40281a816e3b268f016e3b26c9d5000c','dataBase','MYSQL','haozhou','动态切换数据源'),('40281a816e43de6d016e43de94270000','dataBase','MYSQL','test','动态切换数据源'),('40281a816e43df7b016e43df9fbd0000','dataBase','MYSQL','test','动态切换数据源'),('40281a816e43e10a016e43e12b020000','dataBase','MYSQL','test','动态切换数据源'),('40281a816e6c9f47016e6cce9dc1004e','dataBase','MYSQL','songning123456','动态切换数据源'),('40281a816e6cd330016e6cd4b4490001','dataBase','MYSQL','test123456','动态切换数据源'),('40281a816e6ce514016e6ce6d7b60001','dataBase','MYSQL','shenke123456','动态切换数据源');
/*!40000 ALTER TABLE `system_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-15 16:47:32
