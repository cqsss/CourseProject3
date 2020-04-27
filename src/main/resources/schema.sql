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
INSERT INTO `meeting` VALUES ('hitwh', '南康', '2020-04-22', 'http://www.hitwh.edu.cn/', '0');
INSERT INTO `meeting` VALUES ('操作系统', '威海', '2020-04-15', 'http://www.hitwh.edu.cn/', '1');
INSERT INTO `meeting` VALUES ('软件工程', '日本东京', '2020-04-15', 'http://www.hitwh.edu.cn/', '2');
INSERT INTO `meeting` VALUES ('软件工程', '日本名古屋市立大学', '2020-04-15', 'http://www.hitwh.edu.cn/', '3');


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_manager` tinyint(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL CHECK (sex='男'||sex='女'),
  `telephone` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`username`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '123456', '1', '', null, null, null, null, '1');
INSERT INTO `user` VALUES ('hitwh', '123456', '0', '', null, null, null, null, '2');


/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : academicconference

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-04-26 11:35:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_meetings
-- ----------------------------
DROP TABLE IF EXISTS `user_meetings`;
CREATE TABLE `user_meetings` (
  `user_id` int(11) NOT NULL,
  `meeting_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_meetings
-- ----------------------------
INSERT INTO `user_meetings` VALUES ('2', '1');
INSERT INTO `user_meetings` VALUES ('2', '2');
