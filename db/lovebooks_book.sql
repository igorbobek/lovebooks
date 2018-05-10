-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lovebooks
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(101) NOT NULL,
  `publisher` varchar(101) NOT NULL,
  `year` varchar(4) NOT NULL,
  `pages` int(11) NOT NULL,
  `language` varchar(45) NOT NULL,
  `url` longtext NOT NULL,
  `img` longtext NOT NULL,
  `description` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Королева Марго','Александр Дюма','1932',1001,'Русский','https://aldebaran.ru/author/dyuma_aleksandr/kniga_koroleva_margo/download.a6.pdf','https://be2.aldebaran.ru/static/bookimages/02/02/97/02029755.bin.dir/02029755.cover_250.jpg','Роман французского классика Александра Дюма-отца «Королева Марго» открывает знаменитую трилогию об эпохе Генриха III и Генриха IV Наваррского, которую продолжают «Графиня де Монсоро» и...'),(2,'Алиса в Стране чудес','Льюис Кэрролл','1999',207,'Русский','https://aldebaran.ru/author/kyerroll_lyuis/kniga_alisa_v_strane_chudes_28747935/download.a6.pdf','https://be2.aldebaran.ru/static/bookimages/34/82/15/34821536.bin.dir/34821536.cover_250.jpg','Сказочная повесть Льюиса Кэрролла не нуждается ни в представлении, ни в пересказе. Взрослым будут интересны зашифрованные в ней символы, а дети будут просто наслаждаться удивительным развитием событий... '),(5,'Ярмарка тщеславия','Уильям Мейкпис Теккерей','1976',666,'Русский','https://aldebaran.ru/author/tekkereyi_uilyam/kniga_yarmarka_tsheslaviya/download.a6.pdf','https://be2.aldebaran.ru/static/bookimages/30/06/85/30068540.bin.dir/30068540.cover_250.jpg','Вершиной творчества английского писателя, журналиста и графика Уильяма Мейкписа Теккерея стал роман «Ярмарка тщеславия». Все персонажи романа – положительные и отрицательные – вовлечены, по словам автора, в «вечный круг горя и страдания». Насыщенный событиями, богатый тонкими наблюдениями быта своего времени, прони...'),(6,'123','123','1234',123,'123','123','123','123'),(7,'123','123','1234',123,'123','123123','123124','123124'),(8,'123','123','1234',4234,'234','234','234','1234'),(9,'124234','234234','1234',123154,'12315','235234','23412','1234'),(10,'21165314','334','1234',1234,'123124','3425235','23234','42351'),(11,'12431234','15d','1234',13,'54332','sdfgsdfg','sdgsdfg','sdfgsfdg'),(12,'1234','123123','1234',1234,'sdfdfg','sfdgsdgf','dfgsdf','cvbxert'),(13,'1235534','1231245','1234',1234,'dsfagsfdhg','sdhsdhg','dsfh','sfdhh');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-10 18:32:19
