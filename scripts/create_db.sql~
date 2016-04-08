SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `roads4su_mrep` ;
CREATE SCHEMA IF NOT EXISTS `roads4su_mrep` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `roads4su_mrep` ;

-- -----------------------------------------------------
-- Table `roads4su_mrep`.`zone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`zone` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`zone` (
  `z_id` INT NOT NULL AUTO_INCREMENT ,
  `z_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`z_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`territory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`territory` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`territory` (
  `tor_id` INT NOT NULL AUTO_INCREMENT ,
  `tor_name` VARCHAR(45) NULL ,
  `zone_z_id` INT NOT NULL ,
  PRIMARY KEY (`tor_id`) ,
  INDEX `fk_territory_zone` (`zone_z_id` ASC) ,
  CONSTRAINT `fk_territory_zone`
    FOREIGN KEY (`zone_z_id` )
    REFERENCES `roads4su_mrep`.`zone` (`z_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`brick`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`brick` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`brick` (
  `b_id` INT NOT NULL AUTO_INCREMENT ,
  `b_name` VARCHAR(45) NULL ,
  `territory_tor_id` INT NOT NULL ,
  PRIMARY KEY (`b_id`) ,
  INDEX `fk_Brick_territory1` (`territory_tor_id` ASC) ,
  CONSTRAINT `fk_Brick_territory1`
    FOREIGN KEY (`territory_tor_id` )
    REFERENCES `roads4su_mrep`.`territory` (`tor_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`emplyee_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`emplyee_type` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`emplyee_type` (
  `emplyee_type_id` INT NOT NULL AUTO_INCREMENT ,
  `emplyee_type_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`emplyee_type_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`line`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`line` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`line` (
  `l_id` INT NOT NULL AUTO_INCREMENT ,
  `l_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`l_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`employee` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`employee` (
  `emp_id` INT NOT NULL AUTO_INCREMENT ,
  `emp_name` VARCHAR(45) NULL ,
  `emp_username` VARCHAR(45) NULL ,
  `emp_pass` VARCHAR(45) NULL ,
  `emp_manager` INT NULL ,
  `emp_phone` VARCHAR(45) NULL ,
  `emp_address` VARCHAR(80) NULL ,
  `emp_brick` INT NOT NULL ,
  `emp_type` INT NOT NULL ,
  `l_id` INT NOT NULL ,
  PRIMARY KEY (`emp_id`) ,
  INDEX `fk_employee_Brick1` (`emp_brick` ASC) ,
  INDEX `fk_employee_emplyee_type1` (`emp_type` ASC) ,
  INDEX `fk_employee_Line1` (`l_id` ASC) ,
  CONSTRAINT `fk_employee_Brick1`
    FOREIGN KEY (`emp_brick` )
    REFERENCES `roads4su_mrep`.`brick` (`b_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_emplyee_type1`
    FOREIGN KEY (`emp_type` )
    REFERENCES `roads4su_mrep`.`emplyee_type` (`emplyee_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_Line1`
    FOREIGN KEY (`l_id` )
    REFERENCES `roads4su_mrep`.`line` (`l_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`cus_speciality`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`cus_speciality` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`cus_speciality` (
  `s_id` INT NOT NULL AUTO_INCREMENT ,
  `s_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`s_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`cus_tier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`cus_tier` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`cus_tier` (
  `cus_tier_id` INT NOT NULL ,
  `cus_tier_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`cus_tier_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`customer_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`customer_type` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`customer_type` (
  `cus_type_id` INT NOT NULL AUTO_INCREMENT ,
  `cus_type_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`cus_type_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`customer` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`customer` (
  `cus_id` INT NOT NULL AUTO_INCREMENT ,
  `cus_name` VARCHAR(45) NULL ,
  `cus_phone` VARCHAR(45) NULL ,
  `cus_address1` VARCHAR(80) NULL ,
  `cus_address2` VARCHAR(80) NULL ,
  `cus_brick` INT NOT NULL ,
  `cus_speciality` INT NOT NULL ,
  `cus_tier` INT NOT NULL ,
  `cus_type` INT NOT NULL ,
  PRIMARY KEY (`cus_id`) ,
  INDEX `fk_customer_Brick1` (`cus_brick` ASC) ,
  INDEX `fk_customer_cus_speciality1` (`cus_speciality` ASC) ,
  INDEX `fk_customer_cus_tier1` (`cus_tier` ASC) ,
  INDEX `fk_customer_customer_type1` (`cus_type` ASC) ,
  CONSTRAINT `fk_customer_Brick1`
    FOREIGN KEY (`cus_brick` )
    REFERENCES `roads4su_mrep`.`brick` (`b_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_cus_speciality1`
    FOREIGN KEY (`cus_speciality` )
    REFERENCES `roads4su_mrep`.`cus_speciality` (`s_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_cus_tier1`
    FOREIGN KEY (`cus_tier` )
    REFERENCES `roads4su_mrep`.`cus_tier` (`cus_tier_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_customer_type1`
    FOREIGN KEY (`cus_type` )
    REFERENCES `roads4su_mrep`.`customer_type` (`cus_type_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`plan` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`plan` (
  `plan_id` INT NOT NULL AUTO_INCREMENT ,
  `plan_name` VARCHAR(45) NULL ,
  `plan_date` DATETIME NULL ,
  `admin_id` INT NOT NULL ,
  `rep_id` INT NOT NULL ,
  `plan_brick` INT NOT NULL ,
  `status` INT NULL DEFAULT 0 ,
  PRIMARY KEY (`plan_id`) ,
  INDEX `fk_plan_employee1` (`admin_id` ASC) ,
  INDEX `fk_plan_employee2` (`rep_id` ASC) ,
  INDEX `fk_plan_brick1` (`plan_brick` ASC) ,
  CONSTRAINT `fk_plan_employee1`
    FOREIGN KEY (`admin_id` )
    REFERENCES `roads4su_mrep`.`employee` (`emp_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plan_employee2`
    FOREIGN KEY (`rep_id` )
    REFERENCES `roads4su_mrep`.`employee` (`emp_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plan_brick1`
    FOREIGN KEY (`plan_brick` )
    REFERENCES `roads4su_mrep`.`brick` (`b_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`plan_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`plan_items` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`plan_items` (
  `pi_id` INT NOT NULL AUTO_INCREMENT ,
  `plan_id` INT NOT NULL ,
  `pi_calls` INT NULL ,
  `cus_id` INT NOT NULL ,
  `pi_calls_done` INT NULL DEFAULT 0 ,
  PRIMARY KEY (`pi_id`) ,
  INDEX `fk_plan_items_plan1` (`plan_id` ASC) ,
  INDEX `fk_plan_items_customer1` (`cus_id` ASC) ,
  CONSTRAINT `fk_plan_items_plan1`
    FOREIGN KEY (`plan_id` )
    REFERENCES `roads4su_mrep`.`plan` (`plan_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plan_items_customer1`
    FOREIGN KEY (`cus_id` )
    REFERENCES `roads4su_mrep`.`customer` (`cus_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`call`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`call` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`call` (
  `call_id` INT NOT NULL AUTO_INCREMENT ,
  `call_date` DATE NULL ,
  `call_details` VARCHAR(100) NULL ,
  `pi_id` INT NOT NULL ,
  `samples` INT NULL ,
  `promMaterials` INT NULL ,
  `nph_id` VARCHAR(45) NULL ,
  `nph_feedback` VARCHAR(500) NULL ,
  `company_feedback` VARCHAR(45) NULL ,
  PRIMARY KEY (`call_id`) ,
  INDEX `fk_call_details_plan_items1` (`pi_id` ASC) ,
  CONSTRAINT `fk_call_details_plan_items1`
    FOREIGN KEY (`pi_id` )
    REFERENCES `roads4su_mrep`.`plan_items` (`pi_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`product` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`product` (
  `p_id` INT NOT NULL AUTO_INCREMENT ,
  `p_name` VARCHAR(45) NULL ,
  `l_id` INT NOT NULL ,
  PRIMARY KEY (`p_id`) ,
  INDEX `fk_product_Line1` (`l_id` ASC) ,
  CONSTRAINT `fk_product_Line1`
    FOREIGN KEY (`l_id` )
    REFERENCES `roads4su_mrep`.`line` (`l_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `roads4su_mrep`.`call_product_feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roads4su_mrep`.`call_product_feedback` ;

CREATE  TABLE IF NOT EXISTS `roads4su_mrep`.`call_product_feedback` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `feedback` VARCHAR(500) NULL ,
  `prod_id` INT NOT NULL ,
  `call_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_call_product_feedback_product1` (`prod_id` ASC) ,
  INDEX `fk_call_product_feedback_call1` (`call_id` ASC) ,
  CONSTRAINT `fk_call_product_feedback_product1`
    FOREIGN KEY (`prod_id` )
    REFERENCES `roads4su_mrep`.`product` (`p_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_call_product_feedback_call1`
    FOREIGN KEY (`call_id` )
    REFERENCES `roads4su_mrep`.`call` (`call_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

