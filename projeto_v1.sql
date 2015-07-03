-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Máquina: 127.0.0.1
-- Data de Criação: 03-Jul-2015 às 06:03
-- Versão do servidor: 5.5.32
-- versão do PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `projeto`
--
CREATE DATABASE IF NOT EXISTS `projeto` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projeto`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `imagem_processada`
--

CREATE TABLE IF NOT EXISTS `imagem_processada` (
  `nomearquivo` varchar(45) DEFAULT NULL,
  `vermelhos` int(11) DEFAULT NULL,
  `verdes` int(11) DEFAULT NULL,
  `pretos` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `Tomate_numtom` varchar(45) NOT NULL,
  `Tomate_rua` varchar(45) NOT NULL,
  `Tomate_linha` varchar(45) NOT NULL,
  `Tomate_data` date NOT NULL,
  PRIMARY KEY (`Tomate_numtom`,`Tomate_rua`,`Tomate_linha`,`Tomate_data`),
  KEY `fk_Imagem_processada_Tomate_idx` (`Tomate_numtom`,`Tomate_rua`,`Tomate_linha`,`Tomate_data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `imagem_processada`
--

INSERT INTO `imagem_processada` (`nomearquivo`, `vermelhos`, `verdes`, `pretos`, `estado`, `Tomate_numtom`, `Tomate_rua`, `Tomate_linha`, `Tomate_data`) VALUES
('COR_DNT_IMG_002001.jpg', 5408, 7931, 12053, '5', '1', '2', 'a', '2015-05-10'),
('DNT_IMG_004001', 6762, 252396, NULL, '1', '1', '4', 'a', '2015-05-10'),
('COR_teste1070.jpg', 5137, 10334, 9921, '4', '1', '5', 'a', '2015-05-26'),
('COR_imgteste1.jpg', 6551, 7719, 11122, '6', '1', '7', 'a', '2015-06-09'),
('DNT_IMG_002010', 32216, 232644, NULL, '2', '10', '2', 'a', '2015-05-10'),
('DNT_IMG_004010', 38250, 238627, NULL, '2', '10', '4', 'a', '2015-05-10'),
('COR_DNT_IMG_001011.jpg', 997, 18438, 5957, '1', '11', '1', 'b', '2015-05-10'),
('DNT_IMG_002011', 20073, 252193, NULL, '1', '11', '2', 'b', '2015-05-10'),
('DNT_IMG_003011', 50431, 216938, NULL, '3', '11', '3', 'b', '2015-05-10'),
('DNT_IMG_004011', 15393, 254166, NULL, '1', '11', '4', 'b', '2015-05-10'),
('DNT_IMG_003012', 43122, 197895, NULL, '3', '12', '3', 'b', '2015-05-10'),
('DNT_IMG_004012', 31843, 231161, NULL, '2', '12', '4', 'b', '2015-05-10'),
('DNT_IMG_004013', 26832, 252662, NULL, '2', '13', '4', 'b', '2015-05-10'),
('DNT_IMG_001014', 11676, 250375, NULL, '1', '14', '1', 'b', '2015-05-10'),
('DNT_IMG_002014', 23581, 241811, NULL, '2', '14', '2', 'b', '2015-05-10'),
('DNT_IMG_003014', 16434, 261505, NULL, '1', '14', '3', 'b', '2015-05-10'),
('DNT_IMG_004014', 63322, 194187, NULL, '4', '14', '4', 'b', '2015-05-10'),
('DNT_IMG_001015', 26160, 217161, NULL, '1', '15', '1', 'b', '2015-05-10'),
('DNT_IMG_002015', 3709, 270859, NULL, '1', '15', '2', 'b', '2015-05-10'),
('DNT_IMG_003015', 77316, 186029, NULL, '4', '15', '3', 'b', '2015-05-10'),
('DNT_IMG_004015', 40426, 215828, NULL, '3', '15', '4', 'b', '2015-05-10'),
('DNT_IMG_0010016', 8896, 248663, NULL, '1', '16', '1', 'a', '2015-05-10'),
('DNT_IMG_002016', 22283, 206990, NULL, '2', '16', '2', 'b', '2015-05-10'),
('DNT_IMG_003016', 34889, 229624, NULL, '2', '16', '3', 'b', '2015-05-10'),
('DNT_IMG_004016', 28613, 217861, NULL, '2', '16', '4', 'b', '2015-05-10'),
('DNT_IMG_001002', 3321, 264936, NULL, '1', '2', '1', 'a', '2015-05-10'),
('DNT_IMG_004002', 36837, 228616, NULL, '2', '2', '4', 'a', '2015-05-10'),
('COR_teste1071.jpg', 2850, 11703, 10839, '3', '2', '5', 'a', '2015-05-26'),
('COR_imgteste2.jpg', 2855, 10665, 11872, '3', '2', '7', 'a', '2015-06-09'),
('DNT_IMG_004003', 85438, 189891, NULL, '4', '3', '4', 'a', '2015-05-10'),
('COR_teste1072.jpg', 3660, 14573, 7159, '3', '3', '6', 'a', '2015-06-02'),
('DNT_IMG_001004', 16622, 247239, NULL, '1', '4', '1', 'a', '2015-05-10'),
('DNT_IMG_003004', 9361, 259515, NULL, '1', '4', '3', 'a', '2015-05-10'),
('DNT_IMG_001005', 12048, 249372, NULL, '1', '5', '1', 'a', '2015-05-10'),
('DNT_IMG_001006', 15412, 255656, NULL, '1', '6', '1', 'b', '2015-05-10'),
('DNT_IMG_002006', 22831, 258142, NULL, '2', '6', '2', 'a', '2015-05-10'),
('DNT_IMG_004006', 38819, 213789, NULL, '3', '6', '4', 'a', '2015-05-10'),
('DNT_IMG_001007', 22899, 243755, NULL, '2', '7', '1', 'b', '2015-05-10'),
('DNT_IMG_002007', 28449, 217660, NULL, '2', '7', '2', 'a', '2015-05-10'),
('DNT_IMG_003007', 15179, 243325, NULL, '1', '7', '3', 'a', '2015-05-10'),
('DNT_IMG_004007', 83117, 192264, NULL, '4', '7', '4', 'a', '2015-05-10'),
('DNT_IMG_001008', 37406, 216532, NULL, '2', '8', '1', 'b', '2015-05-10'),
('DNT_IMG_002008', 23115, 250380, NULL, '2', '8', '2', 'a', '2015-05-10'),
('DNT_IMG_003008', 48450, 209487, NULL, '3', '8', '3', 'a', '2015-05-10'),
('DNT_IMG_001009', 15019, 255324, NULL, '1', '9', '1', 'b', '2015-05-10'),
('DNT_IMG_002009', 20069, 234938, NULL, '2', '9', '2', 'a', '2015-05-10'),
('DNT_IMG_003009', 11192, 250321, NULL, '1', '9', '3', 'a', '2015-05-10');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tomate`
--

CREATE TABLE IF NOT EXISTS `tomate` (
  `nomearquivo` varchar(45) NOT NULL,
  `numtom` varchar(45) NOT NULL,
  `rua` varchar(45) NOT NULL,
  `linha` varchar(45) NOT NULL,
  `lat` varchar(45) DEFAULT NULL,
  `longi` varchar(45) DEFAULT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`numtom`,`rua`,`linha`,`data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tomate`
--

INSERT INTO `tomate` (`nomearquivo`, `numtom`, `rua`, `linha`, `lat`, `longi`, `data`) VALUES
('DNT_IMG_002001.jpg', '1', '2', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_004001.jpg', '1', '4', 'a', NULL, NULL, '2015-05-10'),
('teste1070.jpg', '1', '5', 'a', NULL, NULL, '2015-05-26'),
('imgteste1.jpg', '1', '7', 'a', NULL, NULL, '2015-06-09'),
('DNT_IMG_002010.jpg', '10', '2', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_004010.jpg', '10', '4', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_001011.jpg', '11', '1', 'b', '-', NULL, '2015-05-10'),
('DNT_IMG_002011.jpg', '11', '2', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_003011.jpg', '11', '3', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_004011.jpg', '11', '4', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_003012.jpg', '12', '3', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_004012.jpg', '12', '4', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_004013.jpg', '13', '4', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_001014.jpg', '14', '1', 'b', '-', NULL, '2015-05-10'),
('DNT_IMG_002014.jpg', '14', '2', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_003014.jpg', '14', '3', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_004014.jpg', '14', '4', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_001015.jpg', '15', '1', 'b', '-', NULL, '2015-05-10'),
('DNT_IMG_002015.jpg', '15', '2', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_003015.jpg', '15', '3', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_004015.jpg', '15', '4', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_0010016.jpg', '16', '1', 'a', '-', NULL, '2015-05-10'),
('DNT_IMG_002016.jpg', '16', '2', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_003016.jpg', '16', '3', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_004016.jpg', '16', '4', 'b', NULL, NULL, '2015-05-10'),
('DNT_IMG_001002.jpg', '2', '1', 'a', '-', NULL, '2015-05-10'),
('DNT_IMG_004002.jpg', '2', '4', 'a', NULL, NULL, '2015-05-10'),
('teste1071.jpg', '2', '5', 'a', NULL, NULL, '2015-05-26'),
('imgteste2.jpg', '2', '7', 'a', NULL, NULL, '2015-06-09'),
('DNT_IMG_004003.jpg', '3', '4', 'a', NULL, NULL, '2015-05-10'),
('teste1072.jpg', '3', '6', 'a', NULL, NULL, '2015-06-02'),
('DNT_IMG_001004.jpg', '4', '1', 'a', '-', NULL, '2015-05-10'),
('DNT_IMG_003004.jpg', '4', '3', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_001005.jpg', '5', '1', 'a', '-', NULL, '2015-05-10'),
('DNT_IMG_001006.jpg', '6', '1', 'b', '-', NULL, '2015-05-10'),
('DNT_IMG_002006.jpg', '6', '2', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_004006.jpg', '6', '4', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_001007.jpg', '7', '1', 'b', '-', NULL, '2015-05-10'),
('DNT_IMG_002007.jpg', '7', '2', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_003007.jpg', '7', '3', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_004007.jpg', '7', '4', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_001008.jpg', '8', '1', 'b', '-', NULL, '2015-05-10'),
('DNT_IMG_002008.jpg', '8', '2', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_003008.jpg', '8', '3', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_001009.jpg', '9', '1', 'b', '-', NULL, '2015-05-10'),
('DNT_IMG_002009.jpg', '9', '2', 'a', NULL, NULL, '2015-05-10'),
('DNT_IMG_003009.jpg', '9', '3', 'a', NULL, NULL, '2015-05-10');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `imagem_processada`
--
ALTER TABLE `imagem_processada`
  ADD CONSTRAINT `fk_Imagem_processada_Tomate` FOREIGN KEY (`Tomate_numtom`, `Tomate_rua`, `Tomate_linha`, `Tomate_data`) REFERENCES `tomate` (`numtom`, `rua`, `linha`, `data`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
