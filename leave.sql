-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 21, 2015 at 02:06 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `leave`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `uname` varchar(50) NOT NULL,
  `pass` varchar(150) NOT NULL,
  `cyear` int(10) NOT NULL,
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `dnum` int(10) NOT NULL AUTO_INCREMENT,
  `did` varchar(15) NOT NULL,
  `dname` varchar(50) NOT NULL,
  `hod` varchar(50) NOT NULL,
  PRIMARY KEY (`dnum`),
  UNIQUE KEY `did` (`did`),
  UNIQUE KEY `dname_2` (`dname`),
  KEY `dname` (`dname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `uid` int(15) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) NOT NULL,
  `pass` varchar(200) NOT NULL,
  `name` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  `role` varchar(15) NOT NULL,
  `cleave` int(10) NOT NULL,
  `bcleave` int(10) NOT NULL,
  `aleave` int(10) NOT NULL,
  `baleave` int(10) NOT NULL,
  `sleave` int(10) NOT NULL,
  `bsleave` int(11) NOT NULL,
  `dutyleave` int(10) NOT NULL DEFAULT '0',
  `lwpay` int(10) NOT NULL DEFAULT '0',
  `spl` int(10) NOT NULL DEFAULT '0',
  `anyother` int(10) NOT NULL DEFAULT '0',
  `cyear` int(10) NOT NULL,
  `regdate` varchar(15) NOT NULL,
  `regtime` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `group`
--

CREATE TABLE IF NOT EXISTS `group` (
  `gname` varchar(50) NOT NULL,
  `cleave` int(10) NOT NULL,
  `aleave` int(10) NOT NULL,
  `sleave` int(10) NOT NULL,
  PRIMARY KEY (`gname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group`
--

INSERT INTO `group` (`gname`, `cleave`, `aleave`, `sleave`) VALUES
('Default', 12, 12, 12);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `msgid` int(10) NOT NULL AUTO_INCREMENT,
  `from` varchar(50) NOT NULL,
  `ltype` varchar(50) NOT NULL,
  `fromdate` varchar(20) NOT NULL,
  `todate` varchar(20) NOT NULL,
  `days` int(10) NOT NULL,
  `to` varchar(50) NOT NULL,
  `fwdby` varchar(50) NOT NULL DEFAULT 'NONE',
  `msg` varchar(300) NOT NULL,
  `cl` int(10) NOT NULL,
  `al` int(10) NOT NULL,
  `sl` int(10) NOT NULL,
  `dl` int(10) NOT NULL,
  `lwp` int(10) NOT NULL,
  `spl` int(10) NOT NULL,
  `ao` int(10) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'pending',
  `cancel` varchar(50) NOT NULL DEFAULT 'no',
  `trash` varchar(10) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`msgid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `notif`
--

CREATE TABLE IF NOT EXISTS `notif` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `msg` varchar(500) NOT NULL,
  `to` varchar(50) NOT NULL,
  `fwdby` varchar(50) NOT NULL,
  `ondate` varchar(15) NOT NULL,
  `fromdate` varchar(15) NOT NULL,
  `todate` varchar(15) NOT NULL,
  `button` varchar(70) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'pending',
  `nstatus` varchar(10) NOT NULL DEFAULT 'unread',
  `ncheck` varchar(50) NOT NULL DEFAULT 'other',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
