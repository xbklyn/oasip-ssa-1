-- MySQL Script generated by MySQL Workbench
-- Sun May 15 21:59:56 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
SET GLOBAL time_zone = '+7:00';

-- -----------------------------------------------------
-- Schema oasip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Databse oasip
-- -----------------------------------------------------
CREATE database IF NOT EXISTS `oasip` ;
USE `oasip` ;

-- -----------------------------------------------------
-- Table `oasip`.`eventCategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oasip`.`eventCategories` (
  `eventCategoryId` INT NOT NULL AUTO_INCREMENT,
  `eventCategoryName` VARCHAR(100) NOT NULL,
  `eventCategoryDescription` VARCHAR(500) NULL DEFAULT NULL,
  `eventDuration` INT NOT NULL,
  PRIMARY KEY (`eventCategoryId`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

--
-- Dumping data for table `eventcategories`
--

LOCK TABLES `eventCategories` WRITE;
/*!40000 ALTER TABLE `eventCategories` DISABLE KEYS */;
INSERT INTO `eventCategories` VALUES (1,'Project Management Clinic',' ตารางนัดหมายนี้ใช้สําหรับนัดหมาย project management clinic ในวิชา INT221 integrated project I ให้นักศึกษาเตรียมเอกสารที่เกี่ยวข้องเพื่อแสดง ระหว่างขอคําปรึกษา',30),(2,'DevOps/Infra Clinic','Use this event category for DevOps/Infra clinic.',20),(3,'Database Clinic','ตารางนัดหมายนี้ใช้สําหรับนัดหมาย database clinic ใน วิชา INT221 integrated project I',15),(4,'Client-side Clinic','ตารางนัดหมายนี้ใช้สําหรับนัดหมาย client-side clinic ใน วิชา INT221 integrated project I',30),(5,'Server-side Clinic','',30);
/*!40000 ALTER TABLE `eventCategories` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `oasip`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oasip`.`status` (
  `statusId` INT NOT NULL AUTO_INCREMENT,
  `statusName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`statusId`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'COMPLETED'),(2,'ONGOING'),(3,'UPCOMING');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;



-- -----------------------------------------------------
-- Table `oasip`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oasip`.`events` (
  `bookingId` INT NOT NULL AUTO_INCREMENT,
  `bookingName` VARCHAR(100) NOT NULL,
  `bookingEmail` VARCHAR(100) BINARY NOT NULL,
  `eventStartTime` DATETIME NOT NULL,
  `eventEndTime` DATETIME NOT NULL,
  `eventDuration` INT NOT NULL,
  `eventNotes` VARCHAR(500) NULL DEFAULT NULL,
  `eventCategoryId` INT NOT NULL,
  `statusId` INT NOT NULL,
  PRIMARY KEY (`bookingId`),
  INDEX `fk_event_eventCategory_idx` (`eventCategoryId` ASC) VISIBLE,
  INDEX `fk_events_status1_idx` (`statusId` ASC) VISIBLE,
  CONSTRAINT `fk_event_eventCategory`
    FOREIGN KEY (`eventCategoryId`)
    REFERENCES `oasip`.`eventCategories` (`eventCategoryId`),
  CONSTRAINT `fk_events_status1`
    FOREIGN KEY (`statusId`)
    REFERENCES `oasip`.`status` (`statusId`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'Somchai Jaidee (OR-7)','somchai.jai@mail.kmutt.ac.th','2022-05-23 13:30:00','2022-05-23 14:00:00',30,'',2,3),(2,'Somsri Rakdee (SJ-3)','somsri.rak@mail.kmutt.ac.th','2022-04-27 09:30:00','2022-04-27 10:00:00',30,'ขอปรึกษาปัญหาเพื่อนไม่ช่วยงาน',1,1),(3,'สมเกียรติ ขยันเรียน  (TT-4)','somkiat.kay@kmutt.ac.th','2022-05-23 16:30:00','2022-05-23 17:00:00',15,'',3,3);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


--
-- create user
--
create user root@'172.17.0.1' identified by '123';
grant all privileges on *.* to 'root'@'172.17.0.1';