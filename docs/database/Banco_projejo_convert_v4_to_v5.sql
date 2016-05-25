/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Gustavo
SISTON-8
 * Created: May 08, 2016
 */
SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT=0;
START TRANSACTION;

CREATE TABLE `localidade` (
  `id` INT NOT NULL,
  `estacao` VARCHAR(45) NOT NULL,
  `latitude` VARCHAR(45) NULL,
  `longitude` VARCHAR(45) NULL,
  `altitude` VARCHAR(45) NULL,
  `coleta_inicio` DATE NOT NULL,
  `coleta_fim` DATE NOT NULL,
  PRIMARY KEY (`id`));
  
  ALTER TABLE `localidade` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `estacao` `estacao` VARCHAR(200) NOT NULL ;

INSERT INTO `localidade` (`id`, `estacao`, `latitude`, `longitude`, `altitude`, `coleta_inicio`, `coleta_fim`) VALUES ('1', 'AVELAR  P DO ALFERES  - RJ (OMM: 83049)', ' -22.35', '-43.41', '507.00', '2000-01-01', '2015-02-28');

  
  CREATE TABLE `dados_meteorologicos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `temp_media` DOUBLE NOT NULL,
  `umidade` INT NOT NULL,
  `flag_chuva` TINYINT(1) NULL,
  `localidade` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `localidade_fk_idx` (`localidade` ASC),
  CONSTRAINT `localidade_fk`
    FOREIGN KEY (`localidade`)
    REFERENCES `localidade` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);




SET FOREIGN_KEY_CHECKS=1;

COMMIT;