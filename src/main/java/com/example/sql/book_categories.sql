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

 Date: 14/01/2024 15:48:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_categories
-- ----------------------------
DROP TABLE IF EXISTS `book_categories`;
CREATE TABLE `book_categories`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_click_num` int(0) NULL DEFAULT 0,
  `category_download_num` int(0) NULL DEFAULT 0,
  `category_alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_categories
-- ----------------------------
INSERT INTO `book_categories` VALUES (1, '科幻', 0, 0, 'SicenceFiction');
INSERT INTO `book_categories` VALUES (2, '文学', 0, 0, 'Literature');
INSERT INTO `book_categories` VALUES (3, '历史', 0, 0, 'History');
INSERT INTO `book_categories` VALUES (4, '科技', 0, 0, 'Technology');
INSERT INTO `book_categories` VALUES (5, '童话', 0, 0, 'Fairy');
INSERT INTO `book_categories` VALUES (6, '风土人情', 0, 0, 'LocalCondition');
INSERT INTO `book_categories` VALUES (7, '玄学', 0, 0, 'Metaphysics');
INSERT INTO `book_categories` VALUES (8, '人文', 0, 0, 'humanity');
INSERT INTO `book_categories` VALUES (9, '爱情', 0, 0, 'Love');
INSERT INTO `book_categories` VALUES (10, '悬疑', 0, 0, 'Suspense');
INSERT INTO `book_categories` VALUES (11, '教育', 0, 0, 'Education');

SET FOREIGN_KEY_CHECKS = 1;
