/*
 Navicat Premium Data Transfer

 Source Server         : mysql_connect
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 10/01/2024 22:59:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(0) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `book_author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `book_version` int(0) NULL DEFAULT NULL,
  `book_publish_house` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `book_grade` float NULL DEFAULT NULL,
  `book_download_num` int(0) NULL DEFAULT NULL,
  `book_click_num` int(0) NULL DEFAULT NULL,
  `book_uploader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `book_points` int(0) NULL DEFAULT NULL,
  `book_profile` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `book_cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `book_cover_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `book_file_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`book_id`) USING BTREE,
  UNIQUE INDEX `book_cover_uuid`(`book_cover_uuid`) USING BTREE,
  UNIQUE INDEX `book_file_uuid`(`book_file_uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '毛泽东选集', '毛泽东', 525252, '人民出版社', 9.9, 999, 10, '3146624068@qq.com', 99, '最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/d2cd9aa0-67e7-4d97-ad20-2ba640cc7ef6.jpg', 'Cover/d2cd9aa0-67e7-4d97-ad20-2ba640cc7ef6.jpg', 'Material/146fe016-81be-407d-8892-d53fa2ad7762.txt', '文学', 'literture', NULL, NULL);
INSERT INTO `book` VALUES (2, 'Java编程思想', 'Bruce Eckel', 2, '机械工业出版社', 8.5, 1200, 1500, 'user1@example.com', 50, '深入浅出Java编程', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/abb74761-50e6-4bcb-970f-bc59d8d2e768.png', 'Cover/abb74761-50e6-4bcb-970f-bc59d8d2e768.png', 'Books/39373567-c157-4e8e-abf2-6e5c02c12d71.txt', '技术', 'technology', NULL, NULL);
INSERT INTO `book` VALUES (3, 'Python核心编程', 'Wesley J. Chun', 3, '人民邮电出版社', 9, 800, 1000, 'user2@example.com', 60, '掌握Python核心技术', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/e14bc3c5-5a6d-48c7-96fb-d7a96384abdc.png', 'Cover/e14bc3c5-5a6d-48c7-96fb-d7a96384abdc.png', 'Books/1ee379f4-2b03-44ac-a92f-18520ebd1585.txt', '技术', 'technology', NULL, NULL);
INSERT INTO `book` VALUES (4, '数据库系统概念', 'Abraham Silberschatz', 6, '机械工业出版社', 8.8, 600, 900, 'user3@example.com', 70, '数据库系统的经典教材', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/adf33e63-f043-4924-874b-3512495fd41f.png', 'Cover/adf33e63-f043-4924-874b-3512495fd41f.png', 'Books/0aa1cbc9-92e1-463c-970d-f428645dea62.txt', '科学', 'science', NULL, NULL);
INSERT INTO `book` VALUES (5, '算法导论', 'Thomas H. Cormen', 4, '机械工业出版社', 9.5, 1500, 2000, 'user4@example.com', 80, '计算机算法领域的经典之作', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/e3ac576f-9d49-44b2-bbf7-66b11e207e2d.png', 'Cover/e3ac576f-9d49-44b2-bbf7-66b11e207e2d.png', 'Books/02de7efc-2f0e-4206-b905-9f9b2ce87c31.txt', '算法', 'algorithm', NULL, NULL);
INSERT INTO `book` VALUES (6, '人类简史', '尤瓦尔·赫拉利', 5, '中信出版社', 9.2, 1000, 1200, 'user5@example.com', 90, '人类历史的综合性介绍', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/8c60fc8c-a97b-4bf5-9c0d-c8efd614ece8.png', 'Cover/8c60fc8c-a97b-4bf5-9c0d-c8efd614ece8.png', 'Books/b046ded6-343d-4ef6-96e6-9882139575ff.txt', '历史', 'history', NULL, NULL);
INSERT INTO `book` VALUES (7, '西游记', '吴承恩', 10, '人民文学出版社', 9.7, 2000, 2500, 'user6@example.com', 100, '中国古代小说四大名著之一', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/b43571fc-9dd4-4533-84a8-c3f17e782edd.png', 'Cover/b43571fc-9dd4-4533-84a8-c3f17e782edd.png', 'Books/0a36827b-8ca5-4762-a2cf-4172bbfcccef.txt', '文学', 'literture', NULL, NULL);
INSERT INTO `book` VALUES (8, '三体', '刘慈欣', 3, '重庆出版社', 9.8, 1800, 2100, 'user7@example.com', 95, '硬科幻巅峰之作', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/0e379ce2-b581-482f-b6e9-dddf254f4ba7.png', 'Cover/0e379ce2-b581-482f-b6e9-dddf254f4ba7.png', 'Books/aaac7c58-6866-49c4-9577-788c50016e70.txt', '科幻', 'fiction', NULL, NULL);
INSERT INTO `book` VALUES (9, '霍乱时期的爱情', '加西亚·马尔克斯', 8, '南海出版公司', 9.6, 1300, 1600, 'user8@example.com', 85, '拉丁美洲文学巨匠的代表作', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/6622066b-4815-4891-a1ff-78ad4005bdb9.png', 'Cover/6622066b-4815-4891-a1ff-78ad4005bdb9.png', 'Books/ffbb25ca-b1b8-4709-8c5e-bd1424ce2d72.txt', '爱情', 'love', NULL, NULL);
INSERT INTO `book` VALUES (10, '红楼梦', '曹雪芹', 15, '人民文学出版社', 9.9, 2500, 3000, 'user9@example.com', 110, '中国古典小说四大名著之一', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/5afee9fa-4bf1-4000-a247-bec74c2c902c.png', 'Cover/5afee9fa-4bf1-4000-a247-bec74c2c902c.png', 'Books/14e62b53-82ba-44bb-bf69-611b21563eee.txt', '文学', 'literture', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
