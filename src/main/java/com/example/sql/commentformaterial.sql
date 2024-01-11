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

 Date: 10/01/2024 22:59:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commentformaterial
-- ----------------------------
DROP TABLE IF EXISTS `commentformaterial`;
CREATE TABLE `commentformaterial`  (
  `comment_id` int(0) NOT NULL AUTO_INCREMENT,
  `school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `material_comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `material_grade` decimal(3, 1) NULL DEFAULT NULL,
  `user_nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` int(0) NULL DEFAULT 0,
  `user_id` int(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'user',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commentformaterial
-- ----------------------------
INSERT INTO `commentformaterial` VALUES (1, '上海最菜大学', '计算机', '算法设计', '真是垃圾课程', 0.1, '凹凸曼', 'hyg3146624068@163.com', 1, 6, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'admin');
INSERT INTO `commentformaterial` VALUES (2, '北京最强大学', '软件工程', '人工智能', '这门课程太有趣了', 4.5, '小明', 'hyg3146624068@163.com', 2, 6, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'admin');
INSERT INTO `commentformaterial` VALUES (3, '南京最牛大学', '电子商务', '市场营销', '无聊至极', 1.2, '张三', 'hyg3146624068@163.com', 0, 6, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'admin');
INSERT INTO `commentformaterial` VALUES (4, '广州最受欢迎大学', '通信工程', '网络技术', '很实用的课程', 3.8, '李四', 'hyg3146624068@163.com', 1, 6, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'admin');
INSERT INTO `commentformaterial` VALUES (5, '上海最佳大学', '计算机科学', '数据库', '非常实用的数据库课程', 4.8, '王七', 'hyg3146624068@163.com', 1, 6, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'admin');
INSERT INTO `commentformaterial` VALUES (6, '武汉最古老大学', '历史学', '中国古代史', '很有趣的历史学课程', 3.5, '杨八', 'yhempr@gmail.com', 2, 1, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (7, '重庆最具特色大学', '文学', '现代诗歌', '让人感慨良多的文学课', 4.2, '孙九', 'yhempr@gmail.com', 1, 1, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (8, '南宁最和谐大学', '心理学', '人格心理学', '非常有深度的心理学课', 4.5, '赵十', 'yhempr@gmail.com', 2, 1, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (9, '杭州最宜居大学', '环境科学', '生态学', '关注环境问题的重要课程', 3.8, '钱十一', 'yhempr@gmail.com', 1, 1, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (10, '哈尔滨最寒冷大学', '气象学', '气候变化', '研究气象学的好地方', 3.0, '孙十二', '851314610@qq.com', 2, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (11, '西安最有历史感大学', '考古学', '古代文明', '深度挖掘古代文明的好课', 4.5, '周十三', '851314610@qq.com', 1, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (12, '苏州最具园林特色大学', '园林学', '园林设计', '学习园林设计的理想地', 3.8, '吴十四', '851314610@qq.com', 2, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (13, '南昌最有活力大学', '体育学', '体育管理', '关注体育行业的好选择', 4.2, '郑十五', '851314610@qq.com', 1, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (14, '济南最山东大学', '地理信息科学', 'GIS技术', '地理信息科学的前沿课程', 4.5, '王十六', '851314610@qq.com', 2, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (15, '长沙最美风景大学', '旅游管理', '旅游规划', '规划旅游的重要环节', 3.8, '赵十七', '851314610@qq.com', 1, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (16, '天津最有创意大学', '设计学', '平面设计', '培养创意设计能力的专业', 4.0, '钱十八', '851314610@qq.com', 2, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (17, '成都最有活力大学', '生命科学', '遗传学', '研究生命科学的好地方', 4.3, '孙十九', '851314610@qq.com', 1, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (18, '深圳最繁荣大学', '经济学', '国际贸易', '学习国际贸易的理想学府', 3.9, '李二十', '851314610@qq.com', 2, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (19, '西宁最宜居大学', '环境工程', '环保科学', '深入学习环保科学的好地方', 4.6, '赵二十一', '851314610@qq.com', 1, 5, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (20, '南阳最有历史感大学', '考古学', '文物保护', '研究文物保护的好地方', 4.8, '王二十二', 'myhsnd666@gmail.com', 2, 4, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (21, '青岛最有创意大学', '艺术设计', '平面设计', '培养创意设计能力的专业', 4.2, '赵二十三', 'myhsnd666@gmail.com', 1, 4, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (22, '合肥最适合生活大学', '城市规划', '城市设计', '学习城市规划的理想学府', 3.5, '孙二十四', 'myhsnd666@gmail.com', 2, 4, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (23, '哈尔滨最有文化底蕴大学', '文学', '中国现当代文学', '深入研究中国现当代文学的好地方', 4.5, '钱二十五', 'myhsnd666@gmail.com', 1, 4, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (24, '昆明最美风景大学', '旅游管理', '自然风景摄影', '学习自然风景摄影的理想学府', 4.0, '周二十六', 'myhsnd666@gmail.com', 2, 4, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (25, '厦门最宜居大学', '土木工程', '建筑设计', '深入学习建筑设计的好地方', 3.7, '吴二十七', '2031282938@qq.com', 1, 3, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (26, '兰州最有特色大学', '民族学', '少数民族文化', '深入研究少数民族文化的好地方', 4.2, '郑二十八', '2031282938@qq.com', 2, 3, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (27, '宁波最繁荣大学', '工商管理', '市场营销', '深入学习市场营销的好地方', 3.9, '王二十九', '2031282938@qq.com', 1, 3, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (28, '太原最适合生活大学', '公共管理', '政府管理', '深入学习政府管理的好地方', 4.7, '赵三十', '2031282938@qq.com', 2, 3, '2024-01-10 19:09:44', '2024-01-10 19:09:44', 'user');
INSERT INTO `commentformaterial` VALUES (29, '北京最菜大学', '计算机', '算法设计', '这本书就是逊啦', 0.5, '凹凸曼', '2031282938@qq.com', 0, 3, '2024-01-10 22:06:08', '2024-01-10 22:06:08', 'admin');
INSERT INTO `commentformaterial` VALUES (33, '北京最菜大学', '计算机', '算法设计', 'nice资料', 0.5, '管理员', 'hyg3146624068@163.com', 0, 6, '2024-01-10 22:51:50', '2024-01-10 22:51:50', 'admin');
INSERT INTO `commentformaterial` VALUES (34, '北京最菜大学', '计算机', '算法设计', 'nice资料', 0.5, '管理员', 'hyg3146624068@163.com', 0, 6, '2024-01-10 22:51:50', '2024-01-10 22:51:50', 'admin');
INSERT INTO `commentformaterial` VALUES (35, '北京最菜大学', '计算机', '算法设计', 'nice资料', 0.5, '管理员', 'hyg3146624068@163.com', 0, 6, '2024-01-10 22:51:57', '2024-01-10 22:51:57', 'admin');
INSERT INTO `commentformaterial` VALUES (36, '北京最菜大学', '计算机', '算法设计', 'nice资料', 0.5, '管理员', 'hyg3146624068@163.com', 0, 6, '2024-01-10 22:52:01', '2024-01-10 22:52:01', 'admin');
INSERT INTO `commentformaterial` VALUES (37, '北京最菜大学', '计算机', '算法设计', 'nice资料', 0.5, '管理员', 'hyg3146624068@163.com', 0, 6, '2024-01-10 22:53:42', '2024-01-10 22:53:42', 'admin');
INSERT INTO `commentformaterial` VALUES (38, '上海最菜大学', '计算机', '算法设计', 'nice资料', 0.5, '管理员', 'hyg3146624068@163.com', 0, 6, '2024-01-10 22:55:39', '2024-01-10 22:55:39', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
