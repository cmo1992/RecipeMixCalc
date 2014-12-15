-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 10, 2014 at 09:24 PM
-- Server version: 5.5.37-MariaDB
-- PHP Version: 5.5.14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `imrdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `compk` int(5) NOT NULL AUTO_INCREMENT,
  `comname` char(30) NOT NULL,
  PRIMARY KEY (`compk`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`compk`, `comname`) VALUES
(1, 'CMO''s Inc.'),
(3, 'Imperial Mills');

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE IF NOT EXISTS `ingredients` (
  `ingpk` int(5) NOT NULL AUTO_INCREMENT,
  `ingredient` char(20) NOT NULL,
  PRIMARY KEY (`ingpk`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`ingpk`, `ingredient`) VALUES
(1, 'Amaranth'),
(2, 'White Rice'),
(3, 'Brown Rice'),
(4, 'Sorgum'),
(5, 'Sorgum'),
(6, 'Sorgum'),
(7, 'Salt'),
(8, 'Sugar'),
(9, 'Baking Powder'),
(10, 'Brown Rice Flour');

-- --------------------------------------------------------

--
-- Table structure for table `ingtorec`
--

CREATE TABLE IF NOT EXISTS `ingtorec` (
  `irpk` int(7) NOT NULL AUTO_INCREMENT,
  `recpk` int(5) NOT NULL,
  `ingpk` int(5) NOT NULL,
  `percentage` float NOT NULL,
  PRIMARY KEY (`irpk`),
  KEY `recpk` (`recpk`),
  KEY `ingpk` (`ingpk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `recipes`
--

CREATE TABLE IF NOT EXISTS `recipes` (
  `recpk` int(5) NOT NULL AUTO_INCREMENT,
  `recipe` char(40) NOT NULL,
  PRIMARY KEY (`recpk`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `recipes`
--

INSERT INTO `recipes` (`recpk`, `recipe`) VALUES
(1, 'Test Panacakes'),
(2, 'Test Panacakes');

-- --------------------------------------------------------

--
-- Table structure for table `rectocom`
--

CREATE TABLE IF NOT EXISTS `rectocom` (
  `rcpk` int(5) NOT NULL AUTO_INCREMENT,
  `compk` int(5) NOT NULL,
  `recpk` int(5) NOT NULL,
  PRIMARY KEY (`rcpk`),
  KEY `compk` (`compk`),
  KEY `recpk` (`recpk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ingtorec`
--
ALTER TABLE `ingtorec`
  ADD CONSTRAINT `ingtorec_ibfk_2` FOREIGN KEY (`ingpk`) REFERENCES `ingredients` (`ingPK`),
  ADD CONSTRAINT `ingtorec_ibfk_3` FOREIGN KEY (`recpk`) REFERENCES `rectocom` (`rcpk`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
