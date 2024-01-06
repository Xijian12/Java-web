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

 Date: 06/01/2024 21:41:33
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '毛泽东选集', '毛泽东', 525252, '人民出版社', 9.9, 999, 10, '3146624068@qq.com', 99, '最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/a7bd7aa3-5406-48d2-a557-ab0376f05b5f.jpg', 'BookCover/a7bd7aa3-5406-48d2-a557-ab0376f05b5f.jpg', 'Books/24424c2a-12ea-42fd-b6ca-159f26a36d6d.txt', '文学', 'literture', NULL, NULL);
INSERT INTO `book` VALUES (2, 'Java编程思想', 'Bruce Eckel', 2, '机械工业出版社', 8.5, 1200, 1500, 'user1@example.com', 50, '深入浅出Java编程', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/efdc94c3-ba7d-406b-89fa-c074583babb3.png', 'BookCover/efdc94c3-ba7d-406b-89fa-c074583babb3.png', 'Books/39373567-c157-4e8e-abf2-6e5c02c12d71.txt', '技术', 'technology', NULL, NULL);
INSERT INTO `book` VALUES (3, 'Python核心编程', 'Wesley J. Chun', 3, '人民邮电出版社', 9, 800, 1000, 'user2@example.com', 60, '掌握Python核心技术', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/90dc0c9c-9584-4337-90fc-906f49e8bcd8.png', 'BookCover/90dc0c9c-9584-4337-90fc-906f49e8bcd8.png', 'Books/1ee379f4-2b03-44ac-a92f-18520ebd1585.txt', '技术', 'technology', NULL, NULL);
INSERT INTO `book` VALUES (4, '数据库系统概念', 'Abraham Silberschatz', 6, '机械工业出版社', 8.8, 600, 900, 'user3@example.com', 70, '数据库系统的经典教材', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/eaa5799b-7bf3-4d42-8c51-204be2429492.png', 'BookCover/eaa5799b-7bf3-4d42-8c51-204be2429492.png', 'Books/0aa1cbc9-92e1-463c-970d-f428645dea62.txt', '科学', 'science', NULL, NULL);
INSERT INTO `book` VALUES (5, '算法导论', 'Thomas H. Cormen', 4, '机械工业出版社', 9.5, 1500, 2000, 'user4@example.com', 80, '计算机算法领域的经典之作', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/b15f154e-9e19-4ffd-a769-c09b8534995f.png', 'BookCover/b15f154e-9e19-4ffd-a769-c09b8534995f.png', 'Books/02de7efc-2f0e-4206-b905-9f9b2ce87c31.txt', '算法', 'algorithm', NULL, NULL);
INSERT INTO `book` VALUES (6, '人类简史', '尤瓦尔·赫拉利', 5, '中信出版社', 9.2, 1000, 1200, 'user5@example.com', 90, '人类历史的综合性介绍', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/b9b5c4ed-4a8f-4b04-9e1f-640347d5ec22.png', 'BookCover/b9b5c4ed-4a8f-4b04-9e1f-640347d5ec22.png', 'Books/b046ded6-343d-4ef6-96e6-9882139575ff.txt', '历史', 'history', NULL, NULL);
INSERT INTO `book` VALUES (7, '西游记', '吴承恩', 10, '人民文学出版社', 9.7, 2000, 2500, 'user6@example.com', 100, '中国古代小说四大名著之一', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/a1377b92-cebf-4abe-88ce-3712b5db0042.png', 'BookCover/a1377b92-cebf-4abe-88ce-3712b5db0042.png', 'Books/0a36827b-8ca5-4762-a2cf-4172bbfcccef.txt', '文学', 'literture', NULL, NULL);
INSERT INTO `book` VALUES (8, '三体', '刘慈欣', 3, '重庆出版社', 9.8, 1800, 2100, 'user7@example.com', 95, '硬科幻巅峰之作', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/b18c2b4e-038c-48d5-9850-85cb6e5b7abc.png', 'BookCover/b18c2b4e-038c-48d5-9850-85cb6e5b7abc.png', 'Books/aaac7c58-6866-49c4-9577-788c50016e70.txt', '科幻', 'fiction', NULL, NULL);
INSERT INTO `book` VALUES (9, '霍乱时期的爱情', '加西亚·马尔克斯', 8, '南海出版公司', 9.6, 1300, 1600, 'user8@example.com', 85, '拉丁美洲文学巨匠的代表作', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/ead0aad6-4a42-4090-8e08-07fb0645a91c.png', 'BookCover/ead0aad6-4a42-4090-8e08-07fb0645a91c.png', 'Books/ffbb25ca-b1b8-4709-8c5e-bd1424ce2d72.txt', '爱情', 'love', NULL, NULL);
INSERT INTO `book` VALUES (10, '红楼梦', '曹雪芹', 15, '人民文学出版社', 9.9, 2500, 3000, 'user9@example.com', 110, '中国古典小说四大名著之一', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/214efbec-d7f9-423f-90dd-a2898bc5e1aa.png', 'BookCover/214efbec-d7f9-423f-90dd-a2898bc5e1aa.png', 'Books/14e62b53-82ba-44bb-bf69-611b21563eee.txt', '文学', 'literture', NULL, NULL);
INSERT INTO `book` VALUES (11, '数学之美', '吴军', 7, '人民邮电出版社', 8.9, 700, 800, 'user10@example.com', 75, '数学的思维与美感', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/BookCover/fd7ca852-4164-4321-b968-3b05bf4a0679.png', 'BookCover/fd7ca852-4164-4321-b968-3b05bf4a0679.png', 'Books/82777a51-6bdb-4437-843d-a7e91815ae9d.txt', '艺术', 'art', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
