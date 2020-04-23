/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : academicconference

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-04-23 20:03:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`name`,`location`,`date`),
  KEY `id` (`id`),UNIQUE (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meeting
-- ----------------------------
INSERT INTO `meeting` VALUES ('hitwh', '南康', '2020-04-22', '', '0');
INSERT INTO `meeting` VALUES ('操作系统', '威海', '2020-04-15', '', '1');
INSERT INTO `meeting` VALUES ('软件工程', '日本东京', '2020-04-15', '', '2');
INSERT INTO `meeting` VALUES ('软件工程', '日本名古屋市立大学', '2020-04-15', '', '3');
