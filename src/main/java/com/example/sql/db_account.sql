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

 Date: 10/01/2024 22:59:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_account
-- ----------------------------
DROP TABLE IF EXISTS `db_account`;
CREATE TABLE `db_account`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `register_time` datetime(0) NULL DEFAULT NULL,
  `points` int(0) NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_account
-- ----------------------------
INSERT INTO `db_account` VALUES (1, 'yhempr', 'yhempr@gmail.com', '$2a$10$zJLWCSvVbKTTt.UgkgklkeaJVnSQVHFj.XTRTVH3HhithpafAQ3/y', 'user', '2024-01-04 09:54:07', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (2, '马英九', '2323223@qq.com', '$2a$10$9C1sEUj30.Gky5YTg2NBp.GLemjnftCTu/aiR1YlyPwokLdDfI9yG', 'user', '2024-01-04 09:56:43', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (3, '马英华', '2031282938@qq.com', '$2a$10$oP9Hj6EWcYJ7e/U.I6Cd8O4hyAThis5mYCZaKTPd/.SYa7.u2c3L2', 'user', '2024-01-04 10:20:04', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (4, 'jocker', 'myhsnd666@gmail.com', '$2a$10$4v9twRnhoepwqlhW0.hDveVoh3W4FmQA.PDdWRuNwGs0quDoM62mG', 'user', '2024-01-04 13:40:49', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (5, '小丑', '851314610@qq.com', '$2a$10$Unl.fve4zSloPy6QC7rX5u1zyQO8Pb7JB2rxOHGXkf8aeux.n4m9C', 'user', '2024-01-04 18:18:49', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (6, '汐渐', 'hyg3146624068@163.com', '$2a$10$ATwuPi9AHAn7xSR7Nz.sqOoD6uk8Dyo/4Be3qcykyMkKz6BUiKG5S', 'admin', '2024-01-09 12:36:06', 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
