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

 Date: 14/01/2024 16:03:12
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
  `book_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
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
  UNIQUE INDEX `book_file_uuid`(`book_file_uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '毛泽东选集全四本', '毛泽东', '978-7-5555-4848-8', '人民出版社', 9, 100, 606, 'hyg3146624068@163.com', 188, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/b22d9b63-0344-412b-a4ac-5b17a0f98f95.png', 'Cover/b22d9b63-0344-412b-a4ac-5b17a0f98f95.png', 'Books/c86b9d57-5fda-4032-b608-2ef918af8cd5.pdf', '文学', 'Literature', '2024-01-14 14:35:12', '2024-01-14 14:35:12');
INSERT INTO `book` VALUES (2, '毛泽东文集第一卷', '毛泽东', '978-7-6666-4848-1', '人民出版社', 10, 100, 505, 'hyg3146624068@163.com', 88, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/24df4e60-0592-43e5-a3ef-c4defc333061.jpg', 'Cover/24df4e60-0592-43e5-a3ef-c4defc333061.jpg', 'Books/accfb660-9335-4def-abba-9b4abab722c3.pdf', '文学', 'Literature', '2024-01-14 14:37:14', '2024-01-14 14:37:14');
INSERT INTO `book` VALUES (3, '毛泽东文集第二卷', '毛泽东', '978-7-6666-4848-2', '人民出版社', 10, 100, 5040, 'hyg3146624068@163.com', 88, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/54421beb-74b1-44f1-9eb3-7475479e5787.jpg', 'Cover/54421beb-74b1-44f1-9eb3-7475479e5787.jpg', 'Books/4e276576-c8ff-4135-92c7-87043bd5b579.pdf', '文学', 'Literature', '2024-01-14 14:37:45', '2024-01-14 14:37:45');
INSERT INTO `book` VALUES (4, '毛泽东文集第三卷', '毛泽东', '978-7-6666-4848-3', '人民出版社', 10, 100, 407, 'hyg3146624068@163.com', 88, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/7888898a-e895-430b-be15-07ebb5278445.jpg', 'Cover/7888898a-e895-430b-be15-07ebb5278445.jpg', 'Books/42c5cc9e-48ae-4e86-9bc4-02d9243ad8f0.pdf', '文学', 'Literature', '2024-01-14 14:37:54', '2024-01-14 14:37:54');
INSERT INTO `book` VALUES (5, '毛泽东文集第四卷', '毛泽东', '978-7-6666-4848-4', '人民出版社', 10, 100, 7864, 'hyg3146624068@163.com', 88, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/91a4faa7-f357-47d8-ad7c-896a62475b8f.jpg', 'Cover/91a4faa7-f357-47d8-ad7c-896a62475b8f.jpg', 'Books/6b760048-b2ca-4dd8-9747-45ff908cc5c0.pdf', '文学', 'Literature', '2024-01-14 14:38:07', '2024-01-14 14:38:07');
INSERT INTO `book` VALUES (6, '毛泽东文集第五卷', '毛泽东', '978-7-6666-4848-5', '人民出版社', 10, 100, 6482, 'hyg3146624068@163.com', 88, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/4b28ed85-c777-471c-a621-3bb7863c78d5.jpg', 'Cover/4b28ed85-c777-471c-a621-3bb7863c78d5.jpg', 'Books/2573a378-6786-4680-a576-0c440a8da484.pdf', '文学', 'Literature', '2024-01-14 14:38:13', '2024-01-14 14:38:13');
INSERT INTO `book` VALUES (7, '毛泽东文集第六卷', '毛泽东', '978-7-6666-4848-6', '人民出版社', 10, 100, 4646, 'hyg3146624068@163.com', 88, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/cf64735b-12c2-457b-91ab-ba8751e7662d.jpg', 'Cover/cf64735b-12c2-457b-91ab-ba8751e7662d.jpg', 'Books/47bb61ac-0128-4e0c-8d6d-2e9507da5fd3.pdf', '文学', 'Literature', '2024-01-14 14:38:20', '2024-01-14 14:38:20');
INSERT INTO `book` VALUES (8, '毛泽东文集第七卷', '毛泽东', '978-7-6666-4848-7', '人民出版社', 10, 100, 7676, 'hyg3146624068@163.com', 88, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/293a21de-495a-4ba4-8f19-a2ebc49a14d8.jpg', 'Cover/293a21de-495a-4ba4-8f19-a2ebc49a14d8.jpg', 'Books/bb1d1a57-50b1-400d-8d88-480c39b9fbef.pdf', '文学', 'Literature', '2024-01-14 14:38:27', '2024-01-14 14:38:27');
INSERT INTO `book` VALUES (9, '毛泽东文集第八卷', '毛泽东', '978-7-6666-4848-8', '人民出版社', 10, 100, 46645, 'hyg3146624068@163.com', 88, '最伟大的人，最伟大的思想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/5be8eddf-0175-4a6f-bfda-8f54d646dfd0.jpg', 'Cover/5be8eddf-0175-4a6f-bfda-8f54d646dfd0.jpg', 'Books/3779c91d-c63d-42c5-9130-97fa552e27be.pdf', '文学', 'Literature', '2024-01-14 14:38:35', '2024-01-14 14:38:35');
INSERT INTO `book` VALUES (10, '带上她的眼睛', '刘慈欣', '978-7-6666-4848-8', '人民出版社', 10, 100, 676, 'hyg3146624068@163.com', 65, '感人肺腑的爱情故事', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/b59b0200-eccf-4d95-a211-f628e0a11cbb.jpg', 'Cover/b59b0200-eccf-4d95-a211-f628e0a11cbb.jpg', 'Books/e7c0794e-8d95-44ef-834e-2202af0c22e0.pdf', '爱情', 'Love', '2024-01-14 14:40:07', '2024-01-14 14:40:07');
INSERT INTO `book` VALUES (11, '球状闪电', '刘慈欣', '978-7-5217-4157-5', '重庆出版社', 9.8, 100, 46464, 'hyg3146624068@163.com', 61, '令人深思的悬疑、科幻故事', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/53ed0d0e-0b4d-4251-a82b-93bbef7213b5.jpg', 'Cover/53ed0d0e-0b4d-4251-a82b-93bbef7213b5.jpg', 'Books/3c14fcc3-dec9-4713-9af1-38aae42d8600.pdf', '悬疑', 'Suspense', '2024-01-14 14:46:02', '2024-01-14 14:46:02');
INSERT INTO `book` VALUES (12, '三体1', '刘慈欣', '978-7-5598-4846-1', '重庆出版社', 9.7, 100, 464, 'hyg3146624068@163.com', 99, '科幻星云奖，最为宏大的科幻史诗', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/5445a2ac-7259-480f-8bd0-6935ff022fdf.jpg', 'Cover/5445a2ac-7259-480f-8bd0-6935ff022fdf.jpg', 'Books/94df9f07-8f23-4c76-b976-3a4cae9b8414.pdf', '科幻', 'SicenceFiction', '2024-01-14 14:47:07', '2024-01-14 14:47:07');
INSERT INTO `book` VALUES (13, '三体2', '刘慈欣', '978-7-5598-4846-2', '重庆出版社', 9.6, 100, 3484, 'hyg3146624068@163.com', 99, '科幻星云奖，最为宏大的科幻史诗', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/07bdfd28-bcc9-4374-99a0-b40716290135.jpg', 'Cover/07bdfd28-bcc9-4374-99a0-b40716290135.jpg', 'Books/b8fee40d-bd94-413c-b2ef-2fcd5fce0e42.pdf', '科幻', 'SicenceFiction', '2024-01-14 14:47:14', '2024-01-14 14:47:14');
INSERT INTO `book` VALUES (14, '三体3', '刘慈欣', '978-7-5598-4846-3', '重庆出版社', 9.9, 100, 34243, 'hyg3146624068@163.com', 99, '科幻星云奖，最为宏大的科幻史诗', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/83c40ca7-512d-41b4-a0aa-a410471a8f37.jpg', 'Cover/83c40ca7-512d-41b4-a0aa-a410471a8f37.jpg', 'Books/65575630-2e99-444b-97da-d28ea82d872d.pdf', '科幻', 'SicenceFiction', '2024-01-14 14:47:21', '2024-01-14 14:47:21');
INSERT INTO `book` VALUES (15, '乡村教师', '刘慈欣', '978-7-5598-4846-5', '重庆出版社', 9.1, 100, 423, 'hyg3146624068@163.com', 23, '让人深思的科幻教育', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/808f7491-e6a3-4043-837a-c49ab726f154.jpg', 'Cover/808f7491-e6a3-4043-837a-c49ab726f154.jpg', 'Books/4a24c972-7a1c-4ae9-8cd3-5bc1d8cabb79.pdf', '教育', 'Education', '2024-01-14 14:48:50', '2024-01-14 14:48:50');
INSERT INTO `book` VALUES (16, '超新星纪元', '刘慈欣', '978-7-5598-4846-6', '重庆出版社', 8.7, 100, 43, 'hyg3146624068@163.com', 25, '让人深思的儿童幻想', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/eb0263e3-7020-4415-b078-5a95c549340f.jpg', 'Cover/eb0263e3-7020-4415-b078-5a95c549340f.jpg', 'Books/4426ee27-034b-4989-8d24-8c474c706b94.pdf', '教育', 'Education', '2024-01-14 14:49:19', '2024-01-14 14:49:19');
INSERT INTO `book` VALUES (17, '流浪地球', '刘慈欣', '978-7-5598-4846-7', '重庆出版社', 8.8, 100, 1313, 'hyg3146624068@163.com', 66, '独属于中国人的科幻，展现中国人对大自然母亲的敬畏', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/49e217eb-96d5-4308-af02-7cf672355f16.jpg', 'Cover/49e217eb-96d5-4308-af02-7cf672355f16.jpg', 'Books/13654edb-ef0d-4a00-99e3-c1b118f930dc.pdf', '科幻', 'SicenceFiction', '2024-01-14 14:50:05', '2024-01-14 14:50:05');
INSERT INTO `book` VALUES (18, '微纪元', '刘慈欣', '978-7-5598-4846-8', '重庆出版社', 9.8, 100, 500, 'hyg3146624068@163.com', 86, '作为2018年高考题的科幻小说', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/29698991-1932-4f47-8686-1033c66f9cb1.jpg', 'Cover/29698991-1932-4f47-8686-1033c66f9cb1.jpg', 'Books/1c9b4ade-2773-4a22-9108-af16f3af702f.pdf', '科幻', 'SicenceFiction', '2024-01-14 14:50:37', '2024-01-14 14:50:37');
INSERT INTO `book` VALUES (19, '共产党宣言', '马克思、恩格斯', '978-7-5598-4846-1', '人民出版社', 10, 100, 3877887, 'hyg3146624068@163.com', 0, '全世界的无产阶级者，联合起来！', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/8797d024-969e-4979-b635-c388b20966ba.jpg', 'Cover/8797d024-969e-4979-b635-c388b20966ba.jpg', 'Books/0666f4ab-4bbd-4a97-b8be-aad6e1a8edeb.pdf', '文学', 'Literature', '2024-01-14 14:51:39', '2024-01-14 14:51:39');
INSERT INTO `book` VALUES (20, '万历十五年', '(美)黄仁宣', '978-7-5598-4846-2', '中华书局', 5.6, 100, 3384, 'hyg3146624068@163.com', 46, '万历年间的历史故事', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/27197a75-e8ea-4246-98a7-548958860479.png', 'Cover/27197a75-e8ea-4246-98a7-548958860479.png', 'Books/047adc29-7055-4bc8-8a79-a09e1989efbd.epub', '历史', 'History', '2024-01-14 14:53:32', '2024-01-14 14:53:32');
INSERT INTO `book` VALUES (21, '学习高手', '《学习高手》出版组', '978-7-5598-4846-3', '北京师范大学出版社', 6.5, 100, 500, 'hyg3146624068@163.com', 64, '教会小孩子如何成为学习高手', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/defaultCover.png', 'defalutCover', 'Books/73c7eb3d-b3da-4b64-bf39-b61f1743f704.epub', '教育', 'Education', '2024-01-14 14:54:44', '2024-01-14 14:54:44');
INSERT INTO `book` VALUES (22, '极简学习法', '廖恒', '978-7-5598-4846-4', '北京联合出版公司', 6.8, 100, 500, 'hyg3146624068@163.com', 45, '教会你如何让学习变得简单', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/defaultCover.png', 'defalutCover', 'Books/e4e3d571-eee2-4cda-8b8b-ee39d9e94475.epub', '教育', 'Education', '2024-01-14 14:55:58', '2024-01-14 14:55:58');
INSERT INTO `book` VALUES (23, '费曼学习法', '刘思宁', '978-7-5598-4846-5', '延边大学出版社', 5.6, 100, 500, 'hyg3146624068@163.com', 11, '鼎鼎大名的费曼学习法', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/defaultCover.png', 'defalutCover', 'Books/d788dcb2-0ea1-41d8-a95f-dab1509ce732.epub', '教育', 'Education', '2024-01-14 14:56:42', '2024-01-14 14:56:42');
INSERT INTO `book` VALUES (24, '成为超级学习者', '(美)彼得.霍林斯', '978-7-5598-4846-6', '北京联合出版公司', 8.8, 100, 500, 'hyg3146624068@163.com', 45, '教你如何成为成为超级学习者', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/defaultCover.png', 'defalutCover', 'Books/798c0b2f-1e9b-49c5-ba17-0ff73d9e8e9a.epub', '教育', 'Education', '2024-01-14 14:57:45', '2024-01-14 14:57:45');
INSERT INTO `book` VALUES (25, '明朝那些事儿', '当年明月', '978-7-5598-4846-7', '北京联合出版公司', 9.8, 100, 500, 'hyg3146624068@163.com', 84, '独属于明朝的浪漫史诗', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/2254a36e-cbea-4137-8e0b-9adb422935d9.png', 'Cover/2254a36e-cbea-4137-8e0b-9adb422935d9.png', 'Books/fb3afea5-a8b7-44f6-aa0c-b25e70bb3891.epub', '历史', 'History', '2024-01-14 14:58:26', '2024-01-14 14:58:26');
INSERT INTO `book` VALUES (26, '大明王朝', '朱颜', '978-7-5598-4846-8', '中国戏剧出版社', 9.6, 100, 500, 'hyg3146624068@163.com', 47, '独属于明朝那波澜壮阔的诗篇', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/02c11817-ba24-48d0-a56d-e9109aa7ad3e.png', 'Cover/02c11817-ba24-48d0-a56d-e9109aa7ad3e.png', 'Books/f149f795-6eaf-4ef6-ab61-7fad6c49754f.pdf', '历史', 'History', '2024-01-14 15:00:02', '2024-01-14 15:00:02');
INSERT INTO `book` VALUES (27, '季羡林图文版精选散文集（套装5册）', '季羡林', '978-7-5598-4846-9', '中国少儿出版社', 9.8, 489, 711, 'hyg3146624068@163.com', 45, '适合青年人读的散文集', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/e7fa0721-9a41-4a45-8b06-a6f651d07803.png', 'Cover/e7fa0721-9a41-4a45-8b06-a6f651d07803.png', 'Books/9595ec6f-dcff-4db7-846e-e010358059bd.pdf', '教育', 'Education', '2024-01-14 15:00:02', '2024-01-14 15:00:02');
INSERT INTO `book` VALUES (28, '写给年轻人的生命礼物', '季羡林', '978-7-5598-4846-10', '中国少儿出版社', 8.8, 99, 625, 'hyg3146624068@163.com', 14, '适合青年人读的文学作品', 'https://xiao-shu-shu.oss-cn-shanghai.aliyuncs.com/Cover/80906374-b998-4fff-a686-235a91edb0ac.png', 'Cover/80906374-b998-4fff-a686-235a91edb0ac.png', 'Books/9bad6dd1-cf87-4075-9c87-0a576b7c5d34.pdf', '教育', 'Education', '2024-01-14 15:00:02', '2024-01-14 15:00:02');

SET FOREIGN_KEY_CHECKS = 1;
