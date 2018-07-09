/*
Navicat MySQL Data Transfer

Source Server         : 114.115.179.255
Source Server Version : 50528
Source Host           : 114.115.179.255:3306
Source Database       : jack_db

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-07-09 13:30:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_clock_in_info
-- ----------------------------
DROP TABLE IF EXISTS `app_clock_in_info`;
CREATE TABLE `app_clock_in_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a_accuracy` varchar(255) DEFAULT NULL,
  `a_addresses` varchar(255) DEFAULT NULL,
  `a_altitude` varchar(255) DEFAULT NULL,
  `a_city` varchar(255) DEFAULT NULL,
  `a_coords_type` varchar(255) DEFAULT NULL,
  `a_country` varchar(255) DEFAULT NULL,
  `a_create_time` datetime DEFAULT NULL,
  `a_district` varchar(255) DEFAULT NULL,
  `a_ip` varchar(255) DEFAULT NULL,
  `a_latitude` varchar(255) DEFAULT NULL,
  `a_longitude` varchar(255) DEFAULT NULL,
  `a_province` varchar(255) DEFAULT NULL,
  `a_re_id` int(11) DEFAULT NULL,
  `a_status` int(11) DEFAULT NULL,
  `a_street` varchar(255) DEFAULT NULL,
  `a_street_num` varchar(255) DEFAULT NULL,
  `a_timestamp` varchar(255) DEFAULT NULL,
  `a_type` int(11) DEFAULT NULL,
  `a_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_clock_in_info
-- ----------------------------
INSERT INTO `app_clock_in_info` VALUES ('1', '65', '剑南大道中段699-附302号', '487.2041931152344', '成都市', 'wgs84', '中国', '2018-07-07 13:00:17', '武侯区', '192.168.2.211', '30.56015163758683', '104.0444498264034', '四川省', null, '0', '剑南大道中段', '699-附302号', '1530939617552', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('2', '65', '剑南大道中段599号', '483.0592651367188', '成都市', 'wgs84', '中国', '2018-07-07 13:10:37', '武侯区', '192.168.2.211', '30.56094438325697', '104.0440329343605', '四川省', null, '0', '剑南大道中段', '599号', '1530940238388', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('3', '65', '盛安街433号', '482.7144470214844', '成都市', 'wgs84', '中国', '2018-07-07 13:11:03', '武侯区', '192.168.2.211', '30.56093198733199', '104.0439446560718', '四川省', null, '0', '盛安街', '433号', '1530940264077', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('4', '65', '盛安街433号', '485.7693786621094', '成都市', 'wgs84', '中国', '2018-07-07 13:42:43', '武侯区', '192.168.2.211', '30.56061094562542', '104.0442564880407', '四川省', null, '0', '盛安街', '433号', '1530942164064', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('5', '70.04572226297564', '剑南大道中段599号', '486.1573486328125', '成都市', 'wgs84', '中国', '2018-07-07 14:08:34', '武侯区', '192.168.2.211', '30.56097435206212', '104.0440465373667', '四川省', null, '0', '剑南大道中段', '599号', '1530943715519', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('6', '70.04572226297564', '剑南大道中段599号', '486.1573486328125', '成都市', 'wgs84', '中国', '2018-07-07 14:09:01', '武侯区', '192.168.2.211', '30.56097435206212', '104.0440465373667', '四川省', null, '0', '剑南大道中段', '599号', '1530943742127', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('7', '65', '盛安街', '482.9490661621094', '成都市', 'wgs84', '中国', '2018-07-07 14:16:27', '武侯区', '192.168.2.211', '30.56081408690899', '104.0444854786214', '四川省', null, '0', '盛安街', 'undefined', '1530944188194', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('8', '66.62459278213386', '剑南大道中段599号', '484.3519287109375', '成都市', 'wgs84', '中国', '2018-07-07 14:25:04', '武侯区', '192.168.2.211', '30.56166870317085', '104.0438453565122', '四川省', '9', '1', '剑南大道中段', '599号', '1530944705232', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('9', '67.8967049400058', '剑南大道中段599号', '484.2658386230469', '成都市', 'wgs84', '中国', '2018-07-07 14:32:17', '武侯区', '192.168.2.211', '30.56164736406399', '104.0437232203301', '四川省', '10', '1', '剑南大道中段', '599号', '1530945137792', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('10', '68.85711034425682', '剑南大道中段599号', '484.836181640625', '成都市', 'wgs84', '中国', '2018-07-07 14:32:31', '武侯区', '192.168.2.211', '30.56118839708811', '104.0439173067539', '四川省', '11', '1', '剑南大道中段', '599号', '1530945152759', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('11', '65', '盛安街433-附202号', '484.9904174804688', '成都市', 'wgs84', '中国', '2018-07-07 14:33:04', '武侯区', '192.168.2.211', '30.56076029710379', '104.044367039493', '四川省', null, '0', '盛安街', '433-附202号', '1530945185424', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('12', '67.18809368966488', '剑南大道中段599号', '484.3468627929688', '成都市', 'wgs84', '中国', '2018-07-07 14:35:13', '武侯区', '192.168.2.211', '30.56108430931019', '104.043970604199', '四川省', null, '0', '剑南大道中段', '599号', '1530945314392', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('13', '67.18809368966488', '剑南大道中段599号', '484.3468627929688', '成都市', 'wgs84', '中国', '2018-07-07 14:36:14', '武侯区', '192.168.2.211', '30.56108430931019', '104.043970604199', '四川省', null, '0', '剑南大道中段', '599号', '1530945375593', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('14', '67.18809368966488', '剑南大道中段599号', '484.3468627929688', '成都市', 'wgs84', '中国', '2018-07-07 14:46:43', '武侯区', '192.168.2.211', '30.56108430931019', '104.043970604199', '四川省', '15', '1', '剑南大道中段', '599号', '1530946004042', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('15', '75.52599440122509', '剑南大道中段599号', '487.5852661132812', '成都市', 'wgs84', '中国', '2018-07-07 14:51:23', '武侯区', '192.168.2.211', '30.56094526907755', '104.0440818643121', '四川省', null, '0', '剑南大道中段', '599号', '1530946283843', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('16', '74.2831784471131', '剑南大道中段599号', '487.2568359375', '成都市', 'wgs84', '中国', '2018-07-07 14:57:43', '武侯区', '192.168.2.211', '30.56149932340346', '104.0438544180614', '四川省', '17', '1', '剑南大道中段', '599号', '1530946664012', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('17', '74.2831784471131', '剑南大道中段599号', '487.2568359375', '成都市', 'wgs84', '中国', '2018-07-07 14:57:49', '武侯区', '192.168.2.211', '30.56149932340346', '104.0438544180614', '四川省', null, '0', '剑南大道中段', '599号', '1530946670473', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('18', '74.2831784471131', '剑南大道中段599号', '487.2568359375', '成都市', 'wgs84', '中国', '2018-07-07 14:57:54', '武侯区', '192.168.2.211', '30.56149932340346', '104.0438544180614', '四川省', null, '0', '剑南大道中段', '599号', '1530946675251', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('19', '74.2831784471131', '剑南大道中段599号', '487.2568359375', '成都市', 'wgs84', '中国', '2018-07-07 14:57:57', '武侯区', '192.168.2.211', '30.56149932340346', '104.0438544180614', '四川省', null, '0', '剑南大道中段', '599号', '1530946678910', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('20', '74.2831784471131', '剑南大道中段599号', '487.2568359375', '成都市', 'wgs84', '中国', '2018-07-07 14:58:00', '武侯区', '192.168.2.211', '30.56149932340346', '104.0438544180614', '四川省', null, '0', '剑南大道中段', '599号', '1530946681548', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('21', '65', '盛安街433号', '486.8874816894531', '成都市', 'wgs84', '中国', '2018-07-07 14:58:03', '武侯区', '192.168.2.211', '30.56067953970153', '104.0442282068661', '四川省', null, '0', '盛安街', '433号', '1530946684891', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('22', '65', '盛安街433-附202号', '485.7278137207031', '成都市', 'wgs84', '中国', '2018-07-07 14:58:19', '武侯区', '192.168.2.211', '30.56066674273988', '104.0443752526829', '四川省', '23', '1', '盛安街', '433-附202号', '1530946700779', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('23', '65', '盛安街433-附202号', '484.67822265625', '成都市', 'wgs84', '中国', '2018-07-07 14:58:30', '武侯区', '192.168.2.211', '30.56074058578629', '104.0443792817478', '四川省', '24', '1', '盛安街', '433-附202号', '1530946711176', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('24', '65', '盛安街433-附202号', '484.7113037109375', '成都市', 'wgs84', '中国', '2018-07-07 14:58:36', '武侯区', '192.168.2.211', '30.56065991225999', '104.0444247639771', '四川省', null, '0', '盛安街', '433-附202号', '1530946716971', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('25', '69.83700718050808', '盛安街', '484.7488708496094', '成都市', 'wgs84', '中国', '2018-07-07 15:00:37', '武侯区', '192.168.2.211', '30.56083696942529', '104.0445024351646', '四川省', null, '0', '盛安街', 'undefined', '1530946838684', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('26', '69.83700718050808', '盛安街', '484.7488708496094', '成都市', 'wgs84', '中国', '2018-07-07 15:00:40', '武侯区', '192.168.2.211', '30.56083696942529', '104.0445024351646', '四川省', null, '0', '盛安街', 'undefined', '1530946841748', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('27', '69.83700718050808', '盛安街', '484.7488708496094', '成都市', 'wgs84', '中国', '2018-07-07 15:00:43', '武侯区', '192.168.2.211', '30.56083696942529', '104.0445024351646', '四川省', '28', '1', '盛安街', 'undefined', '1530946844293', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('28', '69.83700718050808', '盛安街', '484.7488708496094', '成都市', 'wgs84', '中国', '2018-07-07 15:00:48', '武侯区', '192.168.2.211', '30.56083696942529', '104.0445024351646', '四川省', null, '0', '盛安街', 'undefined', '1530946849107', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('29', '69.83700718050808', '盛安街', '484.7488708496094', '成都市', 'wgs84', '中国', '2018-07-07 15:01:09', '武侯区', '192.168.2.211', '30.56083696942529', '104.0445024351646', '四川省', null, '0', '盛安街', 'undefined', '1530946869967', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('30', '69.83700718050808', '盛安街', '484.7488708496094', '成都市', 'wgs84', '中国', '2018-07-07 15:01:11', '武侯区', '192.168.2.211', '30.56083696942529', '104.0445024351646', '四川省', null, '0', '盛安街', 'undefined', '1530946872709', '2', '1');
INSERT INTO `app_clock_in_info` VALUES ('31', '69.83700718050808', '盛安街', '484.7488708496094', '成都市', 'wgs84', '中国', '2018-07-07 15:01:14', '武侯区', '192.168.2.211', '30.56083696942529', '104.0445024351646', '四川省', null, '0', '盛安街', 'undefined', '1530946875687', '1', '1');
INSERT INTO `app_clock_in_info` VALUES ('32', '73.13282280533492', '剑南大道中段599号', '484.1397399902344', '成都市', 'wgs84', '中国', '2018-07-07 15:02:29', '武侯区', '192.168.2.211', '30.56132299710612', '104.0437516990282', '四川省', null, '0', '剑南大道中段', '599号', '1530946950196', '2', '1');

-- ----------------------------
-- Table structure for app_department
-- ----------------------------
DROP TABLE IF EXISTS `app_department`;
CREATE TABLE `app_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_company_id` int(11) DEFAULT NULL,
  `d_create_time` datetime DEFAULT NULL,
  `d_create_user` int(11) DEFAULT NULL,
  `d_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_department
-- ----------------------------

-- ----------------------------
-- Table structure for app_login_user
-- ----------------------------
DROP TABLE IF EXISTS `app_login_user`;
CREATE TABLE `app_login_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_company_id` int(11) DEFAULT NULL,
  `u_create_time` datetime DEFAULT NULL,
  `u_create_user` int(11) DEFAULT NULL,
  `u_department` int(11) DEFAULT NULL,
  `u_login_encry` varchar(255) DEFAULT NULL,
  `u_login_name` varchar(255) DEFAULT NULL,
  `u_login_pwd` varchar(255) DEFAULT NULL,
  `u_nick_name` varchar(255) DEFAULT NULL,
  `u_phone` varchar(255) DEFAULT NULL,
  `u_status` int(11) DEFAULT NULL,
  `u_is_admin` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_login_user
-- ----------------------------
INSERT INTO `app_login_user` VALUES ('1', '3', '2018-07-04 11:16:27', null, null, 'twbxv', 'test', '5D8530A310140DD0B8509DE8C71227C8', '测试公司', null, '0', '');

-- ----------------------------
-- Table structure for app_login_user_log
-- ----------------------------
DROP TABLE IF EXISTS `app_login_user_log`;
CREATE TABLE `app_login_user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `l_login_ip` varchar(255) DEFAULT NULL,
  `l_login_time` datetime DEFAULT NULL,
  `l_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_login_user_log
-- ----------------------------
INSERT INTO `app_login_user_log` VALUES ('1', '192.168.2.211', '2018-07-04 11:23:09', '1');
INSERT INTO `app_login_user_log` VALUES ('2', '192.168.2.211', '2018-07-04 11:30:22', '1');
INSERT INTO `app_login_user_log` VALUES ('3', '192.168.2.211', '2018-07-04 11:33:01', '1');
INSERT INTO `app_login_user_log` VALUES ('4', '192.168.2.211', '2018-07-04 15:53:51', '1');
INSERT INTO `app_login_user_log` VALUES ('5', '192.168.2.211', '2018-07-05 15:33:38', '1');

-- ----------------------------
-- Table structure for app_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `app_role_resource`;
CREATE TABLE `app_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_name` varchar(255) DEFAULT NULL,
  `d_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_role_resource
-- ----------------------------
INSERT INTO `app_role_resource` VALUES ('1', '企业统计', '/app/company/count');
INSERT INTO `app_role_resource` VALUES ('2', '入职登记', '/app/entry/registration');
INSERT INTO `app_role_resource` VALUES ('3', '权限管理', '/app/role/manager');
INSERT INTO `app_role_resource` VALUES ('4', '部门管理', '/app/department/manager');

-- ----------------------------
-- Table structure for app_role_resource_user
-- ----------------------------
DROP TABLE IF EXISTS `app_role_resource_user`;
CREATE TABLE `app_role_resource_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_create_time` datetime DEFAULT NULL,
  `d_create_user` int(11) DEFAULT NULL,
  `d_resource` int(11) DEFAULT NULL,
  `d_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_role_resource_user
-- ----------------------------

-- ----------------------------
-- Table structure for p_serial_number
-- ----------------------------
DROP TABLE IF EXISTS `p_serial_number`;
CREATE TABLE `p_serial_number` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `f_column_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_serial_number
-- ----------------------------

-- ----------------------------
-- Table structure for sys_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_action`;
CREATE TABLE `sys_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(255) DEFAULT NULL,
  `a_url` varchar(255) DEFAULT NULL,
  `a_menu_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_action
-- ----------------------------
INSERT INTO `sys_action` VALUES ('1', '菜单查询', '/menu/data', '4');
INSERT INTO `sys_action` VALUES ('2', '菜单删除', '/menu/delete', '4');
INSERT INTO `sys_action` VALUES ('3', '菜单修改', '/menu/update', '4');
INSERT INTO `sys_action` VALUES ('4', '资源管理', '/menu/action', '4');
INSERT INTO `sys_action` VALUES ('5', '菜单保存', '/menu/save', '4');
INSERT INTO `sys_action` VALUES ('6', '新增资源', '/menu/action/add', '4');
INSERT INTO `sys_action` VALUES ('7', '修改资源', '/menu/action/update', '4');
INSERT INTO `sys_action` VALUES ('8', '删除资源', '/menu/action/delete', '4');
INSERT INTO `sys_action` VALUES ('12', '查询', '/user/data', '3');
INSERT INTO `sys_action` VALUES ('13', '新增用户', '/user/save', '3');
INSERT INTO `sys_action` VALUES ('14', '修改用户', '/user/update', '3');
INSERT INTO `sys_action` VALUES ('15', '删除用户', '/user/delete', '3');
INSERT INTO `sys_action` VALUES ('17', '授权管理', '/user/role', '3');
INSERT INTO `sys_action` VALUES ('18', '查询', '/company/data', '7');
INSERT INTO `sys_action` VALUES ('19', '修改', '/company/update', '7');
INSERT INTO `sys_action` VALUES ('20', '删除', '/company/delete', '7');
INSERT INTO `sys_action` VALUES ('21', '续期', '/company/renewal', '7');
INSERT INTO `sys_action` VALUES ('22', '新增公司', '/company/save', '7');

-- ----------------------------
-- Table structure for sys_action_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_action_user`;
CREATE TABLE `sys_action_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sr_action_id` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `sr_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_action_user
-- ----------------------------
INSERT INTO `sys_action_user` VALUES ('39', '12', '2018-07-03 10:50:38', '4');
INSERT INTO `sys_action_user` VALUES ('40', '13', '2018-07-03 10:50:38', '4');
INSERT INTO `sys_action_user` VALUES ('41', '14', '2018-07-03 10:50:38', '4');
INSERT INTO `sys_action_user` VALUES ('42', '15', '2018-07-03 10:50:38', '4');
INSERT INTO `sys_action_user` VALUES ('43', '17', '2018-07-03 10:50:38', '4');
INSERT INTO `sys_action_user` VALUES ('44', '18', '2018-07-03 10:50:38', '4');
INSERT INTO `sys_action_user` VALUES ('45', '19', '2018-07-03 10:50:38', '4');
INSERT INTO `sys_action_user` VALUES ('46', '20', '2018-07-03 10:50:38', '4');
INSERT INTO `sys_action_user` VALUES ('47', '21', '2018-07-03 10:50:38', '4');

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `a_desc` varchar(255) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `a_name` varchar(255) DEFAULT NULL,
  `a_status` int(11) DEFAULT NULL,
  `a_manager_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_company
-- ----------------------------
INSERT INTO `sys_company` VALUES ('1', '2018-07-02 13:27:33', '1', '测试公司描述', '2018-07-02 13:27:33', '测试公司', '2', null);
INSERT INTO `sys_company` VALUES ('2', '2018-07-02 13:29:20', '1', '11', '2018-07-13 13:29:20', '11', '2', null);
INSERT INTO `sys_company` VALUES ('3', '2018-07-04 11:16:27', '1', '测试公司', '2018-07-14 11:16:27', '测试公司', '0', 'test');

-- ----------------------------
-- Table structure for sys_company_renewal_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_company_renewal_info`;
CREATE TABLE `sys_company_renewal_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `a_day` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_company_renewal_info
-- ----------------------------
INSERT INTO `sys_company_renewal_info` VALUES ('1', '2', '2018-07-02 13:48:19', '1', '10');
INSERT INTO `sys_company_renewal_info` VALUES ('2', '2', '2018-07-02 13:48:34', '1', '1');
INSERT INTO `sys_company_renewal_info` VALUES ('3', '3', '2018-07-04 15:53:37', '1', '10');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `m_desc` varchar(255) DEFAULT NULL,
  `m_goorder` int(11) DEFAULT NULL,
  `m_icon` varchar(255) DEFAULT NULL,
  `m_parentid` int(11) DEFAULT NULL,
  `m_text` varchar(255) DEFAULT NULL,
  `m_url` varchar(255) DEFAULT NULL,
  `m_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '1', 'fa fa-cogs', '0', '系统管理', '', '0');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', '2', 'fa fa-users', '1', '用户管理', 'user', '0');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', '4', 'fa fa-list-ul', '1', '菜单管理', 'menu', '1');
INSERT INTO `sys_menu` VALUES ('7', '公司信息管理', '9999', 'fa fa-file-text-o', '1', '公司管理', 'company', '0');

-- ----------------------------
-- Table structure for sys_menu_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_user`;
CREATE TABLE `sys_menu_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_user
-- ----------------------------
INSERT INTO `sys_menu_user` VALUES ('12', '2018-06-29 15:03:06', '1', '1');
INSERT INTO `sys_menu_user` VALUES ('13', '2018-06-29 15:03:06', '7', '1');
INSERT INTO `sys_menu_user` VALUES ('44', '2018-07-03 10:50:38', '1', '4');
INSERT INTO `sys_menu_user` VALUES ('45', '2018-07-03 10:50:38', '3', '4');
INSERT INTO `sys_menu_user` VALUES ('46', '2018-07-03 10:50:38', '7', '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_desc` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `head_portrait` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `login_encry` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `login_pwd` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `u_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '管理员账号', '2017-11-15 09:42:46', '', '', '2018-07-04 15:53:18', 'jack', 'admin', 'F75C16C62166E263E5F8D84881C15DE9', '公司管理员', '0', null, '0');
INSERT INTO `sys_user` VALUES ('2', 'jack', '2018-06-29 09:56:45', null, '\0', null, 'agce', 'jack', '1E63D749FC6DB4CF1656702EA8A2831C', 'jack', null, null, '4');
INSERT INTO `sys_user` VALUES ('3', '11', '2018-06-29 10:05:27', null, '\0', null, 'imlc', '111', '8DD7513099C4B568D9589A296F34106A', '11', null, null, '4');
INSERT INTO `sys_user` VALUES ('4', '', '2018-06-29 12:13:15', null, '\0', '2018-06-29 16:06:04', 'pxyq', 'jack', '797D74703F5FB6DBE54E372CA9CCD5EE', 'jack', null, null, '0');

-- ----------------------------
-- Table structure for wx_access_token
-- ----------------------------
DROP TABLE IF EXISTS `wx_access_token`;
CREATE TABLE `wx_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a_access_token` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `a_expires_in` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for wx_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `wx_pay_info`;
CREATE TABLE `wx_pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(255) DEFAULT NULL,
  `code_url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `data_info_id` int(11) DEFAULT NULL,
  `data_info_type` int(11) DEFAULT NULL,
  `device_info` varchar(255) DEFAULT NULL,
  `err_code` varchar(255) DEFAULT NULL,
  `err_code_des` varchar(255) DEFAULT NULL,
  `mch_id` varchar(255) DEFAULT NULL,
  `a_money` int(11) DEFAULT NULL,
  `nonce_str` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `out_trade_no` varchar(255) DEFAULT NULL,
  `prepay_id` varchar(255) DEFAULT NULL,
  `result_code` varchar(255) DEFAULT NULL,
  `a_sign` varchar(255) DEFAULT NULL,
  `trade_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_pay_info
-- ----------------------------
