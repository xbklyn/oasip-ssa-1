
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema oasip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Creaet database oasip
-- -----------------------------------------------------
create database if not exists oasip;
use oasip;

-- -----------------------------------------------------
-- Table `oasip`.`eventCategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS eventCategories (
  eventCategoryId INT NOT NULL AUTO_INCREMENT,
  eventCategoryName VARCHAR(100) NOT NULL,
  eventCategoryDescription VARCHAR(250) NULL,
  eventDuration INT NOT NULL,
  PRIMARY KEY (eventCategoryId))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasip`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS status (
  statusId INT NOT NULL AUTO_INCREMENT,
  statusName VARCHAR(50) NOT NULL,
  PRIMARY KEY (statusId))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasip`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS events (
  bookingId INT NOT NULL AUTO_INCREMENT,
  bookingName VARCHAR(100) NOT NULL,
  bookingEmail VARCHAR(100) NOT NULL,
  eventDate DATE NULL,
  eventStartTime TIME NOT NULL,
  eventEndTime TIME NULL,
  eventDuration INT NOT NULL,
  eventNotes VARCHAR(250) NULL,
  eventCategoryId INT NOT NULL,
  statusId INT NOT NULL,
  PRIMARY KEY (bookingId),
  INDEX fk_event_eventCategory_idx (eventCategoryId ASC) VISIBLE,
  INDEX fk_events_status1_idx (statusId ASC) VISIBLE,
  CONSTRAINT fk_event_eventCategory
    FOREIGN KEY (eventCategoryId)
    REFERENCES oasip.eventCategories (eventCategoryId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_events_status1
    FOREIGN KEY (statusId)
    REFERENCES oasip.status (statusId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
