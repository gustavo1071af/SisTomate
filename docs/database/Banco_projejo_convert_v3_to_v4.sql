/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Gustavo
SISTON-2
 * Created: Mar 27, 2016
 */
SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT=0;
START TRANSACTION;

ALTER TABLE `lavoura` 
DROP COLUMN `idLavoura`, RENAME TO `talhao` ;

ALTER TABLE `tomate` 
DROP FOREIGN KEY `fk_Tomate_Lavoura1`;
ALTER TABLE `tomate` 
CHANGE COLUMN `idLavoura` `idTalhao` VARCHAR(45) NOT NULL ;
ALTER TABLE `tomate` 
ADD CONSTRAINT `fk_Tomate_Lavoura1`
  FOREIGN KEY (`idTalhao`)
  REFERENCES `talhao` (`area_Cultivada`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `imagem_processada` 
DROP FOREIGN KEY `fk_Imagem_processada_Tomate1`;
ALTER TABLE `imagem_processada` 
CHANGE COLUMN `idLavoura` `idTalhao` VARCHAR(45) NOT NULL ;
ALTER TABLE `imagem_processada` 
ADD CONSTRAINT `fk_Imagem_processada_Tomate1`
  FOREIGN KEY (`Tomate_numtom` , `Tomate_rua` , `Tomate_linha` , `Tomate_data` , `idTalhao`)
  REFERENCES `tomate` (`numtom` , `rua` , `linha` , `data` , `idTalhao`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


SET FOREIGN_KEY_CHECKS=1;

COMMIT;