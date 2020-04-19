/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : academicconference

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-04-15 20:24:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`name`,`location`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meeting
-- ----------------------------
INSERT INTO `meeting` VALUES ('hitwh', '山东威海环翠器文化西路', '初始化必备', '122.09', '37.54', '2020-01-01');
INSERT INTO `meeting` VALUES ('操作系统', '在家里', '上网课', '114.75', '25.8', '2020-04-15');
INSERT INTO `meeting` VALUES ('软件工程', '在家里', '上网课', '114.75', '25.8', '2020-04-15');
