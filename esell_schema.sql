SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `esell` DEFAULT CHARACTER SET utf8 ;
USE `esell` ;

-- -----------------------------------------------------
-- Table `esell`.`categories`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `esell`.`categories` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `esell`.`users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `esell`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(50) NOT NULL ,
  `firstname` VARCHAR(50) NOT NULL ,
  `lastname` VARCHAR(50) NOT NULL ,
  `email` VARCHAR(50) NOT NULL ,
  `password` VARCHAR(16) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `esell`.`petitions`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `esell`.`petitions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `user_id` INT(11) NOT NULL ,
  `category_id` INT(11) NOT NULL ,
  `title` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(300) NULL DEFAULT NULL ,
  `price` INT(11) NULL DEFAULT NULL ,
  `amount` INT(11) NOT NULL DEFAULT '1' ,
  `image_url` VARCHAR(160) NULL DEFAULT NULL ,
  `state` SET('Searching','Finished') NOT NULL ,
  `created` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_petititions_users` (`user_id` ASC) ,
  INDEX `fk_petititions_categories1` (`category_id` ASC) ,
  CONSTRAINT `fk_petititions_categories1`
    FOREIGN KEY (`category_id` )
    REFERENCES `esell`.`categories` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_petititions_users`
    FOREIGN KEY (`user_id` )
    REFERENCES `esell`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Initiel categories
-- -----------------------------------------------------

INSERT INTO esell.categories (title) VALUES ('Books');
INSERT INTO esell.categories (title) VALUES ('Electronics');
INSERT INTO esell.categories (title) VALUES ('Movies');
INSERT INTO esell.categories (title) VALUES ('Computers');
INSERT INTO esell.categories (title) VALUES ('Home & Garden');
INSERT INTO esell.categories (title) VALUES ('Health & Beauty');
INSERT INTO esell.categories (title) VALUES ('Toys');
INSERT INTO esell.categories (title) VALUES ('Clothing');
INSERT INTO esell.categories (title) VALUES ('Shoes');
INSERT INTO esell.categories (title) VALUES ('Jewelery');
INSERT INTO esell.categories (title) VALUES ('Sports');
INSERT INTO esell.categories (title) VALUES ('Outdoor');
INSERT INTO esell.categories (title) VALUES ('Automotive');

