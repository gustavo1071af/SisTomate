/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Gustavo
 * Created: Mar 27, 2016
 */
SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT=0;
START TRANSACTION;

ALTER TABLE `lavoura` 
CHANGE COLUMN `qtd_Ruas` `qtd_Ruas` INT NOT NULL DEFAULT '20' ,
CHANGE COLUMN `qtd_TomaterPorLinhas` `qtd_TomatesPorLinhas` BIGINT(20) NOT NULL DEFAULT '30' ;



SET FOREIGN_KEY_CHECKS=1;

COMMIT;