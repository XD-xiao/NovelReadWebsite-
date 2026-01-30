-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: novelreaddb
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chaptertable`
--

DROP TABLE IF EXISTS `chaptertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chaptertable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookId` int NOT NULL,
  `title` varchar(40) NOT NULL,
  `content` varchar(4000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chaptertable`
--

LOCK TABLES `chaptertable` WRITE;
/*!40000 ALTER TABLE `chaptertable` DISABLE KEYS */;
INSERT INTO `chaptertable` VALUES (1,1,'第一章1','第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容第一章内容'),(2,1,'第二章','第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容第二章内容');
/*!40000 ALTER TABLE `chaptertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `codetable`
--

DROP TABLE IF EXISTS `codetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `codetable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `code` varchar(10) NOT NULL,
  `ttl` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codetable`
--

LOCK TABLES `codetable` WRITE;
/*!40000 ALTER TABLE `codetable` DISABLE KEYS */;
INSERT INTO `codetable` VALUES (3,'123@qq.com','8946','2025-12-02 22:33:12'),(4,'11111@qq.com','8126','2025-12-02 22:34:40'),(5,'108@qq.com','3872','2025-12-02 22:52:33'),(7,'11111@qq.com','1766','2025-12-03 21:37:49'),(8,'101@qq.com','5604','2025-12-03 21:49:59');
/*!40000 ALTER TABLE `codetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collecttable`
--

DROP TABLE IF EXISTS `collecttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collecttable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookId` int NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collecttable`
--

LOCK TABLES `collecttable` WRITE;
/*!40000 ALTER TABLE `collecttable` DISABLE KEYS */;
INSERT INTO `collecttable` VALUES (3,1,13),(4,5,13),(5,3,13),(6,6,1),(7,5,1),(8,7,1),(9,2,3),(10,9,3),(11,1,3),(12,5,3),(13,7,3),(15,1,21),(16,3,21),(17,5,21),(18,7,21);
/*!40000 ALTER TABLE `collecttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticetable`
--

DROP TABLE IF EXISTS `noticetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticetable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `title` varchar(40) NOT NULL,
  `content` varchar(400) NOT NULL,
  `isRead` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticetable`
--

LOCK TABLES `noticetable` WRITE;
/*!40000 ALTER TABLE `noticetable` DISABLE KEYS */;
INSERT INTO `noticetable` VALUES (1,1,'通知测试','测试测试',0),(2,4,'违规通知','您的行为已危害社区，现对你严肃警告！',0),(3,3,'违规通知','您的行为已危害社区，现对你严肃警告！',0),(4,7,'违规通知','您的行为已危害社区，现对你严肃警告！',0),(5,21,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(6,23,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(7,24,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(8,25,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(9,26,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(10,27,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(11,28,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(12,29,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(13,30,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(14,31,'获奖通知','恭喜您获得“谁是卧底”活动一等奖——系统通知奖励',0),(15,1,'MVP','尊贵的VIP用户，恭喜您成为第一个加入本网站的用户！！！',0);
/*!40000 ALTER TABLE `noticetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noveltable`
--

DROP TABLE IF EXISTS `noveltable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noveltable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cover` varchar(40) DEFAULT NULL,
  `title` varchar(40) NOT NULL,
  `author` varchar(40) NOT NULL,
  `status` varchar(40) NOT NULL,
  `lastUpdated` datetime NOT NULL,
  `rating` float DEFAULT NULL,
  `collect` int DEFAULT NULL,
  `watch` int DEFAULT NULL,
  `summary` varchar(200) NOT NULL,
  `tags` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noveltable`
--

LOCK TABLES `noveltable` WRITE;
/*!40000 ALTER TABLE `noveltable` DISABLE KEYS */;
INSERT INTO `noveltable` VALUES (1,'https://picsum.photos/80/100?id=1','标题测试11','作者测试1','已完结','2025-12-02 10:49:35',4.3,3,1,'简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1简介测试1','[\"标签1\",\"标签2\",\"搞笑\"]'),(2,'https://picsum.photos/80/100?id=2','测试标题2','测试作者2','连载中','2025-12-02 22:19:16',2,1,0,'测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2测试简介2','[\"恐怖\",\"悬疑\"]'),(3,'https://picsum.photos/80/100?id=3','测试标题3','测试作者3','下架中','2025-12-02 22:20:16',2.5,2,0,'测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3测试简介3','[\"下架\"]'),(4,'https://picsum.photos/80/100?id=4','三体1','刘慈欣','连载中','2025-12-02 22:20:57',3,0,0,'这是三体1这是三体1这是三体1这是三体1这是三体1这是三体1这是三体1','[\"三体\"]'),(5,'https://picsum.photos/80/100?id=5','三体2','刘慈欣','连载中','2025-12-02 22:22:01',3.8,4,0,'这是三体2这是三体2这是三体2这是三体2这是三体2','[\"三体\",\"科学\"]'),(6,'https://picsum.photos/80/100?id=6','三体3','刘慈欣','连载中','2025-12-02 22:22:32',4,1,0,'这是三体3这是三体3这是三体3这是三体3这是三体3这是三体3这是三体3这是三体3这是三体3这是三体3这是三体3这是三体3','[\"三体\",\"科幻\"]'),(7,'https://picsum.photos/80/100?id=7','最后一个问题','阿西莫夫','连载中','2025-12-02 22:23:21',3.3,3,0,'要有光要有光要有光要有光要有光要有光要有光要有光','[\"光学\",\"计算机\"]'),(8,'https://picsum.photos/80/100?id=8','重生了','cs','连载中','2025-12-02 22:24:21',2,0,0,'我重生了，我重生到CS2的世界中...','[\"反恐\"]'),(9,'https://picsum.photos/80/100?id=9','元神不启动','米哈游','连载中','2025-12-02 22:25:12',2,1,0,'元神不是原神','[\"原神\",\"二次元\"]');
/*!40000 ALTER TABLE `noveltable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratingtable`
--

DROP TABLE IF EXISTS `ratingtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratingtable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookId` int NOT NULL,
  `userId` int NOT NULL,
  `rating` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratingtable`
--

LOCK TABLES `ratingtable` WRITE;
/*!40000 ALTER TABLE `ratingtable` DISABLE KEYS */;
INSERT INTO `ratingtable` VALUES (3,1,13,5),(4,5,13,4),(5,3,13,3),(6,6,1,4),(7,5,1,5),(8,8,1,2),(9,7,1,5),(10,2,3,2),(11,9,3,2),(12,1,3,3),(13,5,3,2),(14,7,3,2),(15,5,21,4),(16,1,21,5),(17,3,21,2),(18,7,21,3),(19,4,21,3);
/*!40000 ALTER TABLE `ratingtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertable`
--

DROP TABLE IF EXISTS `usertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertable` (
  `name` varchar(30) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userTable_pk_2` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertable`
--

LOCK TABLES `usertable` WRITE;
/*!40000 ALTER TABLE `usertable` DISABLE KEYS */;
INSERT INTO `usertable` VALUES ('李四',2,'123@qq.com','女','2025-12-02 22:28:20'),('一号',3,'11111@qq.com','other','2025-12-02 22:29:51'),('二号',4,'2222@163.com','男','2025-12-02 22:29:51'),('张三',5,'100@yahoo.com','女','2025-11-12 22:39:40'),('李四',6,'101@outlook.com','女','2025-11-12 22:39:40'),('王五',7,'102@163.com','男','2025-11-09 22:39:40'),('赵六',8,'103@yahoo.com','女','2025-11-13 22:39:40'),('钱七',9,'104@yahoo.com','女','2025-11-15 22:39:40'),('孙八',10,'105@outlook.com','女','2025-11-26 22:39:40'),('周九',11,'106@qq.com','other','2025-11-21 22:39:40'),('吴十',12,'107@outlook.com','男','2025-11-17 22:39:40'),('郑一',13,'108@qq.com','女','2025-11-10 22:39:40'),('冯二',14,'109@yahoo.com','女','2025-11-24 22:39:40'),('陈三',15,'110@qq.com','男','2025-11-09 22:39:40'),('褚四',16,'111@163.com','男','2025-11-15 22:39:40'),('卫五',17,'112@outlook.com','女','2025-12-02 22:39:40'),('蒋六',18,'113@yahoo.com','other','2025-11-24 22:39:40'),('沈七',19,'114@outlook.com','女','2025-11-08 22:39:40'),('张三',20,'100@outlook.com','男','2025-11-06 22:40:29'),('李四',21,'101@qq.com','男','2025-11-29 22:40:29'),('赵六',23,'6291352009@163.com','女','2025-11-07 02:36:40'),('褚四',24,'8022310285@outlook.com','女','2025-11-15 02:35:40'),('蒋六',25,'8760731468@163.com','女','2025-12-02 19:20:40'),('陈三',26,'5369956188@gmail.com','男','2025-11-14 16:02:40'),('沈七_5',27,'1794150997@yahoo.com','女','2025-11-14 02:36:40'),('郑一_6',28,'8760816022@yahoo.com','男','2025-11-23 06:37:40'),('蒋六',29,'5896175871@qq.com','女','2025-11-15 22:25:40'),('陈三_8',30,'4359988360@163.com','女','2025-11-14 11:22:40'),('郑一',31,'2132390143@yahoo.com','女','2025-11-09 00:12:40'),('蒋六_10',32,'8436512443@yahoo.com','女','2025-11-29 19:09:40'),('钱七_1',33,'2241345623@qq.com','other','2025-11-18 21:12:34'),('孙八',34,'8193028695@outlook.com','other','2025-11-21 15:36:34'),('吴十',35,'2909172588@outlook.com','女','2025-11-08 09:02:34'),('沈七',36,'6433773136@outlook.com','other','2025-11-03 08:12:34'),('蒋六',37,'9764659783@gmail.com','男','2025-11-18 14:47:34'),('郑一_6',38,'1152571479@163.com','男','2025-11-06 19:55:34'),('郑一',39,'0124613786@gmail.com','女','2025-11-12 15:18:34'),('张三',40,'7266327828@yahoo.com','女','2025-11-05 12:48:34'),('郑一',41,'1921436390@163.com','other','2025-11-08 02:12:34'),('周九',42,'8043955155@gmail.com','男','2025-11-29 15:15:34');
/*!40000 ALTER TABLE `usertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watchtable`
--

DROP TABLE IF EXISTS `watchtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `watchtable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chapterId` int NOT NULL,
  `userId` int NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watchtable`
--

LOCK TABLES `watchtable` WRITE;
/*!40000 ALTER TABLE `watchtable` DISABLE KEYS */;
INSERT INTO `watchtable` VALUES (8,2,13,'2025-12-02 23:11:39');
/*!40000 ALTER TABLE `watchtable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-30 10:23:55
