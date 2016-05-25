/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Gustavo
SISTON-12
 * Created: May 23, 2016
 */
SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT=0;
START TRANSACTION;

ALTER TABLE `dados_meteorologicos` 
CHANGE COLUMN `umidade` `umidade` DOUBLE NOT NULL ,
ADD COLUMN `temp_min` DOUBLE NOT NULL AFTER `flag_chuva`,
ADD COLUMN `temp_max` DOUBLE NOT NULL AFTER `temp_mini`,
ADD COLUMN `precipitacao` DOUBLE NOT NULL AFTER `temp_max`;

SET FOREIGN_KEY_CHECKS=1;

COMMIT;