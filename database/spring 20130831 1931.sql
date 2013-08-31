-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.29


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema spring
--

CREATE DATABASE IF NOT EXISTS spring;
USE spring;

--
-- Definition of table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_name` varchar(20) NOT NULL,
  `article_desc` varchar(45) NOT NULL,
  `date_added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `article`
--

/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`article_id`,`article_name`,`article_desc`,`date_added`) VALUES 
 (1,'chetan','have u gud hao','2013-04-04 20:12:04'),
 (2,'asda','sldkalsd\r\nasd','2013-04-04 20:12:21'),
 (3,'asda','asdasda','2013-04-04 20:23:38'),
 (4,'thakur','ajdkasasd','2013-04-04 20:59:43'),
 (5,'Nupur Thakur','Only applicable to chetan hahahahah ....','2013-04-22 23:46:59'),
 (6,'asds','asdasdas','2013-05-21 12:41:57'),
 (7,'chetan`','ljljk','2013-05-21 17:15:59'),
 (8,'chetan','thakur family','2013-08-31 17:14:00'),
 (9,'hi','there','2013-08-31 17:14:51');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;


--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employee_id`,`firstname`,`lastname`) VALUES 
 (1,'Sergey','Brin'),
 (2,'Larry','Page'),
 (3,'Sergey','Brin'),
 (4,'Larry','Page');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `employee_meeting`
--

DROP TABLE IF EXISTS `employee_meeting`;
CREATE TABLE `employee_meeting` (
  `employee_id` bigint(20) NOT NULL,
  `meeting_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`meeting_id`),
  KEY `FK_MEETING` (`meeting_id`),
  CONSTRAINT `FK_EMPLOYEE` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `FK_MEETING` FOREIGN KEY (`meeting_id`) REFERENCES `meeting` (`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_meeting`
--

/*!40000 ALTER TABLE `employee_meeting` DISABLE KEYS */;
INSERT INTO `employee_meeting` (`employee_id`,`meeting_id`) VALUES 
 (1,1),
 (1,2),
 (2,2),
 (3,3),
 (4,3),
 (3,4);
/*!40000 ALTER TABLE `employee_meeting` ENABLE KEYS */;


--
-- Definition of table `meeting`
--

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `meeting_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject` varchar(50) NOT NULL,
  `meeting_date` date NOT NULL,
  PRIMARY KEY (`meeting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meeting`
--

/*!40000 ALTER TABLE `meeting` DISABLE KEYS */;
INSERT INTO `meeting` (`meeting_id`,`subject`,`meeting_date`) VALUES 
 (1,'Weekly Status meeting','2013-04-20'),
 (2,'Quaterly Sales meeting','2013-04-20'),
 (3,'Quaterly Sales meeting','2013-04-21'),
 (4,'Weekly Status meeting','2013-04-21');
/*!40000 ALTER TABLE `meeting` ENABLE KEYS */;


--
-- Definition of table `object`
--

DROP TABLE IF EXISTS `object`;
CREATE TABLE `object` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `object`
--

/*!40000 ALTER TABLE `object` DISABLE KEYS */;
INSERT INTO `object` (`id`,`url`,`type`) VALUES 
 (1,'http://localhost:8080/assignment/100','assignment'),
 (2,'http://localhost:8080/assignment/100','assignment'),
 (3,'http://localhost:8080/assignment/100','assignment'),
 (4,'http://localhost:8080/assignment/100','assignment');
/*!40000 ALTER TABLE `object` ENABLE KEYS */;


--
-- Definition of table `statement`
--

DROP TABLE IF EXISTS `statement`;
CREATE TABLE `statement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `verb_id` int(10) unsigned NOT NULL,
  `object_id` int(10) unsigned NOT NULL,
  `store` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_statement_1` (`verb_id`),
  KEY `FK_statement_2` (`user_id`),
  KEY `FK_statement_3` (`object_id`),
  CONSTRAINT `FK_statement_1` FOREIGN KEY (`verb_id`) REFERENCES `verb` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_statement_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_statement_3` FOREIGN KEY (`object_id`) REFERENCES `object` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statement`
--

/*!40000 ALTER TABLE `statement` DISABLE KEYS */;
INSERT INTO `statement` (`id`,`user_id`,`verb_id`,`object_id`,`store`) VALUES 
 (1,2,1,1,'2013-08-31 11:33:12'),
 (2,2,2,2,'2013-08-31 11:33:14'),
 (3,2,3,3,'2013-08-31 11:33:16'),
 (4,2,4,4,'2013-08-31 11:33:17');
/*!40000 ALTER TABLE `statement` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `password_type` tinyint(3) unsigned NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `salt` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`,`username`,`password`,`password_type`,`first_name`,`last_name`,`email`,`last_login`,`created_at`,`modified_at`,`salt`) VALUES 
 (2,'asdlj','551fa3c29b7c31344389778ba5b61a77',1,'lkjkljlk','kljlkj','lkjlkjl','2013-04-05 12:42:48','2013-04-05 12:42:48','2013-04-05 12:42:48','6771a772-82f8-40e9-a279-e6b8d52879da'),
 (3,'sd','551fa3c29b7c31344389778ba5b61a77',1,'1','1','1@1gmail.com','2013-05-08 22:27:45','2013-04-20 01:33:27','2013-04-20 01:33:27','3c9bbdaf-d5b2-444f-831f-d67409242f39'),
 (4,'lkjlkj','e5d81e094c00875efcac6f5bb7747423',1,'12','12','12@gmail.com','2013-04-21 17:31:48','2013-04-21 17:31:48','2013-04-21 17:31:48','2e1cd3d3-e832-4b79-ac75-aa56746e79bc'),
 (6,'ljlkjlkj','af7c34ed6f773f604dc07477a936b8eb',1,'lkjlkjl','ljlj','ljljljlj','2013-08-31 18:21:56','2013-08-31 18:21:56','2013-08-31 18:21:56','6ae2fe39-3ea2-42dc-8068-6acf9dcfe9be');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of table `verb`
--

DROP TABLE IF EXISTS `verb`;
CREATE TABLE `verb` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url_id` varchar(100) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `verb`
--

/*!40000 ALTER TABLE `verb` DISABLE KEYS */;
INSERT INTO `verb` (`id`,`url_id`,`type`) VALUES 
 (1,'http://localhost:8080/submit','submit'),
 (2,'http://localhost:8080/submit','submit'),
 (3,'http://localhost:8080/submit','submit'),
 (4,'http://localhost:8080/submit','submit');
/*!40000 ALTER TABLE `verb` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
