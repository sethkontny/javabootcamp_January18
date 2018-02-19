CREATE DATABASE  IF NOT EXISTS `LibraryTerm` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `LibraryTerm`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: LibraryTerm
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `libitems`
--

DROP TABLE IF EXISTS `libitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libitems` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `libraryid` int(11) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `dueDate` date DEFAULT NULL,
  `thumbURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libitems`
--

LOCK TABLES `libitems` WRITE;
/*!40000 ALTER TABLE `libitems` DISABLE KEYS */;
INSERT INTO `libitems` VALUES (1,1,'I, Robot','Issac Asimov','1','2017-07-07','http://library.umbc.edu/speccoll/scifi/images/i_robot.jpg'),(2,2,'Humanity Prime','Bruce Mc Allister','1','2017-07-07','http://people.uncw.edu/smithms/Ace%20singles/s5N-series/34900.jpg'),(3,3,'The Robot Brains','Sydney Bounds','0',NULL,'https://s-media-cache-ak0.pinimg.com/originals/ec/79/1c/ec791c3953c43f26e965f6128c467838.jpg'),(4,1,'No Way Back','Karl Zeigfreid','0',NULL,'http://resonantfish.com/blogalopod/wp-content/uploads/2009/11/no_way_back.jpg'),(5,3,'Stranger in A Strange Land','Robert A. Heinlein','0',NULL,'https://s-media-cache-ak0.pinimg.com/736x/80/5a/0e/805a0e88bd5d18934a35ed7625d1bff3.jpg'),(6,2,'Alien Planet','Flecther Pratt','1','2017-07-04','https://cheapscifi.files.wordpress.com/2011/01/alienplanetcoolestcover.jpg'),(10,3,'Doomed Planet','Lee Sheldon','0',NULL,'http://pzrservices.typepad.com/.a/6a00d83451ccbc69e2010536114a85970b-pi'),(17,1,'UFO','Gold Key','0',NULL,'https://s-media-cache-ak0.pinimg.com/736x/3d/2b/3d/3d2b3d4ce25baa1372e1c08fc7554724.jpg'),(25,2,'Uncarted Starts','Andre Norton','0',NULL,'https://s-media-cache-ak0.pinimg.com/originals/1b/e4/1a/1be41acf9cf1175624dd0592c7026aed.jpg'),(26,3,'The Well of the Worlds','Henry Kuttner','0',NULL,'https://s-media-cache-ak0.pinimg.com/236x/fa/63/4e/fa634eaaab1e28862b0baaae83969683--horror-vintage-mens-vintage.jpg'),(27,1,'The Lizard Lords','Stanton A. Coblentz','0',NULL,'https://theinvisibleagent.files.wordpress.com/2009/02/scifi-book1.jpg?w=460');
/*!40000 ALTER TABLE `libitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libraries`
--

DROP TABLE IF EXISTS `libraries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libraries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libraries`
--

LOCK TABLES `libraries` WRITE;
/*!40000 ALTER TABLE `libraries` DISABLE KEYS */;
INSERT INTO `libraries` VALUES (1,'Main Library','1234 Woodward','Detroit','MI','48226'),(2,'Moe\'s Library','epluribus unium','Moonbase Alpha','Luna','12345'),(3,'Grand Circus','1570 Woodward','Detroit','MI','48226');
/*!40000 ALTER TABLE `libraries` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-24 18:32:15
