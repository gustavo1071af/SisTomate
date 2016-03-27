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
/*caso tiver dados já na base, coloquei valores padrões qualquer*/
ADD COLUMN `qtd_Ruas` BIGINT NOT NULL DEFAULT '20' AFTER `data_Colheita`,
ADD COLUMN `qtd_TomaterPorLinhas` BIGINT NOT NULL DEFAULT '30' AFTER `qtd_Ruas`;



SET FOREIGN_KEY_CHECKS=1;

COMMIT;