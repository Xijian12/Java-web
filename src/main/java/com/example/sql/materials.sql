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

 Date: 10/01/2024 22:59:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for materials
-- ----------------------------
DROP TABLE IF EXISTS `materials`;
CREATE TABLE `materials`  (
  `material_id` int(0) NOT NULL AUTO_INCREMENT,
  `school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `material_grade` int(0) NOT NULL,
  `material_download_num` int(0) NULL DEFAULT 0,
  `material_click_num` int(0) NULL DEFAULT 0,
  `material_uploader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `material_profile` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `material_cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `material_cover_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `elec_book_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teaching_plan_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `class_ppt_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `calendar_volume_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `another_material_uuid` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `elec_book_points` int(0) NULL DEFAULT NULL,
  `teaching_plan_points` int(0) NULL DEFAULT NULL,
  `class_ppt_points` int(0) NULL DEFAULT NULL,
  `calendar_volume_points` int(0) NULL DEFAULT NULL,
  `another_material_points` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`material_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of materials
-- ----------------------------
INSERT INTO `materials` VALUES (1, 'School 1', 'Major 1', 'Subject 1', 1, 0, 0, 'Uploader 1', NULL, 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/8aad35c0-8ae7-452c-9b67-45cb43ab35ef.png', 'Cover/8aad35c0-8ae7-452c-9b67-45cb43ab35ef.png', 'Material/c147798d-942c-4875-b384-fba9594fad84.txt', 'Material/941676ae-4c3c-4ba0-9b89-66fca95050a8.txt', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (2, 'School 2', 'Major 2', 'Subject 2', 2, 0, 0, 'Uploader 2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (3, 'School 3', 'Major 3', 'Subject 3', 3, 0, 0, 'Uploader 3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (4, 'School 4', 'Major 4', 'Subject 4', 4, 0, 0, 'Uploader 4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (5, 'School 5', 'Major 5', 'Subject 5', 5, 0, 0, 'Uploader 5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (6, 'School 6', 'Major 6', 'Subject 6', 1, 0, 0, 'Uploader 6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (7, 'School 7', 'Major 7', 'Subject 7', 2, 0, 0, 'Uploader 7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (8, 'School 8', 'Major 8', 'Subject 8', 3, 0, 0, 'Uploader 8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (9, 'School 9', 'Major 9', 'Subject 9', 4, 0, 0, 'Uploader 9', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (10, 'School 10', 'Major 10', 'Subject 10', 5, 0, 0, 'Uploader 10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (11, 'School 11', 'Major 11', 'Subject 11', 1, 0, 0, 'Uploader 11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (12, 'School 12', 'Major 12', 'Subject 12', 2, 0, 0, 'Uploader 12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (13, 'School 13', 'Major 13', 'Subject 13', 3, 0, 0, 'Uploader 13', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (14, 'School 14', 'Major 14', 'Subject 14', 4, 0, 0, 'Uploader 14', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (15, 'School 15', 'Major 15', 'Subject 15', 5, 0, 0, 'Uploader 15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materials` VALUES (17, '上海最菜大学', '计算机', '算法设计', 0, 0, 0, '凹凸曼', '垃圾课程', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 6, 7, 5, 5, 3, '2024-01-10 14:48:17', '2024-01-10 14:48:17');
INSERT INTO `materials` VALUES (18, '上海最菜大学', '计算机', '算法设计', 0, 0, 0, '凹凸曼', '垃圾课程', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 6, 7, 5, 5, 3, '2024-01-10 15:15:39', '2024-01-10 15:15:39');
INSERT INTO `materials` VALUES (21, '北京最菜大学', '计算机', '算法设计', 0, 0, 0, '凹凸曼', '垃圾课程', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 6, 7, 5, 5, 3, '2024-01-10 17:53:01', '2024-01-10 17:53:01');
INSERT INTO `materials` VALUES (22, '北京最菜大学', '计算机', '算法设计', 0, 0, 0, '凹凸曼', '垃圾课程', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 6, 7, 5, 5, 3, '2024-01-10 17:53:02', '2024-01-10 17:53:02');
INSERT INTO `materials` VALUES (23, '北京最菜大学', '计算机', '算法设计', 0, 0, 0, '凹凸曼', '垃圾课程', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Cover/7226388f-2e29-4dca-be34-9c4011c142a3.jpg', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 'Material/7226388f-2e29-4dca-be34-9c4011c142a3.ZIP', 6, 7, 5, 5, 3, '2024-01-10 17:53:02', '2024-01-10 17:53:02');

SET FOREIGN_KEY_CHECKS = 1;
