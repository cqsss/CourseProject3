/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : academicconference

Target Server Type    : MYSQL
Target Server Version : 80015
FileController Encoding         : 65001

Date: 2020-05-25 20:29:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci ,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`location`,`date`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of meeting
-- ----------------------------

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
`id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
   `type` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `publishdate` varchar(255) NOT NULL,
  `viewcount` int(11) NOT NULL default 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news

-- ----------------------------

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_manager` tinyint(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NUll,
  `sex` varchar(255) DEFAULT '保密' not null ,
  `telephone` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '123456', '1', '', null, '保密', null,  null);
INSERT INTO `user` VALUES ('hitwh', '123456', '0', '', null, '保密', null, null);

-- ----------------------------
-- Table structure for user_dl
-- ----------------------------
DROP TABLE IF EXISTS `user_dl`;
CREATE TABLE `user_dl` (
  `username` varchar(255) NOT NULL,
  `paper_id` int(11) NOT NULL,
  `downloadtime` varchar(255) NOT NULL,
  PRIMARY KEY (`username`,`paper_id`,`downloadtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_dl
-- ----------------------------

-- ----------------------------
-- Table structure for user_meetings
-- ----------------------------
DROP TABLE IF EXISTS `user_meetings`;
CREATE TABLE `user_meetings` (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `meeting_id` int(11) NOT NULL,
  PRIMARY KEY (`username`,`meeting_id`),
  KEY `meeting_id` (`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_meetings
-- ----------------------------

-- ----------------------------
-- Table structure for user_ul
-- ----------------------------
DROP TABLE IF EXISTS `user_ul`;
CREATE TABLE `user_ul` (
  `username` varchar(255) NOT NULL,
  `paper_id` int(11) NOT NULL,
  `uploadtime` varchar(255) NOT NULL,
  PRIMARY KEY (`username`,`paper_id`,`uploadtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_ul
-- ----------------------------
