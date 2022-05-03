create database oasip;
use oasip;

--
-- Table structure for table `eventcategories`
--

DROP TABLE IF EXISTS `eventCategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventCategories` (
  `eventCategoryId` int NOT NULL AUTO_INCREMENT,
  `eventCategoryName` varchar(100) NOT NULL,
  `eventCategoryDescription` varchar(250) DEFAULT NULL,
  `eventDuration` int NOT NULL,
  PRIMARY KEY (`eventCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `bookingId` int NOT NULL AUTO_INCREMENT,
  `bookingName` varchar(100) NOT NULL,
  `bookingEmail` varchar(100) NOT NULL,
  `eventDate` date NOT NULL,
  `eventStartTime` time NOT NULL,
  `eventEndTime` time NOT NULL,
  `eventDuration` int NOT NULL,
  `eventNotes` varchar(250) DEFAULT NULL,
  `eventCategoryId` int NOT NULL,
  PRIMARY KEY (`bookingId`),
  KEY `fk_event_eventCategory_idx` (`eventCategoryId`),
  CONSTRAINT `fk_event_eventCategory` FOREIGN KEY (`eventCategoryId`) REFERENCES `eventcategories` (`eventCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;