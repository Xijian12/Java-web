/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 10/01/2024 19:57:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_account
-- ----------------------------
DROP TABLE IF EXISTS `db_account`;
CREATE TABLE `db_account`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `register_time` datetime NULL DEFAULT NULL,
  `points` int NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `unique_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_account
-- ----------------------------
INSERT INTO `db_account` VALUES (8, 'yhempr', '3838383@gmail.com', '$2a$10$zJLWCSvVbKTTt.UgkgklkeaJVnSQVHFj.XTRTVH3HhithpafAQ3/y', 'user', '2024-01-04 09:54:07', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (9, '原神', '2323223@qq.com', '$2a$10$t4owKlGxgRfAHfxU7rtxBe1fOkYKJdzJLRqakG95emzdDYC397Y1u', 'user', '2024-01-04 09:56:43', 50, 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Avatar/66c714f0-cec9-4598-b1c5-f390d41c3f9a.png', 'Avatar/66c714f0-cec9-4598-b1c5-f390d41c3f9a.png');
INSERT INTO `db_account` VALUES (10, '启动', '11111@qq.com', '$2a$10$cbYvZdh0Cy6WWoAbww9mXeUPSMLlDRMQM3olkQgLDe8MYzZbJdz0W', 'user', '2024-01-04 10:20:04', 100, 'https://ys.mihoyo.com/', '21');
INSERT INTO `db_account` VALUES (11, 'jocker', '2222222@gmail.com', '$2a$10$4v9twRnhoepwqlhW0.hDveVoh3W4FmQA.PDdWRuNwGs0quDoM62mG', 'user', '2024-01-04 13:40:49', 5, 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Avatar/02bab06c-88e6-4dc7-acb5-b74642b6b553.png', 'Avatar/02bab06c-88e6-4dc7-acb5-b74642b6b553.png');
INSERT INTO `db_account` VALUES (12, '小丑', '99999@qq.com', '$2a$10$Unl.fve4zSloPy6QC7rX5u1zyQO8Pb7JB2rxOHGXkf8aeux.n4m9C', 'user', '2024-01-04 18:18:49', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (13, '邱逸轩', '2498118731@qq.com', '$2a$10$gmnK7gH63SCEUAWGlVZvk.QBakGUN7OhE2mvU048yZmcniMBuvvjK', 'user', '2024-01-06 23:27:46', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (14, '樱花', 'yyyy@gmail.com', '$2a$10$vhu6ZMAY76CNIp/LMaJwf.yRPuKalJy/od0lA7PhVWJ/Wd7H9AI.G', 'user', '2024-01-09 12:15:22', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (15, '张三', 'yhempr@gmail.com', '$2a$10$XFC/p8B4MNkUwOTN/TFDHuuYXlJFO7SoIgh3ZaDXBYgNGoKG0DSNS', 'user', '2024-01-10 17:56:27', 5, NULL, NULL);
INSERT INTO `db_account` VALUES (16, '李四', '851314610@qq.com', '$2a$10$iZzxY5UjwZ3wZoIPawIbR.zAswLyfo7rtiwEn38buIPKplRN7vPWe', 'user', '2024-01-10 18:32:37', 5, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
