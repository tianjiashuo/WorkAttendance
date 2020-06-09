/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : work_attendance

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-05-23 00:04:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `attendance`
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_no` int NOT NULL,
  `emp_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', '1', 'maowenrui', '2020-05-10 09:09:09');
INSERT INTO `attendance` VALUES ('2', '2', 'tianjiashuo', '2020-05-10 09:10:09');
INSERT INTO `attendance` VALUES ('3', '1', 'maowenrui', '2020-05-10 19:09:09');
INSERT INTO `attendance` VALUES ('4', '2', 'tianjiashuo', '2020-05-10 19:10:09');
INSERT INTO `attendance` VALUES ('5', '3', 'baijiax', '2020-05-20 09:09:09');
INSERT INTO `attendance` VALUES ('6', '4', 'liqinying', '2020-05-20 09:10:09');
INSERT INTO `attendance` VALUES ('7', '3', 'baijiax', '2020-05-20 19:09:09');
INSERT INTO `attendance` VALUES ('8', '4', 'liqinying', '2020-05-20 19:10:09');

-- ----------------------------
-- Table structure for `checktable`
-- ----------------------------
DROP TABLE IF EXISTS `checktable`;
CREATE TABLE `checktable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_no` int NOT NULL,
  `emp_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `days` int NOT NULL,
  `absenteeism` int NOT NULL,
  `late_days` int NOT NULL,
  `leave_early_days` int NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of checktable
-- ----------------------------
INSERT INTO `checktable` VALUES ('1', '2', 'tianjiashuo', '3', '4', '5', '6', '2020-05-10 09:09:09');

-- ----------------------------
-- Table structure for `goout`
-- ----------------------------
DROP TABLE IF EXISTS `goout`;
CREATE TABLE `goout` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_no` int NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `state` tinyint(1) NOT NULL,
  `division_manager_state` tinyint(1) NOT NULL,
  `vice_manager_state` tinyint(1) NOT NULL,
  `manager_state` tinyint(1) NOT NULL,
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goout
-- ----------------------------
INSERT INTO `goout` VALUES ('1', '3', '2020-05-21 11:16:42', '2020-05-22 11:16:48', '外出', '1', '1', '0', '0', 'baijiax');

-- ----------------------------
-- Table structure for `leave_balances`
-- ----------------------------
DROP TABLE IF EXISTS `leave_balances`;
CREATE TABLE `leave_balances` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_no` int NOT NULL,
  `leave_id` int NOT NULL,
  `days` int NOT NULL,
  `balances` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of leave_balances
-- ----------------------------

-- ----------------------------
-- Table structure for `leave_request`
-- ----------------------------
DROP TABLE IF EXISTS `leave_request`;
CREATE TABLE `leave_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_no` int NOT NULL,
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` int NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `state` tinyint(1) NOT NULL,
  `division_manager_state` tinyint(1) NOT NULL,
  `vice_manager_state` tinyint(1) NOT NULL,
  `manager_state` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of leave_request
-- ----------------------------
INSERT INTO `leave_request` VALUES ('1', '4', 'liqinying', '4', '2020-05-21 22:30:08', '2020-05-23 22:30:11', '想修年假', '1', '0', '0', '0');
INSERT INTO `leave_request` VALUES ('2', '1', 'maowenrui', '5', '2020-05-22 17:04:10', '2020-05-25 17:04:10', '有事', '1', '0', '0', '0');
INSERT INTO `leave_request` VALUES ('3', '2', 'tianjiashuo', '4', '2020-04-21 22:30:08', '2020-05-26 22:30:11', '想修年假', '1', '0', '0', '0');
INSERT INTO `leave_request` VALUES ('4', '4', 'liqinying', '5', '2020-05-21 22:30:08', '2020-05-21 22:30:11', '修事假', '1', '0', '0', '0');

-- ----------------------------
-- Table structure for `leave_types`
-- ----------------------------
DROP TABLE IF EXISTS `leave_types`;
CREATE TABLE `leave_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `leave_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `paid` tinyint NOT NULL,
  `days` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of leave_types
-- ----------------------------
INSERT INTO `leave_types` VALUES ('1', '婚假', '1', '14');
INSERT INTO `leave_types` VALUES ('2', '产假', '1', '98');
INSERT INTO `leave_types` VALUES ('3', '陪产假', '1', '7');
INSERT INTO `leave_types` VALUES ('4', '年假', '1', '15');
INSERT INTO `leave_types` VALUES ('5', '事假', '0', '0');
INSERT INTO `leave_types` VALUES ('6', '病假', '0', '0');

-- ----------------------------
-- Table structure for `position`
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `powerId` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '普通员工', '1');
INSERT INTO `position` VALUES ('2', '行政部员工', '2');
INSERT INTO `position` VALUES ('3', '财务部员工', '3');
INSERT INTO `position` VALUES ('4', '经理', '4');

-- ----------------------------
-- Table structure for `power`
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `id` int NOT NULL AUTO_INCREMENT,
  `positionId` int NOT NULL,
  `view_own_attendance` tinyint(1) NOT NULL,
  `view_all_attendance` tinyint(1) NOT NULL,
  `view_check` tinyint(1) NOT NULL,
  `view_all_leave` tinyint(1) NOT NULL,
  `view_pass_leave` tinyint(1) NOT NULL,
  `view_all_goout` tinyint(1) NOT NULL,
  `view_pass_goout` tinyint(1) NOT NULL,
  `set_annual_leave` tinyint(1) NOT NULL,
  `set_leave_types` tinyint(1) NOT NULL,
  `leave_approval` tinyint(1) NOT NULL,
  `goout_approval` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', '1', '1', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0');
INSERT INTO `power` VALUES ('2', '2', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0');
INSERT INTO `power` VALUES ('3', '3', '1', '0', '1', '0', '1', '0', '1', '0', '0', '0', '0');
INSERT INTO `power` VALUES ('4', '4', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_no` int NOT NULL,
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(28) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `power` int NOT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `years` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'maowenrui', '123456', '1', '无薪休假', '20');
INSERT INTO `user` VALUES ('2', '2', 'tianjiashuo', '123456', '2', '在公司', '19');
INSERT INTO `user` VALUES ('3', '3', 'baijiax', '123456', '3', '外出', '21');
INSERT INTO `user` VALUES ('4', '4', 'liqinying', '123456', '4', '带薪休假', '20');
