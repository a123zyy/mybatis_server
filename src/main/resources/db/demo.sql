/*
 Navicat Premium Data Transfer

 Source Server         : 127localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost
 Source Database       : demo

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : utf-8

 Date: 08/20/2020 18:46:38 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `comment_info`
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) NOT NULL COMMENT '发帖人id',
  `parent_id` int(8) NOT NULL DEFAULT '0' COMMENT '父id',
  `comment_content` varchar(255) NOT NULL,
  `post_id` int(8) NOT NULL,
  `comment_name` varchar(32) NOT NULL,
  `comment_head` varchar(32) NOT NULL,
  `status` int(2) DEFAULT '1' COMMENT '1正常 0不正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `hobby_info`
-- ----------------------------
DROP TABLE IF EXISTS `hobby_info`;
CREATE TABLE `hobby_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) DEFAULT NULL,
  `hobby` varchar(32) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '1正常  0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `hobby_info`
-- ----------------------------
BEGIN;
INSERT INTO `hobby_info` VALUES ('1', '1', '看书', '1');
COMMIT;

-- ----------------------------
--  Table structure for `label_info`
-- ----------------------------
DROP TABLE IF EXISTS `label_info`;
CREATE TABLE `label_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `lable` varchar(32) NOT NULL,
  `user_id` int(16) DEFAULT NULL,
  `status` int(2) DEFAULT '1' COMMENT '1 可用  0 不可用',
  `label_count` int(16) DEFAULT '0' COMMENT '帖子的统计量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `label_info`
-- ----------------------------
BEGIN;
INSERT INTO `label_info` VALUES ('1', 'java', '1', '1', '0');
COMMIT;

-- ----------------------------
--  Table structure for `login_info`
-- ----------------------------
DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(8) NOT NULL DEFAULT '1' COMMENT '1 密码验证码  2手机号登录   3第三方登录',
  `lastTime` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT '1' COMMENT '1 正常 0注销 ',
  `userid` int(11) DEFAULT '0' COMMENT '关联了用户表',
  `roleid` int(32) DEFAULT NULL,
  `phone_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `login_info`
-- ----------------------------
BEGIN;
INSERT INTO `login_info` VALUES ('1', 'root123', 'root123', '3', '2020-08-17 06:05:33', '1', '1', null, null), ('2', 'admin123', 'admin123', '2', '2020-08-14 11:13:42', '1', '2', null, null), ('3', '阿洋洋', '123456', '0', '2020-08-19 09:42:44', '1', '1', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `post_info`
-- ----------------------------
DROP TABLE IF EXISTS `post_info`;
CREATE TABLE `post_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `post_content` varchar(255) NOT NULL COMMENT '帖子 内容富文本',
  `post_name` varchar(32) NOT NULL COMMENT '帖子标题',
  `post_type` int(2) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '0 删除 1可用',
  `user_id` int(32) DEFAULT NULL,
  `label_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(8) DEFAULT NULL,
  `age` int(8) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `comment` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_info`
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('1', '阿洋洋', null, 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', null, '这个人什么都没留下~');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
