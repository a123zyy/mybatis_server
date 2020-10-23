/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost
 Source Database       : blog_db

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : utf-8

 Date: 10/23/2020 18:39:07 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `comment_info`
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) NOT NULL COMMENT '发评论id',
  `parent_uid` int(11) DEFAULT NULL COMMENT '父uid',
  `parent_id` int(8) NOT NULL DEFAULT '0' COMMENT '父commentid',
  `comment_content` varchar(255) NOT NULL,
  `post_id` int(8) NOT NULL COMMENT '帖子id',
  `comment_name` varchar(32) NOT NULL DEFAULT '' COMMENT '评论者name',
  `comment_head` varchar(255) NOT NULL COMMENT '评论者头像',
  `status` int(2) DEFAULT '1' COMMENT '1正常 0不正常',
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `comment_info`
-- ----------------------------
BEGIN;
INSERT INTO `comment_info` VALUES ('2', '3', '0', '0', '哈哈哈', '1', '胨6576', 'https://dev-qiniu-dp.xfun233.com/2bc456ab131343c3a7b9ee69e4f0cd23.jpg', '1', '1596439246000'), ('14', '6', '3', '2', '你是什么我给胨君的。。。。', '1', '鏥5012', 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', '1', '1602647379678'), ('16', '3', '6', '2', '我给宿君哦！', '1', '胨6576', 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', '1', '1602653053492'), ('18', '7', '3', '2', '你好胨君~', '1', '嚂8726', 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', '1', '1602657805634'), ('19', '7', '0', '0', '现在只有二级评论啦', '1', '嚂8726', 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', '1', '1602657827406');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `hobby_info`
-- ----------------------------
BEGIN;
INSERT INTO `hobby_info` VALUES ('1', '1', '看书', '1'), ('2', '1', '看电影', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `label_info`
-- ----------------------------
BEGIN;
INSERT INTO `label_info` VALUES ('1', 'java', '1', '1', '0'), ('2', 'vue', '1', '1', '0');
COMMIT;

-- ----------------------------
--  Table structure for `login_info`
-- ----------------------------
DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `login_type` int(8) NOT NULL DEFAULT '1' COMMENT '1 密码验证码  2手机号登录   3第三方登录',
  `lastTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(255) DEFAULT '1' COMMENT '1 正常 0注销 ',
  `userid` int(11) DEFAULT '0' COMMENT '关联了用户表',
  `roleid` int(32) DEFAULT NULL,
  `phone_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `login_info`
-- ----------------------------
BEGIN;
INSERT INTO `login_info` VALUES ('1', 'root123', 'root123', '0', '2020-08-24 10:30:00', null, '1', null, null), ('2', 'admin123', 'admin123', '0', '2020-08-21 03:41:04', null, '2', null, null), ('3', 'zyy123', 'zyy123', '1', '2020-10-14 05:23:15', null, '3', null, null), ('4', 'ZYJ123', 'yy123456', '1', '2020-10-14 03:09:55', null, '6', null, null), ('5', 'mao123', 'mao123', '1', '2020-10-14 06:55:17', null, '7', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `post_info`
-- ----------------------------
DROP TABLE IF EXISTS `post_info`;
CREATE TABLE `post_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `post_content` longtext NOT NULL COMMENT '帖子 内容富文本',
  `post_name` varchar(32) NOT NULL COMMENT '帖子标题',
  `post_type` int(2) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT '1' COMMENT '0 删除 1可用',
  `user_id` int(32) DEFAULT NULL,
  `like_count` varchar(255) DEFAULT NULL COMMENT '文章点赞总数',
  `label_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `post_info`
-- ----------------------------
BEGIN;
INSERT INTO `post_info` VALUES ('1', '<p><strong>这是一个晴朗的暂存</strong></p><p>吃的晚滑板车保密文学技能</p>\\n\\n<p style=\\\"text-align:center\\\"><img alt=\\\"\\\" src=\\\"https://img-blog.csdnimg.cn/20200831151126487.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5MjY0ODk3,size_16,color_FFFFFF,t_70\\\" /></p>\\n\\n<p>哈哈哈</p>\\n', '关于我是谁', '1', '2020-08-22 11:42:24', '1', '1', '7', '1'), ('2', '<p><strong>这是一个晴朗的暂存</strong></p>\\n\\n<p>吃的晚滑板车保密文学技能</p>\\n\\n<p style=\\\"text-align:center\\\"><img alt=\\\"\\\" src=\\\"https://img-blog.csdnimg.cn/20200831151126487.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5MjY0ODk3,size_16,color_FFFFFF,t_70\\\" /></p>\\n\\n<p>哈哈哈</p>\\n', '胨6576', '1', '2019-08-21 11:48:56', '1', '3', null, '1'), ('3', '<p><strong>这是一个晴朗的暂存</strong></p>\\n\\n<p>吃的晚滑板车保密文学技能</p>\\n\\n<p style=\\\"text-align:center\\\"><img alt=\\\"\\\" src=\\\"https://img-blog.csdnimg.cn/20200831151126487.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5MjY0ODk3,size_16,color_FFFFFF,t_70\\\" /></p>\\n\\n<p>哈哈哈</p>\\n', '胨6576', '1', '2018-08-21 11:48:56', '1', '3', null, '1'), ('4', '<p><strong>这是一个晴朗的暂存</strong></p>\\n\\n<p>吃的晚滑板车保密文学技能</p>\\n\\n<p style=\\\"text-align:center\\\"><img alt=\\\"\\\" src=\\\"https://img-blog.csdnimg.cn/20200831151126487.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5MjY0ODk3,size_16,color_FFFFFF,t_70\\\" /></p>\\n\\n<p>哈哈哈</p>\\n', '胨6576', '1', '2019-08-21 11:48:56', '1', '3', null, '1'), ('5', '<p><strong>这是一个晴朗的暂存</strong></p>\\n\\n<p>吃的晚滑板车保密文学技能</p>\\n\\n<p style=\\\"text-align:center\\\"><img alt=\\\"\\\" src=\\\"https://img-blog.csdnimg.cn/20200831151126487.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5MjY0ODk3,size_16,color_FFFFFF,t_70\\\" /></p>\\n\\n<p>哈哈哈</p>\\n', '胨6576', '1', '2020-08-21 11:48:56', '1', '3', null, '1'), ('6', '<p><strong>关于vue</strong></p>\\n\\n<p>吃的晚滑板车保密文学技能</p>\\n\\n<p style=\\\"text-align:center\\\"><img alt=\\\"\\\" src=\\\"https://img-blog.csdnimg.cn/20200831151126487.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5MjY0ODk3,size_16,color_FFFFFF,t_70\\\" /></p>\\n\\n<p>哈哈哈</p>\\n', 'vue路由的理解', '1', '2019-08-21 11:48:56', '1', '3', null, '2');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_info`
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('1', '羁3781', '2', 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', null, '这个人什么都没留下~'), ('2', '绰6816', '3', 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', null, '这个人什么都没留下~'), ('3', '胨6576', '2', 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', null, '这个人什么都没留下~'), ('5', '嵓2634', null, 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', null, '这个人什么都没留下~'), ('6', '鏥5012', null, 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', null, '这个人什么都没留下~'), ('7', '嚂8726', null, 'https://img.blkbst.com/f74648b9faf64170880b92becd1e968b.png', null, '这个人什么都没留下~');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
