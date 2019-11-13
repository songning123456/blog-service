/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : simple_blog

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-11-07 11:28:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for label_config
-- ----------------------------
DROP TABLE IF EXISTS `label_config`;
CREATE TABLE `label_config` (
  `id` varchar(32) NOT NULL,
  `label_name` varchar(50) NOT NULL COMMENT '标签名',
  `label_photo` varchar(100) NOT NULL COMMENT '标签图片',
  PRIMARY KEY (`id`),
  UNIQUE KEY `label_name` (`label_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of label_config
-- ----------------------------
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b0c0f0000', '前端', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2d110001', '后端', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2d290002', 'JavaScript', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2d410003', 'GitHub', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2d520004', '架构', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2d6a0005', '代码规范', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2d7c0006', '面试', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2d950007', '算法', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2da60008', 'Android', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2dbd0009', 'CSS', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2dd1000a', '程序员', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2de8000b', 'Vue.js', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2df9000c', 'Java', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2e10000d', 'Node.js', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2e33000e', '数据库', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2e4b000f', '设计模式', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2e750010', '设计', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2e8d0011', '前端框架', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2ea70012', 'HTML', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2eca0013', '开源', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2ee40014', '产品', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2f000015', 'Linux', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2f1d0016', 'React.js', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2f2f0017', 'Git', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2f450018', 'Python', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2f6e0019', 'IOS', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2fe2001a', '人工智能', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b2fff001b', '微信小程序', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3016001c', 'Webpack', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3029001d', '全栈', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3041001e', '微信', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b305a001f', 'ECMAScript 6', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b30990020', 'MYSQL', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b30bf0021', 'HTTP', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b30d50022', 'Google', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b30e90023', '正则表达式', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b30fe0024', '机器学习', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31110025', '黑客', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31290026', 'Jquery', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b313b0027', '响应式设计', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31520028', 'APP', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31650029', '创业', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b317c002a', 'Chrome', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b318f002b', 'Nginx', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31a6002c', '编程语言', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31b9002d', '命令行', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31cf002e', 'Doctor', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31e3002f', 'Redis', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b31f90030', '产品经理', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b320c0031', 'Android Studio', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32240032', 'Angular.js', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32360033', 'Mac', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b324c0034', 'React Native', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32600035', 'BootStrap', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32760036', 'Apple', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32890037', '图片资源', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32a00038', 'Photoshop', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32b30039', 'PHP', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32c9003a', 'API', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32dc003b', 'Sublime Text', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b32f3003c', '设计师', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3306003d', '数据挖掘', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b331d003e', '操作系统', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3351003f', '阿里巴巴', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b33680040', 'MongoDB', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b33840041', 'gradle', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b339a0042', 'Material Design', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b33ad0043', '数据可视化', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b33c40044', '安全', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b33d70045', '招聘', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b33ed0046', 'Go', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b34030047', 'Swift', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b34230048', 'Vuex', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b343d0049', 'MVVM', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b347a004a', 'RxJava', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3494004b', 'Xcode', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b34b8004c', '敏捷开发', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3526004d', '运维', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b355e004e', 'Markdown', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b35b6004f', '动效', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b35d00050', '字体', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b35f10051', '腾讯', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b360b0052', 'Objective-C', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b361e0053', '云计算', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36350054', 'Spring', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36490055', '运营', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36600056', '物联网', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36720057', 'Canvas', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36880058', '深度学习', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b369c0059', 'Icon', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36b2005a', '爬虫', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36c5005b', 'C++', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36db005c', '虚拟现实', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b36f2005d', 'HTTPS', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b370d005e', 'Debug', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3721005f', 'Eclipse', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b37580060', '电子书', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b37710061', 'Ubuntu', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b37850062', 'Sketch', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b379b0063', '翻译', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b37bb0064', 'NPM', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b37d60065', '微服务', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b37ee0066', 'JSON', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b38210067', '测试', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b38390068', '配色', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b384a0069', 'Ajax', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3862006a', 'DOM', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3873006b', 'C', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b38a5006c', '源码', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b38b7006d', 'Facebook', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b38cf006e', 'VIM', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b38e9006f', 'SCSS', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b39010070', '稀土', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b39120071', 'TypeScript', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b392a0072', 'Apache', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b393d0073', '游戏', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b39540074', 'Redux', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b39660075', 'maven', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b397e0076', 'Kotlin', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b39900077', 'Visual Studio Code', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b39a80078', '负载均衡', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3a250079', 'SVG', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3a57007a', 'Windows', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3a88007b', 'SEC', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3a9b007c', '区域链', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3ab3007d', '支付宝', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3acc007e', '函数式编程', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3ae6007f', 'Gulp', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3b000080', '增强现实', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3b410081', 'Microsoft', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3b590082', 'SQLite', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3b730083', 'Flutter', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3b8a0084', '浏览器', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3ba40085', 'Express', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3bd60086', 'Unity 3D', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3bf10087', 'SQL', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3c080088', '远程工作', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3c220089', 'Firefox', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3c3a008a', 'APK', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3c54008b', 'Atom', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3c85008c', 'Promise', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3ca0008d', 'Webkit', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3cb7008e', 'IntelliJ IDEA', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3cd0008f', 'Hadoop', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3d170090', 'Spring boot', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3d350091', '嵌入式', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3d4d0092', 'JVM', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3d670093', '机器人', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3d7f0094', '编译器', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3d990095', '神经网络', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3db10096', '响应式编程', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3dcb0097', '投资', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3de40098', 'Django', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3dfe0099', '科幻', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3e15009a', '百度', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3e2f009b', '比特币', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3e47009c', '单元测试', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3e61009d', 'flexbox', 'static/labelImg/noPicture.svg');
INSERT INTO `label_config` VALUES ('40281a816dab2aba016dab2b3e79009e', 'Java EE', 'static/labelImg/noPicture.svg');