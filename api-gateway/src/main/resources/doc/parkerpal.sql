/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3308
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3308
 Source Schema         : parkerpal

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 28/05/2025 15:38:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_station
-- ----------------------------
DROP TABLE IF EXISTS `base_station`;
CREATE TABLE `base_station` (
                                `id` varchar(32) NOT NULL COMMENT '基站ID',
                                `iot_device_id` varchar(32) NOT NULL COMMENT 'IoT设备ID',
                                `type` varchar(32) DEFAULT NULL COMMENT '基站类型',
                                `x_axis` double DEFAULT NULL COMMENT 'X坐标',
                                `y_axis` double DEFAULT NULL COMMENT 'Y坐标',
                                PRIMARY KEY (`id`,`iot_device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of base_station
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for car_visitor
-- ----------------------------
DROP TABLE IF EXISTS `car_visitor`;
CREATE TABLE `car_visitor` (
                               `id` varchar(32) NOT NULL COMMENT 'ID',
                               `mainland_license_plates` varchar(255) NOT NULL COMMENT '内地车牌号码',
                               `other_license_plates` varchar(255) DEFAULT NULL COMMENT '其他车牌号码，多个车牌号码英文逗号分隔',
                               `start_time` datetime NOT NULL COMMENT '免收费开始时间',
                               `end_time` datetime NOT NULL COMMENT '免收费结束时间',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆管理-访客车登记管理表';

-- ----------------------------
-- Records of car_visitor
-- ----------------------------
BEGIN;
INSERT INTO `car_visitor` (`id`, `mainland_license_plates`, `other_license_plates`, `start_time`, `end_time`, `create_time`, `update_time`) VALUES ('1913130883909423106', '粤A145657', '', '2025-04-01 00:00:00', '2025-05-31 00:00:00', '2025-04-18 15:22:02', '2025-04-18 15:25:20');
COMMIT;

-- ----------------------------
-- Table structure for car_vpi_monthly_rent
-- ----------------------------
DROP TABLE IF EXISTS `car_vpi_monthly_rent`;
CREATE TABLE `car_vpi_monthly_rent` (
                                        `id` varchar(32) NOT NULL COMMENT 'ID',
                                        `mainland_license_plates` varchar(255) NOT NULL COMMENT '内地车牌号码',
                                        `other_license_plates` varchar(255) DEFAULT NULL COMMENT '其他车牌号码，多个车牌号码英文逗号分隔',
                                        `create_time` datetime DEFAULT NULL COMMENT '登记时间',
                                        `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆管理-vip月租车登记管理表';

-- ----------------------------
-- Records of car_vpi_monthly_rent
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iot_device
-- ----------------------------
DROP TABLE IF EXISTS `iot_device`;
CREATE TABLE `iot_device` (
                              `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备ID',
                              `mac_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备MAC地址',
                              `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备名称',
                              `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备所在位置',
                              `role` int DEFAULT NULL COMMENT '设备作用',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                              `group_id` int DEFAULT NULL COMMENT '组号',
                              `create_user_id` int DEFAULT NULL COMMENT '创建用户ID',
                              `is_disabled` tinyint NOT NULL DEFAULT '0' COMMENT '是否禁用，0|否；1|是',
                              `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除，0|否；1|是',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物联网-设备表';

-- ----------------------------
-- Records of iot_device
-- ----------------------------
BEGIN;
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1', '68:C6:3A:FB:E6:17', 'SA-101', 'A', 0, '2025-01-02 09:44:14', '2025-04-18 15:44:11', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1000', '30:ED:A0:28:28:34', 'SA-TEST-1081', 'A', 0, '2025-02-02 07:24:31', '2025-02-13 17:05:34', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1001', 'F5:6D:DD:8A:07:66', 'SA-TEST-4161', 'A', 0, '2024-12-29 02:27:13', '2025-02-13 20:23:09', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1002', '31:5B:04:AE:E8:7C', 'SA-TEST-6026', 'A', 0, '2023-10-10 02:02:45', '2025-02-13 15:56:48', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1003', '05:50:A5:57:67:9B', 'SA-TEST-5286', 'A', 0, '2024-03-28 05:28:45', '2025-02-13 22:24:28', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1004', '7D:94:DD:B4:1F:27', 'SA-TEST-7872', 'A', 0, '2024-03-27 06:36:24', '2025-02-13 00:15:11', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1005', '35:78:B1:46:EE:D2', 'SA-TEST-6636', 'A', 0, '2024-02-03 04:20:18', '2025-02-13 22:24:29', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1006', 'B1:7B:4B:F6:8F:D6', 'SA-TEST-2359', 'A', 0, '2023-10-15 13:36:49', '2025-02-13 21:42:20', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1007', '31:2E:55:6E:B3:EF', 'SA-TEST-5422', 'A', 0, '2023-03-03 04:58:40', '2025-02-13 12:18:43', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1008', 'FB:FF:B2:EA:40:FC', 'SA-TEST-5504', 'A', 0, '2024-02-15 18:13:49', '2025-02-13 09:59:29', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1009', '4B:CF:A6:92:58:07', 'SA-TEST-3425', 'A', 0, '2023-09-22 22:03:19', '2025-02-13 00:32:09', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1010', '7D:94:CD:7A:79:C0', 'SA-TEST-4683', 'A', 0, '2024-01-20 01:51:48', '2025-02-13 18:12:36', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1011', '73:18:60:97:A0:E7', 'SA-TEST-7887', 'A', 0, '2024-07-04 15:34:12', '2025-02-13 18:44:41', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1012', '7A:62:F4:81:FD:63', 'SA-TEST-8703', 'A', 0, '2023-11-01 12:59:36', '2025-02-13 17:45:49', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1013', '62:52:C9:8A:AC:98', 'SA-TEST-4038', 'A', 0, '2023-06-09 06:40:49', '2025-02-13 02:17:04', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1014', 'FD:D4:99:58:11:50', 'SA-TEST-3472', 'A', 0, '2024-10-13 17:07:51', '2025-02-13 17:40:38', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1015', '40:3E:85:21:5F:D6', 'SA-TEST-3274', 'A', 0, '2023-01-21 18:19:16', '2025-02-13 11:38:35', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1016', 'FA:53:47:D8:2B:F5', 'SA-TEST-7786', 'A', 0, '2023-07-17 22:50:22', '2025-02-13 16:27:30', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1017', '92:B5:F7:CC:49:B5', 'SA-TEST-8009', 'A', 0, '2024-12-23 07:33:08', '2025-02-13 08:34:58', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1018', '10:8D:F0:3C:66:53', 'SA-TEST-6804', 'A', 0, '2023-03-30 16:59:20', '2025-02-13 02:27:52', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1019', '9E:B0:58:DB:1E:FC', 'SA-TEST-4953', 'A', 0, '2024-06-04 06:42:54', '2025-02-13 04:16:35', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1020', '66:1E:FF:FB:8B:1D', 'SA-TEST-8521', 'A', 0, '2023-08-13 19:10:26', '2025-02-13 05:21:55', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1021', '9A:90:E6:C5:D9:54', 'SA-TEST-3860', 'A', 0, '2024-08-07 06:01:54', '2025-02-13 20:14:56', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1022', 'F7:89:BB:4F:A0:08', 'SA-TEST-9428', 'A', 0, '2024-05-07 14:43:04', '2025-02-13 07:59:54', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1023', 'F0:D9:34:49:97:A1', 'SA-TEST-2898', 'A', 0, '2024-10-03 04:23:29', '2025-02-13 16:35:18', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1024', 'A3:80:66:87:35:7E', 'SA-TEST-8638', 'A', 0, '2024-04-05 04:05:27', '2025-02-13 00:46:06', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1025', '9C:62:5E:79:82:16', 'SA-TEST-9777', 'A', 0, '2023-02-10 00:56:39', '2025-02-13 11:46:27', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1026', '79:43:47:0D:82:0B', 'SA-TEST-7523', 'A', 0, '2024-10-15 16:16:42', '2025-02-13 07:57:04', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1027', 'CF:9C:7B:9F:5A:E3', 'SA-TEST-0379', 'A', 0, '2023-04-08 08:13:25', '2025-02-13 22:48:00', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1028', '2E:06:AB:D2:D9:D9', 'SA-TEST-5412', 'A', 0, '2024-05-17 11:29:21', '2025-02-13 00:58:09', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1029', '92:C7:28:BA:84:C6', 'SA-TEST-8589', 'A', 0, '2023-09-07 15:14:07', '2025-02-13 12:54:29', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1030', 'A7:28:9D:4D:B5:A3', 'SA-TEST-4368', 'A', 0, '2024-02-09 14:00:34', '2025-02-13 20:07:35', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1031', '2C:8E:88:C9:63:79', 'SA-TEST-7909', 'A', 0, '2024-10-19 06:29:06', '2025-02-13 10:12:57', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1032', '97:E8:04:1C:1E:45', 'SA-TEST-7681', 'A', 0, '2024-06-25 00:40:42', '2025-02-13 18:11:07', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1033', 'E9:1B:BF:04:4E:F1', 'SA-TEST-3042', 'A', 0, '2024-12-04 22:15:22', '2025-02-13 12:31:37', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1034', 'BE:A2:67:B2:F3:BB', 'SA-TEST-0887', 'A', 0, '2024-08-01 00:59:42', '2025-02-13 00:08:25', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1035', '7B:A5:8D:20:7E:A4', 'SA-TEST-6031', 'A', 0, '2023-11-17 18:48:16', '2025-02-13 08:43:08', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1036', '91:C2:46:AC:6C:1E', 'SA-TEST-7006', 'A', 0, '2024-07-19 08:50:26', '2025-02-13 17:44:17', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1037', 'B6:6B:F8:3E:84:90', 'SA-TEST-6409', 'A', 0, '2024-03-08 10:07:56', '2025-02-13 07:40:15', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1038', 'C5:EE:00:9F:5E:DB', 'SA-TEST-7979', 'A', 0, '2023-05-02 09:18:21', '2025-02-13 02:27:32', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1039', 'EE:03:5E:E9:EE:82', 'SA-TEST-3279', 'A', 0, '2024-07-19 14:19:55', '2025-02-13 08:13:44', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1040', '9B:AB:11:FA:D8:43', 'SA-TEST-3832', 'A', 0, '2024-08-11 08:06:29', '2025-02-13 19:54:41', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1041', '7B:58:32:1E:8B:76', 'SA-TEST-8376', 'A', 0, '2023-01-10 18:27:36', '2025-02-13 14:07:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1042', '27:8D:A8:7D:67:51', 'SA-TEST-2475', 'A', 0, '2024-04-12 10:04:54', '2025-02-13 04:51:54', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1043', '3C:C8:DF:D2:02:3C', 'SA-TEST-2420', 'A', 0, '2024-03-12 20:59:03', '2025-02-13 23:28:37', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1044', 'BA:2B:DE:0E:10:3B', 'SA-TEST-6476', 'A', 0, '2023-04-29 11:27:31', '2025-02-13 16:08:05', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1045', '4F:B2:A4:1C:EC:45', 'SA-TEST-8988', 'A', 0, '2023-04-03 12:47:16', '2025-02-13 19:15:41', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1046', '2C:5E:48:11:FA:55', 'SA-TEST-5977', 'A', 0, '2024-10-21 00:55:23', '2025-02-13 17:00:10', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1047', 'D1:D6:9C:9D:C2:3A', 'SA-TEST-7038', 'A', 0, '2024-12-04 18:48:26', '2025-02-13 02:37:12', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1048', 'D2:79:02:1B:95:27', 'SA-TEST-7779', 'A', 0, '2025-02-11 12:32:50', '2025-02-13 19:33:26', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1049', 'DF:7C:0A:0B:6B:64', 'SA-TEST-8015', 'A', 0, '2024-08-02 06:11:00', '2025-02-13 03:04:27', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1050', '38:F6:DC:BF:05:40', 'SA-TEST-9743', 'A', 0, '2024-05-08 07:10:15', '2025-02-13 12:12:13', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1051', '84:83:51:8A:8B:F3', 'SA-TEST-1773', 'A', 0, '2024-08-15 10:11:17', '2025-02-13 11:45:10', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1052', '8A:64:72:E4:8F:FD', 'SA-TEST-8466', 'A', 0, '2023-11-27 18:28:54', '2025-02-13 14:51:32', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1053', '8E:D0:9E:CF:30:C0', 'SA-TEST-2751', 'A', 0, '2023-07-03 03:58:34', '2025-02-13 02:15:08', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1054', '5E:AD:86:EB:09:24', 'SA-TEST-7497', 'A', 0, '2023-07-30 19:08:40', '2025-02-13 01:39:19', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1055', '47:6B:CF:91:AC:DA', 'SA-TEST-6133', 'A', 0, '2024-04-02 05:49:30', '2025-02-13 11:14:36', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1056', 'BE:34:F4:F5:41:41', 'SA-TEST-4951', 'A', 0, '2024-02-29 17:01:58', '2025-02-13 06:42:19', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1057', '29:14:B4:FF:B2:27', 'SA-TEST-3108', 'A', 0, '2024-07-16 23:04:46', '2025-02-13 14:20:52', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1058', 'C7:B9:26:03:07:92', 'SA-TEST-7250', 'A', 0, '2024-07-16 21:29:04', '2025-02-13 17:19:00', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1059', '7A:CD:78:0C:E2:C7', 'SA-TEST-8409', 'A', 0, '2024-02-09 00:55:41', '2025-02-13 00:24:37', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1060', '91:C3:54:8C:19:F4', 'SA-TEST-1194', 'A', 0, '2024-12-19 01:55:05', '2025-02-13 16:18:26', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1061', '36:58:10:34:AE:45', 'SA-TEST-1185', 'A', 0, '2023-06-09 17:03:31', '2025-02-13 13:35:04', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1062', '05:5C:4A:59:80:22', 'SA-TEST-6138', 'A', 0, '2024-03-10 21:33:09', '2025-02-13 05:51:30', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1063', '88:07:4B:17:65:CD', 'SA-TEST-5922', 'A', 0, '2023-02-22 06:27:10', '2025-02-13 02:11:47', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1064', 'FF:2C:06:B1:C9:12', 'SA-TEST-4946', 'A', 0, '2024-05-13 10:05:49', '2025-02-13 09:53:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1065', '59:D7:47:3F:4E:30', 'SA-TEST-3914', 'A', 0, '2023-04-06 12:54:15', '2025-02-13 12:00:53', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1066', '6D:DD:DF:DD:94:EC', 'SA-TEST-5958', 'A', 0, '2025-02-04 21:18:50', '2025-02-13 12:27:17', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1067', 'AA:EA:53:88:D8:86', 'SA-TEST-8452', 'A', 0, '2024-05-04 00:29:51', '2025-02-13 13:21:20', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1068', '04:17:43:07:61:6F', 'SA-TEST-1162', 'A', 0, '2024-05-13 10:37:33', '2025-02-13 23:55:44', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1069', 'F0:16:53:1B:07:0B', 'SA-TEST-7693', 'A', 0, '2023-12-06 13:59:54', '2025-02-13 15:22:57', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1070', 'C1:50:5C:42:9D:46', 'SA-TEST-1216', 'A', 0, '2024-01-09 00:46:51', '2025-02-13 04:27:49', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1071', 'D8:C3:79:F1:18:C7', 'SA-TEST-8986', 'A', 0, '2024-03-05 00:19:06', '2025-02-13 20:30:11', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1072', 'FC:C2:BE:82:63:CF', 'SA-TEST-2414', 'A', 0, '2023-12-31 16:55:16', '2025-02-13 15:58:27', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1073', '53:A8:7F:68:DE:04', 'SA-TEST-5857', 'A', 0, '2024-03-31 00:44:40', '2025-02-13 04:08:42', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1074', 'FA:99:EF:87:53:FA', 'SA-TEST-0389', 'A', 0, '2023-05-30 05:45:45', '2025-02-13 21:21:48', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1075', 'CA:F3:4E:8A:85:FE', 'SA-TEST-1139', 'A', 0, '2023-08-03 07:17:21', '2025-02-13 20:48:08', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1076', '46:AC:E4:E6:16:0B', 'SA-TEST-8162', 'A', 0, '2023-08-16 10:59:40', '2025-02-13 20:30:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1077', '7D:9F:4F:19:7E:78', 'SA-TEST-8673', 'A', 0, '2023-12-22 17:10:24', '2025-02-13 04:43:41', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1078', '13:B7:4C:3B:9A:56', 'SA-TEST-8555', 'A', 0, '2024-01-17 19:23:02', '2025-02-13 19:53:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1079', 'CC:6D:92:8E:3B:D1', 'SA-TEST-5291', 'A', 0, '2024-05-24 17:29:06', '2025-02-13 11:18:40', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1080', '1E:75:6B:9A:13:F4', 'SA-TEST-6135', 'A', 0, '2024-10-25 17:02:26', '2025-02-13 05:08:25', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1081', '41:F0:9C:C3:67:75', 'SA-TEST-0696', 'A', 0, '2024-08-07 20:04:43', '2025-02-13 14:37:36', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1082', '6E:9B:04:EC:C0:D4', 'SA-TEST-6273', 'A', 0, '2023-08-28 05:31:18', '2025-02-13 20:29:07', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1083', 'E4:71:86:A1:14:E6', 'SA-TEST-1040', 'A', 0, '2024-01-12 14:48:07', '2025-02-13 04:02:22', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1084', '51:49:CA:24:51:0C', 'SA-TEST-3693', 'A', 0, '2023-06-10 16:01:45', '2025-02-13 13:13:59', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1085', 'EB:B8:22:F8:FC:96', 'SA-TEST-3255', 'A', 0, '2024-11-22 11:14:06', '2025-02-13 05:17:42', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1086', 'B4:F9:87:42:63:DE', 'SA-TEST-5233', 'A', 0, '2025-01-28 03:21:23', '2025-02-13 16:22:41', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1087', 'D5:E8:C2:1C:40:88', 'SA-TEST-2202', 'A', 0, '2024-04-28 06:24:09', '2025-02-13 22:20:41', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1088', '1B:AD:3D:1B:FE:DC', 'SA-TEST-8205', 'A', 0, '2024-02-14 11:56:03', '2025-02-13 19:53:34', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1089', '06:AD:D0:16:1B:95', 'SA-TEST-8030', 'A', 0, '2024-07-18 22:09:37', '2025-02-13 16:27:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1090', 'DC:7A:D4:06:87:CC', 'SA-TEST-3624', 'A', 0, '2024-07-27 12:17:54', '2025-02-13 10:18:10', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1091', 'E1:DB:2D:9F:35:67', 'SA-TEST-9748', 'A', 0, '2024-12-18 01:32:47', '2025-02-13 13:49:14', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1092', '34:7B:AD:CF:D8:BA', 'SA-TEST-3769', 'A', 0, '2023-10-06 13:50:09', '2025-02-13 10:53:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1093', 'CF:84:FC:BE:FD:42', 'SA-TEST-9583', 'A', 0, '2024-10-26 18:59:11', '2025-02-13 14:48:43', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1094', 'DB:A1:72:04:2A:2A', 'SA-TEST-6055', 'A', 0, '2024-06-14 12:28:38', '2025-02-13 20:43:04', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1095', '71:6F:61:39:17:25', 'SA-TEST-2361', 'A', 0, '2024-07-15 15:10:34', '2025-02-13 00:34:07', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1096', '99:48:FE:FE:79:99', 'SA-TEST-7442', 'A', 0, '2025-01-02 17:48:58', '2025-02-13 15:23:13', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1097', 'D6:EE:5C:21:EF:41', 'SA-TEST-1441', 'A', 0, '2023-07-15 13:12:58', '2025-02-13 16:44:26', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1098', '27:D5:29:C2:35:F9', 'SA-TEST-5774', 'A', 0, '2024-04-08 13:11:50', '2025-02-13 16:58:49', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('1099', 'DC:9C:BE:A7:1E:53', 'SA-TEST-5522', 'A', 0, '2024-11-14 04:48:31', '2025-02-13 10:48:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2000', 'C5:85:7C:ED:A4:8B', 'BA-TEST-0002', 'A', 1, '2025-01-28 17:45:14', '2025-02-13 15:35:03', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2001', '8E:C3:9B:18:AE:34', 'BA-TEST-5690', 'A', 1, '2024-02-05 18:48:55', '2025-02-13 14:55:39', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2002', '36:3B:D0:3E:6D:FA', 'BA-TEST-9412', 'A', 1, '2023-07-24 02:04:54', '2025-02-13 09:22:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2003', '1F:45:3C:9B:E6:CB', 'BA-TEST-5667', 'A', 1, '2024-10-17 11:06:57', '2025-02-13 07:37:57', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2004', '54:FC:0F:2E:30:E5', 'BA-TEST-2105', 'A', 1, '2025-02-05 13:22:12', '2025-02-13 00:17:08', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2005', 'B0:A3:FB:CF:37:C4', 'BA-TEST-6140', 'A', 1, '2023-06-14 14:02:06', '2025-02-13 08:47:07', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2006', '57:D6:6D:EA:93:06', 'BA-TEST-7530', 'A', 1, '2023-06-01 08:29:50', '2025-02-13 21:06:28', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2007', '6C:EC:53:2A:1F:CB', 'BA-TEST-0646', 'A', 1, '2024-05-12 20:44:10', '2025-02-13 09:54:49', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2008', '29:B7:01:D3:C3:05', 'BA-TEST-0027', 'A', 1, '2023-10-15 02:02:37', '2025-02-13 22:59:04', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2009', '8A:C8:F0:36:64:4C', 'BA-TEST-9108', 'A', 1, '2025-01-22 12:18:04', '2025-02-13 00:28:08', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2010', 'CD:DF:1A:91:5A:C6', 'BA-TEST-0475', 'A', 1, '2024-09-12 11:11:08', '2025-02-13 00:23:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2011', '65:0A:B7:60:87:37', 'BA-TEST-0125', 'A', 1, '2023-03-24 07:16:36', '2025-02-13 08:54:32', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2012', '78:67:FE:8F:70:C3', 'BA-TEST-3372', 'A', 1, '2024-06-24 08:30:05', '2025-02-13 23:50:19', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2013', '8B:E0:4E:36:B5:25', 'BA-TEST-0920', 'A', 1, '2024-11-24 22:28:25', '2025-02-13 05:01:37', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2014', '8D:77:BF:06:59:D4', 'BA-TEST-7314', 'A', 1, '2025-02-05 00:53:04', '2025-02-13 01:32:54', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2015', 'B0:E2:EE:5B:32:BF', 'BA-TEST-6883', 'A', 1, '2023-10-01 11:16:27', '2025-02-13 19:19:58', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2016', 'EC:F4:90:A4:69:46', 'BA-TEST-1874', 'A', 1, '2024-07-17 14:39:38', '2025-02-13 19:39:15', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2017', '72:1B:C4:DA:CC:0A', 'BA-TEST-1377', 'A', 1, '2024-03-02 00:51:18', '2025-02-13 07:34:06', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2018', '3C:CD:32:10:A0:1A', 'BA-TEST-0542', 'A', 1, '2024-09-08 13:25:30', '2025-02-13 18:17:32', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2019', 'BB:69:B7:1B:23:FC', 'BA-TEST-6220', 'A', 1, '2023-08-09 18:32:09', '2025-02-13 21:18:13', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2020', '00:44:56:07:E1:A2', 'BA-TEST-8106', 'A', 1, '2025-02-08 12:16:41', '2025-02-13 15:30:33', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2021', 'CD:B8:4F:70:94:01', 'BA-TEST-8185', 'A', 1, '2024-01-04 19:32:06', '2025-02-13 09:02:08', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2022', 'A1:07:66:C4:54:A4', 'BA-TEST-5873', 'A', 1, '2024-12-26 20:41:12', '2025-02-13 04:51:52', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2023', 'DD:BC:BF:E5:BF:68', 'BA-TEST-4430', 'A', 1, '2024-06-07 20:07:19', '2025-02-13 05:19:01', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2024', 'DD:84:40:5B:64:48', 'BA-TEST-1471', 'A', 1, '2024-11-27 14:22:19', '2025-02-13 12:00:35', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2025', 'FF:6A:F1:6E:02:26', 'BA-TEST-5821', 'A', 1, '2023-08-22 07:28:30', '2025-02-13 04:18:22', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2026', 'C7:55:36:97:E2:02', 'BA-TEST-1062', 'A', 1, '2024-08-19 05:02:14', '2025-02-13 19:19:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2027', '27:E7:0B:34:25:AF', 'BA-TEST-3219', 'A', 1, '2024-04-21 08:12:41', '2025-02-13 06:57:50', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2028', 'B6:6B:FD:DA:3A:48', 'BA-TEST-1468', 'A', 1, '2024-03-08 07:45:04', '2025-02-13 11:19:55', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2029', '59:11:14:87:77:3A', 'BA-TEST-9979', 'A', 1, '2023-04-05 07:39:17', '2025-02-13 09:56:28', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2030', 'D9:B3:AB:06:18:B2', 'BA-TEST-7250', 'A', 1, '2024-07-19 17:56:34', '2025-02-13 16:44:43', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2031', 'B3:C7:50:89:01:30', 'BA-TEST-1925', 'A', 1, '2023-01-19 19:20:15', '2025-02-13 02:13:04', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2032', '73:1C:1B:56:07:61', 'BA-TEST-0732', 'A', 1, '2023-06-28 14:57:04', '2025-02-13 11:54:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2033', 'C1:DF:F6:C3:24:F9', 'BA-TEST-2236', 'A', 1, '2023-03-22 02:39:02', '2025-02-13 00:00:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2034', '1F:B5:A3:7C:70:AC', 'BA-TEST-9392', 'A', 1, '2024-06-03 02:18:02', '2025-02-13 13:49:54', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2035', '20:C4:01:2A:18:19', 'BA-TEST-3937', 'A', 1, '2024-08-30 02:09:24', '2025-02-13 23:16:53', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2036', '08:15:00:0F:6C:5C', 'BA-TEST-6032', 'A', 1, '2023-09-05 15:04:33', '2025-02-13 08:57:14', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2037', 'EF:7D:DF:A9:B3:1A', 'BA-TEST-4228', 'A', 1, '2023-07-15 06:55:49', '2025-02-13 19:45:12', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2038', '85:0F:97:8E:D0:0E', 'BA-TEST-8857', 'A', 1, '2023-12-22 05:24:49', '2025-02-13 15:50:48', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2039', 'D8:3D:62:66:27:DA', 'BA-TEST-8632', 'A', 1, '2024-11-15 14:06:28', '2025-02-13 03:39:04', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2040', '67:45:3B:FB:27:E1', 'BA-TEST-4883', 'A', 1, '2023-08-11 19:28:38', '2025-02-13 23:30:46', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2041', '89:BC:8D:E1:58:7E', 'BA-TEST-5268', 'A', 1, '2024-02-07 22:09:06', '2025-02-13 12:51:49', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2042', 'D0:9A:A5:CA:E1:D3', 'BA-TEST-6402', 'A', 1, '2025-01-14 15:11:06', '2025-02-13 11:19:15', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2043', 'E8:B3:6C:6F:56:61', 'BA-TEST-7552', 'A', 1, '2023-04-06 16:54:02', '2025-02-13 15:57:34', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2044', 'B1:55:DA:1D:78:61', 'BA-TEST-9581', 'A', 1, '2023-12-02 19:58:19', '2025-02-13 05:54:59', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2045', '04:87:E4:C9:C0:FA', 'BA-TEST-5637', 'A', 1, '2023-10-10 17:53:18', '2025-02-13 10:33:13', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2046', '37:C5:00:CD:26:53', 'BA-TEST-6247', 'A', 1, '2025-01-14 20:01:47', '2025-02-13 13:19:30', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2047', '2B:D9:EE:10:A9:49', 'BA-TEST-2026', 'A', 1, '2024-01-29 08:00:51', '2025-02-13 15:18:29', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2048', '3B:D5:0D:EB:93:40', 'BA-TEST-0382', 'A', 1, '2023-09-30 10:12:53', '2025-02-13 04:37:22', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2049', 'B9:73:9D:66:63:9B', 'BA-TEST-5770', 'A', 1, '2024-01-16 11:30:08', '2025-02-13 22:56:28', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2050', '65:F7:97:EA:FF:69', 'BA-TEST-9046', 'A', 1, '2024-12-12 05:05:46', '2025-02-13 14:17:37', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2051', '60:F2:2B:D4:E4:F8', 'BA-TEST-7120', 'A', 1, '2024-03-10 18:21:56', '2025-02-13 23:08:23', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2052', 'E5:44:04:59:55:43', 'BA-TEST-8445', 'A', 1, '2024-07-11 10:56:13', '2025-02-13 01:14:24', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2053', 'FA:E8:8E:7C:08:6E', 'BA-TEST-6632', 'A', 1, '2024-08-10 23:21:19', '2025-02-13 16:16:57', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2054', '7E:D0:0F:6D:F9:A7', 'BA-TEST-0440', 'A', 1, '2024-12-08 06:03:41', '2025-02-13 00:41:35', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2055', '7F:6E:1C:08:EB:D7', 'BA-TEST-2202', 'A', 1, '2024-05-18 00:32:16', '2025-02-13 20:18:34', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2056', '8D:36:35:93:96:4E', 'BA-TEST-6031', 'A', 1, '2024-08-23 10:12:12', '2025-02-13 19:16:39', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2057', 'C8:32:4A:CA:7D:85', 'BA-TEST-8846', 'A', 1, '2024-07-31 17:57:22', '2025-02-13 00:44:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2058', 'E5:2F:55:8D:C8:F3', 'BA-TEST-8804', 'A', 1, '2023-06-27 04:02:18', '2025-02-13 15:09:02', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2059', 'A1:D1:62:76:ED:F0', 'BA-TEST-7308', 'A', 1, '2023-04-28 09:20:41', '2025-02-13 21:57:02', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2060', 'E9:00:5C:6F:23:00', 'BA-TEST-2165', 'A', 1, '2023-03-24 08:48:34', '2025-02-13 14:51:18', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2061', '4A:31:B6:6C:9A:A9', 'BA-TEST-8284', 'A', 1, '2024-07-30 23:12:26', '2025-02-13 06:07:49', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2062', '73:33:11:FE:05:9C', 'BA-TEST-7632', 'A', 1, '2023-01-16 08:25:11', '2025-02-13 18:10:59', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2063', '27:7F:00:FE:21:54', 'BA-TEST-0503', 'A', 1, '2023-10-01 23:16:16', '2025-02-13 22:19:04', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2064', 'AD:8B:52:FE:A4:59', 'BA-TEST-9586', 'A', 1, '2023-12-23 19:43:48', '2025-02-13 13:34:38', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2065', '0F:B1:3A:EA:02:94', 'BA-TEST-4234', 'A', 1, '2024-12-18 12:48:39', '2025-02-13 13:19:56', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2066', '4B:C4:D7:A1:FB:C5', 'BA-TEST-8770', 'A', 1, '2024-11-20 18:01:47', '2025-02-13 17:18:31', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2067', '36:02:AC:06:C4:C2', 'BA-TEST-8296', 'A', 1, '2023-07-31 08:45:23', '2025-02-13 18:37:32', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2068', '8D:BC:22:5B:77:61', 'BA-TEST-1074', 'A', 1, '2024-08-20 11:28:50', '2025-02-13 14:41:07', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2069', 'B3:49:5D:24:A4:C7', 'BA-TEST-4149', 'A', 1, '2023-12-23 07:05:38', '2025-02-13 16:19:22', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2070', '47:6D:26:34:F0:C0', 'BA-TEST-3164', 'A', 1, '2023-06-12 09:48:34', '2025-02-13 05:40:50', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2071', '88:9D:E1:F0:03:AC', 'BA-TEST-2508', 'A', 1, '2025-01-09 06:22:51', '2025-02-13 12:39:12', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2072', 'A1:96:0E:F0:74:69', 'BA-TEST-8018', 'A', 1, '2024-10-12 08:48:15', '2025-02-13 14:17:26', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2073', '4C:D5:E7:11:C7:C4', 'BA-TEST-4200', 'A', 1, '2023-06-28 13:33:35', '2025-02-13 18:08:38', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2074', 'C6:DC:B3:66:36:FD', 'BA-TEST-2943', 'A', 1, '2024-03-21 11:04:03', '2025-02-13 10:16:49', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2075', 'C8:07:A1:95:1E:F6', 'BA-TEST-4742', 'A', 1, '2024-05-20 10:43:27', '2025-02-13 02:45:39', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2076', 'E4:91:E1:68:7E:15', 'BA-TEST-3629', 'A', 1, '2023-08-07 16:28:34', '2025-02-13 17:30:27', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2077', 'CB:5D:1E:F3:86:49', 'BA-TEST-9529', 'A', 1, '2023-07-13 09:52:15', '2025-02-13 20:11:53', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2078', '40:AA:55:1F:CA:F6', 'BA-TEST-6634', 'A', 1, '2023-11-04 02:36:45', '2025-02-13 20:39:00', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2079', 'D0:99:C7:CF:48:D0', 'BA-TEST-0937', 'A', 1, '2025-02-08 17:08:20', '2025-02-13 14:01:00', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2080', 'C7:09:E3:70:C1:79', 'BA-TEST-0990', 'A', 1, '2025-02-13 14:58:54', '2025-02-13 14:56:23', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2081', '17:47:3F:1B:60:2F', 'BA-TEST-1366', 'A', 1, '2023-04-27 23:59:17', '2025-02-13 07:58:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2082', '4F:56:AD:33:2B:62', 'BA-TEST-5414', 'A', 1, '2023-04-16 18:33:52', '2025-02-13 05:04:31', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2083', 'FE:50:F0:6B:50:24', 'BA-TEST-5448', 'A', 1, '2025-01-21 13:23:11', '2025-02-13 20:12:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2084', 'B7:A5:CD:46:33:5E', 'BA-TEST-2797', 'A', 1, '2024-09-17 01:38:51', '2025-02-13 18:59:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2085', '64:75:18:7B:3D:67', 'BA-TEST-0230', 'A', 1, '2024-01-10 02:16:49', '2025-02-13 11:41:51', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2086', '89:31:46:C6:D7:CE', 'BA-TEST-1401', 'A', 1, '2024-01-09 12:04:07', '2025-02-13 14:58:36', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2087', '43:C4:19:AB:5A:5A', 'BA-TEST-8419', 'A', 1, '2024-08-09 03:40:30', '2025-02-13 00:45:03', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2088', '87:F5:92:F2:52:6D', 'BA-TEST-4341', 'A', 1, '2023-10-06 00:29:09', '2025-02-13 17:39:50', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2089', '3F:66:43:82:C1:FE', 'BA-TEST-3585', 'A', 1, '2023-08-15 16:33:47', '2025-02-13 07:38:42', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2090', '6E:92:D6:B4:7A:1B', 'BA-TEST-3737', 'A', 1, '2024-08-07 16:41:06', '2025-02-13 01:43:20', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2091', '74:70:A8:1C:5B:D5', 'BA-TEST-0132', 'A', 1, '2024-09-25 21:59:18', '2025-02-13 03:34:06', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2092', '2D:F7:C2:D9:E3:EA', 'BA-TEST-5988', 'A', 1, '2024-07-02 07:37:56', '2025-02-13 21:39:50', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2093', '86:5F:B2:17:FD:D7', 'BA-TEST-5435', 'A', 1, '2025-01-17 00:16:29', '2025-02-13 07:15:21', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2094', 'B1:78:3A:FB:D6:AB', 'BA-TEST-0866', 'A', 1, '2024-02-17 08:50:23', '2025-02-13 13:29:58', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2095', '07:24:22:F5:EF:9F', 'BA-TEST-0198', 'A', 1, '2023-07-28 05:17:11', '2025-02-13 22:35:41', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2096', '7D:A6:C0:61:EE:C5', 'BA-TEST-2820', 'A', 1, '2024-07-15 05:54:07', '2025-02-13 23:30:38', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2097', '7E:65:37:BC:B1:EA', 'BA-TEST-4761', 'A', 1, '2024-05-13 09:02:50', '2025-02-13 06:54:44', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2098', '51:B8:AB:46:67:3E', 'BA-TEST-6190', 'A', 1, '2024-02-24 16:30:30', '2025-02-13 23:35:17', 1, 1, 0, 0);
INSERT INTO `iot_device` (`id`, `mac_address`, `name`, `location`, `role`, `create_time`, `update_time`, `group_id`, `create_user_id`, `is_disabled`, `is_deleted`) VALUES ('2099', '3D:C3:00:68:10:EA', 'BA-TEST-9237', 'A', 1, '2024-05-31 17:21:55', '2025-02-13 16:31:48', 1, 1, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for iot_device_group
-- ----------------------------
DROP TABLE IF EXISTS `iot_device_group`;
CREATE TABLE `iot_device_group` (
                                    `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                    `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组名称',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                    `create_user` int DEFAULT NULL COMMENT '创建用户ID',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物联网-设备组表';

-- ----------------------------
-- Records of iot_device_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for iot_device_parking_space
-- ----------------------------
DROP TABLE IF EXISTS `iot_device_parking_space`;
CREATE TABLE `iot_device_parking_space` (
                                            `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                            `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备ID',
                                            `parking_space` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车位ID',
                                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                            `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                            `create_user_id` int DEFAULT NULL COMMENT '创建用户ID',
                                            PRIMARY KEY (`id`),
                                            KEY `deviceid` (`device_id`),
                                            KEY `spaceid` (`parking_space`),
                                            CONSTRAINT `deviceid` FOREIGN KEY (`device_id`) REFERENCES `iot_device` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                            CONSTRAINT `spaceid` FOREIGN KEY (`parking_space`) REFERENCES `system_parking_space` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物联网-设备车位关联表';

-- ----------------------------
-- Records of iot_device_parking_space
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for merchant_reconciliation
-- ----------------------------
DROP TABLE IF EXISTS `merchant_reconciliation`;
CREATE TABLE `merchant_reconciliation` (
                                           `id` varchar(32) NOT NULL COMMENT 'ID',
                                           `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
                                           `total_duration` varchar(255) DEFAULT NULL COMMENT '总停车时长',
                                           `total_amount` varchar(255) DEFAULT NULL COMMENT '总计金额',
                                           `total_discount_amount` varchar(255) DEFAULT NULL COMMENT '总计优惠金额',
                                           `total_income_amount` varchar(255) DEFAULT NULL COMMENT '总计收入金额',
                                           `year_number` varchar(255) DEFAULT NULL COMMENT '年份',
                                           `month_number` varchar(255) DEFAULT NULL COMMENT '月份',
                                           `status` varchar(255) DEFAULT NULL COMMENT '发放状态',
                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商户-对账记录';

-- ----------------------------
-- Records of merchant_reconciliation
-- ----------------------------
BEGIN;
INSERT INTO `merchant_reconciliation` (`id`, `user_id`, `total_duration`, `total_amount`, `total_discount_amount`, `total_income_amount`, `year_number`, `month_number`, `status`) VALUES ('1', '1888862836990537730', '100', '3000', '20', '2800', '2025', '04', '未发放');
COMMIT;

-- ----------------------------
-- Table structure for monthly_insurance_payment
-- ----------------------------
DROP TABLE IF EXISTS `monthly_insurance_payment`;
CREATE TABLE `monthly_insurance_payment` (
                                             `id` varchar(32) NOT NULL COMMENT 'ID',
                                             `mainland_license_plates` varchar(500) NOT NULL COMMENT '内地车牌号码',
                                             `monthly_start_time` datetime DEFAULT NULL COMMENT '月保开始时间',
                                             `monthly_end_time` datetime DEFAULT NULL COMMENT '月保结束时间',
                                             `car_type_code` varchar(32) DEFAULT NULL COMMENT '车辆类型编码',
                                             `car_type_name` varchar(32) DEFAULT NULL COMMENT '车辆类型名称',
                                             `user_name` varchar(500) DEFAULT NULL COMMENT '姓名',
                                             `phone_number` varchar(13) DEFAULT NULL COMMENT '手机号码',
                                             `card_id` varchar(50) DEFAULT NULL COMMENT '身份证号码',
                                             `parking_lot_code` varchar(13) DEFAULT NULL COMMENT '车位编号',
                                             `monthly_free` varchar(35) DEFAULT NULL COMMENT '月保费用/月',
                                             `payment_status` varchar(32) DEFAULT NULL COMMENT '缴费状态',
                                             `payment_amount` varchar(32) DEFAULT NULL COMMENT '缴费金额',
                                             `accumulate_payment_amount` varchar(32) DEFAULT NULL COMMENT '累计缴费金额',
                                             `long_term` int NOT NULL DEFAULT '0' COMMENT '是否长期有效，0|否;1|是,默认否',
                                             `create_time` datetime NOT NULL COMMENT '创建时间',
                                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='月保-缴费记录';

-- ----------------------------
-- Records of monthly_insurance_payment
-- ----------------------------
BEGIN;
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1009735868877841792', '粤D53263', '2025-01-30 09:00:00', '2025-09-25 09:00:00', '3', '外部车辆(非机械车位)', '许杰宏', '16043674661', '4416200007030000', '6MKjrGjdgA', '1500', '0', '641', '3994', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1021606871128544640', '粤F51181', '2025-01-02 09:00:00', '2026-10-28 09:00:00', '3', '外部车辆(非机械车位)', '吕秀英', '14428302977', '4407200307210000', '9hB858FM3G', '1500', '0', '143', '3018', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1024316764713353728', '粤A59795', '2025-02-13 09:00:00', '2025-07-19 09:00:00', '3', '外部车辆(非机械车位)', '余子异', '18374880142', '4400200606030000', 'OF0h0s5IHN', '1500', '0', '77', '3870', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1027107267057516032', '粤X28626', '2025-01-11 09:00:00', '2026-01-21 09:00:00', '3', '外部车辆(非机械车位)', '刘秀英', '19126262818', '4417200106100000', 'vX5uxpzA9F', '1500', '0', '344', '2415', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1035996662182432128', '粤K94238', '2025-03-11 09:00:00', '2026-10-03 09:00:00', '3', '外部车辆(非机械车位)', '向詩涵', '13721335142', '4407200300150000', '6B6uSEBVbT', '1500', '0', '930', '5947', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1044886999018993280', '粤Z87877', '2025-02-04 09:00:00', '2025-11-02 09:00:00', '3', '外部车辆(非机械车位)', '龚震南', '16224695014', '4413200504100000', 'TuV2n40b4a', '1500', '0', '156', '1148', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1048265344200605184', '粤N84680', '2025-04-06 09:00:00', '2026-02-01 09:00:00', '3', '外部车辆(非机械车位)', '雷嘉伦', '18168829518', '4404200803210000', 'LlObh4Pylg', '1500', '0', '670', '1122', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1056390359823665920', '粤H96675', '2025-03-18 09:00:00', '2025-12-31 09:00:00', '3', '外部车辆(非机械车位)', '沈璐', '19801612008', '4418200107120000', 'Sav6x3N9w8', '1500', '0', '709', '8214', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1062062586401534848', '粤W43616', '2025-04-07 09:00:00', '2026-04-04 09:00:00', '3', '外部车辆(非机械车位)', '莫子异', '18852521540', '4410200905130000', 'OP4ldpCC1z', '1500', '0', '541', '7467', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1063647224099694464', '粤W58320', '2025-01-28 09:00:00', '2026-12-20 09:00:00', '3', '外部车辆(非机械车位)', '徐云熙', '18441172101', '4419200906240000', 'V6YxQYtPj7', '1500', '0', '237', '6217', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1075262209573697408', '粤B57259', '2025-02-06 09:00:00', '2026-05-05 09:00:00', '3', '外部车辆(非机械车位)', '孙詩涵', '16918865057', '4412200106270000', 'D4PwRuGXaE', '1500', '0', '351', '4104', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1087456009995507328', '粤C76053', '2025-02-17 09:00:00', '2025-11-02 09:00:00', '3', '外部车辆(非机械车位)', '傅子韬', '16854196102', '4416200402260000', 'kQ10PWKU2w', '1500', '0', '944', '6652', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1089622226036842368', '粤C24605', '2025-01-21 09:00:00', '2026-01-08 09:00:00', '3', '外部车辆(非机械车位)', '邓云熙', '14144458081', '4410200909070000', 'SkfbSdzWrv', '1500', '0', '381', '3008', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1093967018432689152', '粤M95227', '2025-02-12 09:00:00', '2026-01-04 09:00:00', '3', '外部车辆(非机械车位)', '曾杰宏', '15417073425', '4410200700120000', 'rmChrq5vN3', '1500', '0', '145', '2198', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1100074050605244416', '粤R88432', '2025-01-26 09:00:00', '2026-04-13 09:00:00', '3', '外部车辆(非机械车位)', '苏震南', '18563448838', '4412200707280000', 'dJRSvlWDCE', '1500', '0', '162', '1504', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1106418790705716352', '粤Y38022', '2025-02-23 09:00:00', '2025-07-23 09:00:00', '3', '外部车辆(非机械车位)', '卢嘉伦', '15540691584', '4411200104020000', 'YWND8uRvmE', '1500', '0', '833', '1123', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1106922127588844416', '粤B86373', '2025-03-26 09:00:00', '2026-10-24 09:00:00', '3', '外部车辆(非机械车位)', '魏安琪', '17528064839', '4411200600240000', 'gWjNH3HCAN', '1500', '0', '122', '7642', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1109947760036169728', '粤J01093', '2025-01-30 09:00:00', '2027-04-01 09:00:00', '3', '外部车辆(非机械车位)', '夏震南', '18477629633', '4416200203080000', 'E1sD0fkPHb', '1500', '0', '473', '3716', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1113685347247354624', '粤C60948', '2025-03-21 09:00:00', '2027-01-17 09:00:00', '3', '外部车辆(非机械车位)', '徐睿', '16862088067', '4406200507000000', 'Z90hj4PZPe', '1500', '0', '58', '9233', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1120987870766773248', '粤J51026', '2025-02-12 09:00:00', '2026-05-30 09:00:00', '3', '外部车辆(非机械车位)', '顾晓明', '15380109003', '4416200502160000', 'MZVCZhBejz', '1500', '0', '925', '9374', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1127304994390510848', '粤H89774', '2025-02-17 09:00:00', '2027-03-19 09:00:00', '3', '外部车辆(非机械车位)', '方震南', '13508965479', '4410200908140000', 'WpppOQOEwe', '1500', '0', '885', '3709', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1142408857821405184', '粤O02151', '2025-02-15 09:00:00', '2025-12-02 09:00:00', '3', '外部车辆(非机械车位)', '曾嘉伦', '15940925890', '4417200500010000', '8a99uwqFvU', '1500', '0', '105', '3284', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1170022613371979264', '粤I62136', '2025-02-03 09:00:00', '2027-01-30 09:00:00', '3', '外部车辆(非机械车位)', '刘子异', '18987502290', '4415200400020000', 'JGD0DxDZmo', '1500', '0', '61', '2233', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1175955420913913088', '粤K88430', '2025-03-24 09:00:00', '2025-10-15 09:00:00', '3', '外部车辆(非机械车位)', '方子韬', '16851455387', '4401200306240000', 'UtMBBMjHJK', '1500', '0', '189', '2645', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1182064501032394752', '粤W14794', '2025-01-19 09:00:00', '2026-12-18 09:00:00', '3', '外部车辆(非机械车位)', '叶秀英', '13694919516', '4417200600270000', 'aLFxgjpqT3', '1500', '0', '40', '7073', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1193905828946770688', '粤D66606', '2025-01-05 09:00:00', '2026-09-04 09:00:00', '3', '外部车辆(非机械车位)', '邵詩涵', '19319337415', '4418200407210000', 'RraeRo9tQ2', '1500', '0', '582', '2221', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1214630050912900352', '粤E70618', '2025-02-25 09:00:00', '2026-07-04 09:00:00', '3', '外部车辆(非机械车位)', '金杰宏', '18103353543', '4401200304130000', 'T7Z4IXldNo', '1500', '0', '981', '5009', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1220446461526371072', '粤D85219', '2025-03-31 09:00:00', '2026-10-15 09:00:00', '3', '外部车辆(非机械车位)', '谭子韬', '15121561285', '4417200903260000', 'X8BIUE8PUY', '1500', '0', '324', '9433', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1222964190828681472', '粤Z67445', '2025-04-01 09:00:00', '2026-08-25 09:00:00', '3', '外部车辆(非机械车位)', '郭子异', '16750175166', '4417200307150000', 'R2l9wKqUBt', '1500', '0', '343', '1702', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1238026961128989696', '粤U66103', '2025-03-31 09:00:00', '2025-08-04 09:00:00', '3', '外部车辆(非机械车位)', '唐云熙', '17056646821', '4415200508250000', '1v9SgpCuIR', '1500', '0', '282', '2559', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1239589934900859648', '粤K64584', '2025-03-18 09:00:00', '2026-07-10 09:00:00', '3', '外部车辆(非机械车位)', '罗晓明', '14343949457', '4415200407040000', 'kT0Z2h9Vsl', '1500', '0', '673', '4622', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1254391620212160512', '粤V18465', '2025-01-28 09:00:00', '2027-04-03 09:00:00', '3', '外部车辆(非机械车位)', '彭安琪', '15358026412', '4416200309040000', '9ypwJnaG5A', '1500', '0', '14', '3629', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1274063891253382912', '粤P74890', '2025-04-16 09:00:00', '2025-07-14 09:00:00', '3', '外部车辆(非机械车位)', '郝睿', '14385092152', '4409200702110000', 'LglZhpwspR', '1500', '0', '231', '8817', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1321787832499023872', '粤F90837', '2025-03-01 09:00:00', '2027-01-31 09:00:00', '3', '外部车辆(非机械车位)', '莫晓明', '13987319690', '4411200903180000', 'T4AELnhUI7', '1500', '0', '11', '9609', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1328413416882350080', '粤P40068', '2025-03-18 09:00:00', '2025-09-18 09:00:00', '3', '外部车辆(非机械车位)', '马詩涵', '16472252398', '4410200906280000', 'NWYdZBKDdR', '1500', '0', '968', '6121', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1340551718097004544', '粤D70874', '2025-02-12 09:00:00', '2026-05-11 09:00:00', '3', '外部车辆(非机械车位)', '钱子异', '18215745080', '4410200801030000', '5ydGJKL6uq', '1500', '0', '600', '9079', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1349087647409351424', '粤O76647', '2025-03-02 09:00:00', '2026-12-29 09:00:00', '3', '外部车辆(非机械车位)', '叶云熙', '14895626573', '4413200505020000', 'i4Ki5QRcg5', '1500', '0', '322', '7473', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1349325207898900480', '粤N42155', '2025-04-07 09:00:00', '2026-02-12 09:00:00', '3', '外部车辆(非机械车位)', '毛安琪', '16559515884', '4412200002280000', 'zmMzoZ4gWz', '1500', '0', '8', '7249', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1353652341282473728', '粤B33403', '2025-04-08 09:00:00', '2025-07-17 09:00:00', '3', '外部车辆(非机械车位)', '邵子韬', '16419882784', '4418200104030000', 'YDmYBxrxDw', '1500', '0', '326', '1128', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1378462745446794752', '粤D82829', '2025-04-06 09:00:00', '2026-10-10 09:00:00', '3', '外部车辆(非机械车位)', '王璐', '18388083159', '4404200406260000', 'zCXPz2SxGX', '1500', '0', '326', '7761', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1381212601910311680', '粤X28152', '2025-01-27 09:00:00', '2026-05-19 09:00:00', '3', '外部车辆(非机械车位)', '黄詩涵', '13420724067', '4417200201110000', 'OUzZ53vzLs', '1500', '0', '882', '7167', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1404749972145287936', '粤V55742', '2025-02-20 09:00:00', '2026-05-31 09:00:00', '3', '外部车辆(非机械车位)', '唐晓明', '16641034357', '4410200608120000', 'VHhKYzdnX7', '1500', '0', '665', '4864', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1412433605164501504', '粤L94849', '2025-01-10 09:00:00', '2026-03-10 09:00:00', '3', '外部车辆(非机械车位)', '唐致远', '19419883725', '4409200902120000', 'bjiZFvPHSv', '1500', '0', '575', '7083', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1413266859652961280', '粤V22427', '2025-04-15 09:00:00', '2025-09-08 09:00:00', '3', '外部车辆(非机械车位)', '段璐', '19135885373', '4402200905250000', 'XlPVNhfQRf', '1500', '0', '683', '2659', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1430170965683942912', '粤D03062', '2025-02-21 09:00:00', '2026-06-11 09:00:00', '3', '外部车辆(非机械车位)', '孟宇宁', '17944506231', '4414200203010000', 'Z4GxIxTj7j', '1500', '0', '88', '6177', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1436938422981807360', '粤S35737', '2025-03-02 09:00:00', '2026-04-11 09:00:00', '3', '外部车辆(非机械车位)', '汪杰宏', '17370860593', '4408200008020000', 'pVmYwiDkXJ', '1500', '0', '697', '8543', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1441229961914582272', '粤E60440', '2025-02-18 09:00:00', '2026-09-19 09:00:00', '3', '外部车辆(非机械车位)', '崔致远', '15857646965', '4408200307170000', 'QEet2yRDas', '1500', '0', '541', '2553', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1446058410712831232', '粤L76973', '2025-01-11 09:00:00', '2025-07-16 09:00:00', '3', '外部车辆(非机械车位)', '汪璐', '19147608454', '4413200705140000', 'JqCsyydmT1', '1500', '0', '204', '3248', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1453182138337730816', '粤E14756', '2025-03-16 09:00:00', '2025-12-12 09:00:00', '3', '外部车辆(非机械车位)', '郑杰宏', '17231799752', '4408200907260000', 'fTzpfMqmQ8', '1500', '0', '382', '4130', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1455517011987178496', '粤S86013', '2025-01-20 09:00:00', '2026-04-02 09:00:00', '3', '外部车辆(非机械车位)', '谭云熙', '15989758048', '4419200907180000', '5MrjNtj2gX', '1500', '0', '249', '7530', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1463133765763203328', '粤X87424', '2025-01-08 09:00:00', '2026-04-11 09:00:00', '3', '外部车辆(非机械车位)', '吕安琪', '14194763265', '4418200509140000', 'nm1sXJNGhM', '1500', '0', '24', '7865', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1480597373132864256', '粤B43327', '2025-02-25 09:00:00', '2026-01-09 09:00:00', '3', '外部车辆(非机械车位)', '石云熙', '17426833827', '4405200101280000', 'uwV0OM6E9q', '1500', '0', '191', '5503', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1488751080693553920', '粤O53003', '2025-01-22 09:00:00', '2027-03-01 09:00:00', '3', '外部车辆(非机械车位)', '邓秀英', '14226287467', '4410200301080000', 'bruccSFP0A', '1500', '0', '546', '6071', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1491922028568471808', '粤W18192', '2025-04-01 09:00:00', '2026-12-05 09:00:00', '3', '外部车辆(非机械车位)', '郭子韬', '13349565467', '4403200004020000', 'vFPEdgvNt1', '1500', '0', '441', '4084', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1494148552482226688', '粤U83352', '2025-03-01 09:00:00', '2026-12-21 09:00:00', '3', '外部车辆(非机械车位)', '沈岚', '19995264300', '4414200308240000', 'L30S37pB36', '1500', '0', '182', '8661', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1498393094589756672', '粤B83813', '2025-02-23 09:00:00', '2026-08-13 09:00:00', '3', '外部车辆(非机械车位)', '夏宇宁', '19342479626', '4416200302140000', '5CWFjez4FE', '1500', '0', '656', '7318', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1514347352171266816', '粤D00625', '2025-02-10 09:00:00', '2025-09-19 09:00:00', '3', '外部车辆(非机械车位)', '陆岚', '17780471998', '4404200404280000', 'rkQPNBGUqR', '1500', '0', '173', '1093', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1527621833306653696', '粤F05675', '2025-04-07 09:00:00', '2026-11-08 09:00:00', '3', '外部车辆(非机械车位)', '田子异', '13310116233', '4401200501130000', 'E3W1tZqWHO', '1500', '0', '673', '8741', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1540381732879842816', '粤A90211', '2025-01-13 09:00:00', '2026-01-15 09:00:00', '3', '外部车辆(非机械车位)', '高睿', '15209415719', '4410200200220000', 'HECUby5SbD', '1500', '0', '407', '7032', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1545628086098076928', '粤P13838', '2025-02-26 09:00:00', '2027-02-08 09:00:00', '3', '外部车辆(非机械车位)', '何宇宁', '15893637227', '4414200307150000', '8kwAsIp1cm', '1500', '0', '581', '8086', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1546221846893467904', '粤T79662', '2025-01-12 09:00:00', '2026-04-16 09:00:00', '3', '外部车辆(非机械车位)', '薛云熙', '13551955622', '4414200608080000', 'PSxFjO0yZ8', '1500', '0', '970', '6553', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1559551688419452928', '粤M41147', '2025-01-25 09:00:00', '2025-09-11 09:00:00', '3', '外部车辆(非机械车位)', '方嘉伦', '16382178921', '4409200209060000', 'ySWunQ1gYH', '1500', '0', '806', '1548', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1565649003273055232', '粤O37802', '2025-03-25 09:00:00', '2026-03-25 09:00:00', '3', '外部车辆(非机械车位)', '严璐', '17610709211', '4419200408110000', 'E6EwjEcTr8', '1500', '0', '162', '9506', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1569932582462061056', '粤R40166', '2025-01-02 09:00:00', '2025-11-26 09:00:00', '3', '外部车辆(非机械车位)', '谢睿', '19843139771', '4401200903250000', 'GEDKsig5q3', '1500', '0', '983', '6636', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1580282524665925120', '粤I71627', '2025-01-10 09:00:00', '2026-05-06 09:00:00', '3', '外部车辆(非机械车位)', '孟璐', '14364749943', '4417200803100000', 'dNaXdQGgPG', '1500', '0', '842', '1960', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1581343912870383104', '粤D54767', '2025-04-11 09:00:00', '2026-02-03 09:00:00', '3', '外部车辆(非机械车位)', '曹宇宁', '18027026474', '4408200201270000', 'R7tQco0MTs', '1500', '0', '15', '5348', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1605177343438203392', '粤P83030', '2025-02-17 09:00:00', '2025-09-26 09:00:00', '3', '外部车辆(非机械车位)', '严子韬', '18748792211', '4411200306040000', 'oy0KUCCmkt', '1500', '0', '327', '1995', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1614929573681143296', '粤Y47742', '2025-03-17 09:00:00', '2025-09-18 09:00:00', '3', '外部车辆(非机械车位)', '石詩涵', '14390448487', '4406200501060000', '0oo8TlWJbq', '1500', '0', '149', '2641', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1636171850312437248', '粤N99795', '2025-01-23 09:00:00', '2026-07-11 09:00:00', '3', '外部车辆(非机械车位)', '戴晓明', '19876785171', '4411200100160000', 'MthbqB9Ved', '1500', '0', '562', '1561', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1642497109157742080', '粤M23783', '2025-03-03 09:00:00', '2026-04-30 09:00:00', '3', '外部车辆(非机械车位)', '余云熙', '16515942700', '4414200406280000', '3LWF73AAze', '1500', '0', '47', '7154', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1663861378788733184', '粤K38236', '2025-02-01 09:00:00', '2026-08-19 09:00:00', '3', '外部车辆(非机械车位)', '姜璐', '13486795657', '4403200802260000', 'UNPyOYs8QK', '1500', '0', '0', '2080', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1664506548470238208', '粤H20183', '2025-01-20 09:00:00', '2026-03-23 09:00:00', '3', '外部车辆(非机械车位)', '林岚', '16009766450', '4402200905210000', 'oBS6LxaNYv', '1500', '0', '61', '8239', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1664708493113706496', '粤P43420', '2025-04-15 09:00:00', '2027-01-06 09:00:00', '3', '外部车辆(非机械车位)', '孟岚', '17649975902', '4405200908230000', 'VixWBVmlUt', '1500', '0', '604', '7729', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1669474772429749248', '粤U62698', '2025-02-02 09:00:00', '2027-04-14 09:00:00', '3', '外部车辆(非机械车位)', '邵晓明', '13449570843', '4413200900000000', 'YiasTYIWEg', '1500', '0', '565', '5204', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1682919548908278272', '粤P67575', '2025-02-21 09:00:00', '2026-10-24 09:00:00', '3', '外部车辆(非机械车位)', '徐杰宏', '16276405505', '4409200208240000', 'bJLjVztoDQ', '1500', '0', '713', '3706', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1717559513833741312', '粤D02842', '2025-03-30 09:00:00', '2026-10-30 09:00:00', '3', '外部车辆(非机械车位)', '严子异', '19205852987', '4412200206240000', 'RvuXzgyvUd', '1500', '0', '343', '4883', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1745493164370402816', '粤P76574', '2025-03-16 09:00:00', '2026-12-22 09:00:00', '3', '外部车辆(非机械车位)', '顾子韬', '16206576447', '4402200905020000', '9GvAmoFFXI', '1500', '0', '708', '5894', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1753607175145260800', '粤I97448', '2025-03-20 09:00:00', '2026-05-29 09:00:00', '3', '外部车辆(非机械车位)', '方杰宏', '17473292131', '4400200700010000', 'LIteYTDSBV', '1500', '0', '387', '2890', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1754110251478895104', '粤M65781', '2025-03-27 09:00:00', '2026-01-22 09:00:00', '3', '外部车辆(非机械车位)', '孙晓明', '15379057338', '4411200401100000', 'jMzfbCKHAp', '1500', '0', '124', '5535', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1791259275590457344', '粤Q60118', '2025-02-26 09:00:00', '2026-08-05 09:00:00', '3', '外部车辆(非机械车位)', '罗震南', '16607096128', '4415200500260000', 'gKAJ4jx7rX', '1500', '0', '376', '4476', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1801922162624174336', '粤A05435', '2025-02-19 09:00:00', '2027-03-14 09:00:00', '3', '外部车辆(非机械车位)', '莫詩涵', '15311610535', '4413200308240000', 'z5pZzJXrE0', '1500', '0', '572', '4363', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1828242586384657664', '粤K46202', '2025-02-15 09:00:00', '2026-10-03 09:00:00', '3', '外部车辆(非机械车位)', '姚云熙', '18101128051', '4406200100200000', 'UpBsQow8YG', '1500', '0', '295', '3114', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1854550583494020608', '粤I03968', '2025-02-25 09:00:00', '2026-09-18 09:00:00', '3', '外部车辆(非机械车位)', '袁宇宁', '17388366841', '4412200804240000', 'Nrb7LosOdM', '1500', '0', '602', '9413', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1862789858949527040', '粤A59718', '2025-02-24 09:00:00', '2025-10-16 09:00:00', '3', '外部车辆(非机械车位)', '邹杰宏', '16525428084', '4403200802100000', 'PB90zwncJZ', '1500', '0', '217', '1132', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1867750109993681920', '粤Y30728', '2025-03-19 09:00:00', '2026-02-09 09:00:00', '3', '外部车辆(非机械车位)', '陆晓明', '13556442104', '4415200305040000', 'yVHzsfsX2S', '1500', '0', '70', '7334', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1868447312688567040', '粤W98373', '2025-02-06 09:00:00', '2026-02-16 09:00:00', '3', '外部车辆(非机械车位)', '蔡晓明', '18985439415', '4411200408140000', '0Fi9vHgrKT', '1500', '0', '646', '9489', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1876443634063277312', '粤N94345', '2025-03-29 09:00:00', '2026-11-21 09:00:00', '3', '外部车辆(非机械车位)', '苏子韬', '15088031917', '4416200303280000', 'Migimr3LXR', '1500', '0', '498', '3948', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1881982377631910144', '粤R65911', '2025-04-13 09:00:00', '2025-08-13 09:00:00', '3', '外部车辆(非机械车位)', '黎杰宏', '18658317553', '4410200000140000', 'apxS1kqXGM', '1500', '0', '6', '1192', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1901138775220699138', '粤A123456', '2025-04-16 15:25:04', '2026-12-31 15:25:04', '3', '外部车辆(机械车位)', '王五', '13413413411', '440900200001010011', '', '1000', '0', '20405.00', '20405.00', 0, '2025-04-16 15:25:04');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1920112779393758464', '粤G85872', '2025-01-15 09:00:00', '2026-07-07 09:00:00', '3', '外部车辆(非机械车位)', '孔宇宁', '16769023640', '4401200003050000', 'VVHMOAF0F5', '1500', '0', '976', '6582', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1940374154236019968', '粤O38250', '2025-01-30 09:00:00', '2026-07-29 09:00:00', '3', '外部车辆(非机械车位)', '廖嘉伦', '19167039456', '4400200106170000', 'thE6mvW8Es', '1500', '0', '991', '1089', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1945919218356928512', '粤U03256', '2025-03-04 09:00:00', '2027-02-18 09:00:00', '3', '外部车辆(非机械车位)', '金晓明', '16940478370', '4410200507260000', 'RdbcPgPIIF', '1500', '0', '29', '3031', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1948127993207077632', '粤M17228', '2025-03-17 09:00:00', '2026-06-06 09:00:00', '3', '外部车辆(非机械车位)', '武致远', '18097440820', '4404200108150000', 'ZuhsJ1Lt8X', '1500', '0', '286', '4438', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1955388471509257472', '粤E18089', '2025-01-27 09:00:00', '2026-05-04 09:00:00', '3', '外部车辆(非机械车位)', '韩秀英', '18669674258', '4407200108210000', 'j3vuyKEHqf', '1500', '0', '47', '9505', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1956637342186317056', '粤X92984', '2025-01-22 09:00:00', '2026-01-01 09:00:00', '3', '外部车辆(非机械车位)', '汪子异', '19565692351', '4402200001000000', 'dlLRG2CnZo', '1500', '0', '908', '2327', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1961857109045736960', '粤U37445', '2025-04-06 09:00:00', '2025-08-11 09:00:00', '3', '外部车辆(非机械车位)', '贾岚', '19654455924', '4402200604280000', 'KaY8StrT1X', '1500', '0', '367', '2710', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1968692633115542784', '粤V29685', '2025-02-10 09:00:00', '2026-06-04 09:00:00', '3', '外部车辆(非机械车位)', '武璐', '13520116291', '4407200602200000', '6y03oamvax', '1500', '0', '747', '2532', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1969832284980337408', '粤B50439', '2025-03-03 09:00:00', '2026-07-23 09:00:00', '3', '外部车辆(非机械车位)', '马睿', '14904141769', '4416200302230000', 'sJBt57P16S', '1500', '0', '96', '7568', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1975203572228007424', '粤Y07243', '2025-01-21 09:00:00', '2026-11-05 09:00:00', '3', '外部车辆(非机械车位)', '雷岚', '15289463820', '4405200006210000', 'NDUXGPNVld', '1500', '0', '635', '1587', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1987419939643601920', '粤J86455', '2025-02-25 09:00:00', '2025-07-24 09:00:00', '3', '外部车辆(非机械车位)', '尹震南', '17284318180', '4405200902040000', 'G5H38kvqWo', '1500', '0', '931', '6519', 0, '2025-04-16 09:00:00');
INSERT INTO `monthly_insurance_payment` (`id`, `mainland_license_plates`, `monthly_start_time`, `monthly_end_time`, `car_type_code`, `car_type_name`, `user_name`, `phone_number`, `card_id`, `parking_lot_code`, `monthly_free`, `payment_status`, `payment_amount`, `accumulate_payment_amount`, `long_term`, `create_time`) VALUES ('1994948143707741952', '粤X84802', '2025-02-25 09:00:00', '2026-03-16 09:00:00', '3', '外部车辆(非机械车位)', '潘詩涵', '15812417602', '4404200703120000', 'ZRCtTbCLQA', '1500', '0', '871', '2694', 0, '2025-04-16 09:00:00');
COMMIT;

-- ----------------------------
-- Table structure for order_paid_cat_outbound
-- ----------------------------
DROP TABLE IF EXISTS `order_paid_cat_outbound`;
CREATE TABLE `order_paid_cat_outbound` (
                                           `id` varchar(32) NOT NULL COMMENT 'ID',
                                           `user_id` varchar(255) NOT NULL COMMENT '用户ID',
                                           `mainland_license_plates` varchar(255) NOT NULL COMMENT '车牌号/手机号码(无牌车)',
                                           `start_time` datetime NOT NULL COMMENT '进场时间',
                                           `end_time` datetime NOT NULL COMMENT '出场时间',
                                           `total_duration` varchar(255) NOT NULL COMMENT '停车时长',
                                           `play_time` datetime NOT NULL COMMENT '订单支付时间',
                                           `order_number` varchar(255) NOT NULL COMMENT '订单编号',
                                           `total_amount` varchar(255) NOT NULL COMMENT '总计金额',
                                           `total_discount_amount` varchar(255) NOT NULL COMMENT '优惠金额',
                                           `discount` varchar(255) NOT NULL COMMENT '优惠信息',
                                           `total_income_amount` varchar(255) NOT NULL COMMENT '收入金额',
                                           `type_code` varchar(255) NOT NULL COMMENT '类型编码',
                                           `type_name` varchar(255) NOT NULL COMMENT '类型名称',
                                           `play_status` varchar(32) NOT NULL COMMENT '支付状态',
                                           `advance_payment` int NOT NULL DEFAULT '0' COMMENT '是否提前支付，0｜否；1｜是，默认为0',
                                           `create_time` datetime NOT NULL COMMENT '订单创建时间',
                                           `device_ip` varchar(35) DEFAULT NULL COMMENT '出口设备IP',
                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单-车辆出库已支付订单记录';

-- ----------------------------
-- Records of order_paid_cat_outbound
-- ----------------------------
BEGIN;
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('1', '1', '粤A02843', '2025-04-16 15:55:25', '2025-04-16 15:55:28', '60', '2025-04-16 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('10', '1', '粤A75319', '2025-04-07 15:55:25', '2025-04-16 15:55:28', '60', '2025-04-07 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('100', '1888862836990537743', '粤A0254D', '2025-03-16 15:55:25', '2025-03-17 15:55:28', '60', '2025-03-17 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-03-17 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('11', '1', '粤A15973', '2025-04-06 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-06 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('12', '1', '粤A24680', '2025-04-05 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-05 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('13', '1', '粤A98765', '2025-04-04 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-04 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('14', '1', '粤A13579', '2025-04-03 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-03 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('1914327410539532289', '1914251510133309442', '粤A12340', '2025-04-21 17:35:00', '2025-04-21 22:36:36', '5小时1分钟', '2025-04-22 00:35:58', 'ORDER_1914327410338332672', '60.00', '10.00', '免费停车15分钟', '50.00', '1', '临保', '支付成功', 0, '2025-04-21 22:36:36', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('2', '1', '粤A45367', '2025-04-15 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-15 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('3', '1', '粤A12345', '2025-04-14 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-14 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('4', '1', '粤A67890', '2025-04-13 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-13 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('5', '1', '粤A54321', '2025-04-12 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-12 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('6', '1', '粤A98765', '2025-04-11 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-11 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('7', '1', '粤A13579', '2025-04-10 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-10 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('8', '1', '粤A24680', '2025-04-09 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-09 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('9', '1', '粤A86420', '2025-04-08 15:55:25', '2025-04-17 15:55:28', '60', '2025-04-08 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-04-16 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('93', '1888862836990537743', '粤A26563', '2025-03-09 15:55:25', '2025-03-10 15:55:28', '60', '2025-03-10 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-03-10 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('94', '1888862836990537743', '粤A45632', '2025-03-10 15:55:25', '2025-03-11 15:55:28', '60', '2025-03-11 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-03-11 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('95', '1888862836990537743', '粤A2143A', '2025-03-11 15:55:25', '2025-03-12 15:55:28', '60', '2025-03-12 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-03-12 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('96', '1888862836990537743', '粤A1D14A', '2025-03-12 15:55:25', '2025-03-13 15:55:28', '60', '2025-03-13 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-03-13 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('97', '1888862836990537743', '粤A1304A', '2025-03-13 15:55:25', '2025-03-14 15:55:28', '60', '2025-03-14 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-03-14 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('98', '1888862836990537743', '粤A1D14F', '2025-03-14 15:55:25', '2025-03-15 15:55:28', '60', '2025-03-15 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-03-15 15:57:26', NULL);
INSERT INTO `order_paid_cat_outbound` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `play_time`, `order_number`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `type_code`, `type_name`, `play_status`, `advance_payment`, `create_time`, `device_ip`) VALUES ('99', '1888862836990537730', '粤A0214F', '2025-03-15 15:55:25', '2025-03-16 15:55:28', '60', '2025-03-16 15:55:50', '100', '240', '24', '10', '216', '1', '临保', '支付成功', 1, '2025-03-16 15:57:26', NULL);
COMMIT;

-- ----------------------------
-- Table structure for park_collect_coupons
-- ----------------------------
DROP TABLE IF EXISTS `park_collect_coupons`;
CREATE TABLE `park_collect_coupons` (
                                        `id` varchar(32) NOT NULL COMMENT 'ID',
                                        `user_id` varchar(255) NOT NULL COMMENT '商户ID',
                                        `mainland_license_plates` varchar(255) NOT NULL COMMENT '车牌号/手机号码(无牌车)',
                                        `start_time` datetime DEFAULT NULL COMMENT '进场时间',
                                        `end_time` datetime DEFAULT NULL COMMENT '出场时间',
                                        `total_duration` varchar(255) DEFAULT NULL COMMENT '停车时长',
                                        `total_amount` varchar(255) DEFAULT NULL COMMENT '总计金额',
                                        `total_discount_amount` varchar(255) DEFAULT NULL COMMENT '优惠金额',
                                        `discount` varchar(255) DEFAULT NULL COMMENT '优惠信息',
                                        `total_income_amount` varchar(255) DEFAULT NULL COMMENT '需付金额',
                                        `receive_time` datetime DEFAULT NULL COMMENT '领劵时间',
                                        `is_it_over` int DEFAULT NULL COMMENT '是否已出场,0|未出场；1|已出场',
                                        `is_play` int NOT NULL DEFAULT '0' COMMENT '订单是否已支付,0|未支付；1|已支付',
                                        `play_time` datetime DEFAULT NULL COMMENT '订单支付成功时间',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商户-停车领劵';

-- ----------------------------
-- Records of park_collect_coupons
-- ----------------------------
BEGIN;
INSERT INTO `park_collect_coupons` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `receive_time`, `is_it_over`, `is_play`, `play_time`) VALUES ('1912881101823578114', '1888862836990537730', '粤A02843', '2025-04-16 15:55:25', '2025-04-17 22:49:29', '1天6小时54分钟', '310.00', '2.50', '免费0.25小时', '307.50', '2025-04-17 22:49:29', 0, 0, NULL);
INSERT INTO `park_collect_coupons` (`id`, `user_id`, `mainland_license_plates`, `start_time`, `end_time`, `total_duration`, `total_amount`, `total_discount_amount`, `discount`, `total_income_amount`, `receive_time`, `is_it_over`, `is_play`, `play_time`) VALUES ('1914251541968076802', '1888862836990537730', '粤A12340', '2025-04-21 17:35:00', '2025-04-21 17:35:07', '1分钟', '0.00', '2.50', '免费0.25小时', '0', '2025-04-21 17:35:07', 0, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for record_car_enter
-- ----------------------------
DROP TABLE IF EXISTS `record_car_enter`;
CREATE TABLE `record_car_enter` (
                                    `id` varchar(32) NOT NULL COMMENT 'ID',
                                    `mainland_license_plates` varchar(255) NOT NULL COMMENT '内地车牌号码',
                                    `phone_number` varchar(13) DEFAULT NULL COMMENT '手机号码',
                                    `other_license_plates` varchar(255) DEFAULT NULL COMMENT '其他车牌号码，多个车牌号码英文逗号分隔',
                                    `start_camera_device_ip` varchar(32) NOT NULL COMMENT '进库摄像头IP',
                                    `start_time` datetime NOT NULL COMMENT '进库时间',
                                    `device_group_id` int NOT NULL COMMENT '设备组号编码，1｜主库；2｜东库；3｜西库',
                                    `device_group_name` varchar(32) NOT NULL COMMENT '设备组号名称',
                                    `car_group_id` int NOT NULL COMMENT '车辆分组编码，1｜临停车；2｜VIP月租车；3｜普通月租车；4｜访客车；5|无牌车；6｜其他',
                                    `car_group_name` varchar(32) NOT NULL COMMENT '车辆分组名称',
                                    `is_toll` tinyint(1) NOT NULL DEFAULT '0' COMMENT '此处是否开始计费，0｜不计费; 1｜开始计费;默认不计费',
                                    `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单是否结束，0｜未结束；1｜已结束,默认未结束',
                                    PRIMARY KEY (`id`),
                                    KEY `licenseplatenum` (`mainland_license_plates`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录-车辆进场记录表';

-- ----------------------------
-- Records of record_car_enter
-- ----------------------------
BEGIN;
INSERT INTO `record_car_enter` (`id`, `mainland_license_plates`, `phone_number`, `other_license_plates`, `start_camera_device_ip`, `start_time`, `device_group_id`, `device_group_name`, `car_group_id`, `car_group_name`, `is_toll`, `status`) VALUES ('1', '粤A02843', NULL, NULL, '192.168.100.1', '2025-04-16 15:55:25', 1, '地下二层', 1, '临牌车', 0, 1);
INSERT INTO `record_car_enter` (`id`, `mainland_license_plates`, `phone_number`, `other_license_plates`, `start_camera_device_ip`, `start_time`, `device_group_id`, `device_group_name`, `car_group_id`, `car_group_name`, `is_toll`, `status`) VALUES ('1914251510133309442', '粤A12340', NULL, NULL, '192.168.100.1', '2025-04-21 17:35:00', 1, '地下二层', 1, '临保', 1, 0);
INSERT INTO `record_car_enter` (`id`, `mainland_license_plates`, `phone_number`, `other_license_plates`, `start_camera_device_ip`, `start_time`, `device_group_id`, `device_group_name`, `car_group_id`, `car_group_name`, `is_toll`, `status`) VALUES ('2', '粤A02843', NULL, NULL, '192.168.100.1', '2025-04-16 15:55:25', 1, '地下二层', 1, '临牌车', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for records_car_outbound
-- ----------------------------
DROP TABLE IF EXISTS `records_car_outbound`;
CREATE TABLE `records_car_outbound` (
                                        `id` varchar(32) NOT NULL COMMENT 'ID',
                                        `mainland_license_plates` varchar(255) NOT NULL COMMENT '内地车牌号码',
                                        `phone_number` varchar(13) DEFAULT NULL COMMENT '手机号码',
                                        `other_license_plates` varchar(255) DEFAULT NULL COMMENT '其他车牌号码，多个车牌号码英文逗号分隔',
                                        `end_camera_device_ip` varchar(32) NOT NULL COMMENT '出库摄像头IP',
                                        `start_time` datetime NOT NULL COMMENT '进场时间',
                                        `end_time` datetime NOT NULL COMMENT '出库时间',
                                        `device_group_id` int NOT NULL COMMENT '设备组号编码，1｜主库；2｜东库；3｜西库',
                                        `device_group_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备组号名称',
                                        `car_group_id` int NOT NULL COMMENT '车辆分组编码，1｜临停车；2｜VIP月租车；3｜普通月租车；4｜访客车；5|无牌车；6｜其他',
                                        `car_group_name` varchar(32) NOT NULL COMMENT '车辆分组名称',
                                        `parking_duration` varchar(100) NOT NULL COMMENT '停车时长',
                                        `is_toll` tinyint(1) NOT NULL DEFAULT '0' COMMENT '此处是否结算计费，0｜不结算; 1｜结算计费;默认不结算',
                                        PRIMARY KEY (`id`),
                                        KEY `mainland_license_plates` (`mainland_license_plates`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录-车辆出场记录表';

-- ----------------------------
-- Records of records_car_outbound
-- ----------------------------
BEGIN;
INSERT INTO `records_car_outbound` (`id`, `mainland_license_plates`, `phone_number`, `other_license_plates`, `end_camera_device_ip`, `start_time`, `end_time`, `device_group_id`, `device_group_name`, `car_group_id`, `car_group_name`, `parking_duration`, `is_toll`) VALUES ('2', '粤A02843', NULL, NULL, '192.168.100.1', '2025-04-16 15:55:25', '2025-04-17 22:55:22', 1, '地下二层', 1, '临牌车', '1天7小时00分钟', 1);
COMMIT;

-- ----------------------------
-- Table structure for system_camera_device
-- ----------------------------
DROP TABLE IF EXISTS `system_camera_device`;
CREATE TABLE `system_camera_device` (
                                        `id` varchar(32) NOT NULL COMMENT 'ID',
                                        `device_ip` varchar(255) NOT NULL COMMENT '设备IP地址',
                                        `device_port` varchar(255) NOT NULL COMMENT '设备IP端口',
                                        `device_user_name` varchar(255) DEFAULT NULL COMMENT '设备用户名',
                                        `device_password` varchar(255) DEFAULT NULL COMMENT '设备密码',
                                        `device_name` varchar(255) DEFAULT NULL COMMENT '设备名称',
                                        `device_location` varchar(255) DEFAULT NULL COMMENT '设备所在位置',
                                        `device_role` int NOT NULL COMMENT '设备作用，1｜进；0｜出',
                                        `is_toll` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否在此处计费，1｜计费；0｜不计费',
                                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                        `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                        `group_id` int DEFAULT NULL COMMENT '组号，进出为一组，1｜主库；2｜东库；3｜西库',
                                        `user_id` int DEFAULT NULL COMMENT '设备登陆句柄',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-摄像头设备管理表';

-- ----------------------------
-- Records of system_camera_device
-- ----------------------------
BEGIN;
INSERT INTO `system_camera_device` (`id`, `device_ip`, `device_port`, `device_user_name`, `device_password`, `device_name`, `device_location`, `device_role`, `is_toll`, `create_time`, `update_time`, `group_id`, `user_id`) VALUES ('1', '192.168.100.1', '443', 'admin', '123456', '出入库摄像头', '地下二层', 1, 1, '2025-04-17 23:03:51', '2025-04-17 23:03:53', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for system_charge_rules
-- ----------------------------
DROP TABLE IF EXISTS `system_charge_rules`;
CREATE TABLE `system_charge_rules` (
                                       `id` varchar(32) NOT NULL COMMENT 'ID',
                                       `free_duration` int DEFAULT NULL COMMENT '进场免费时长，单位：分钟',
                                       `toll_standard` varchar(255) DEFAULT NULL COMMENT '临保收费标准，单位：元/小时',
                                       `fee_cap` varchar(32) DEFAULT NULL COMMENT '临保收费上限。单位：元/24小时',
                                       `monthly_internal_car` varchar(255) DEFAULT NULL COMMENT '月保收费标准-内部车辆，单位：元/月',
                                       `monthly_enterprise_car` varchar(255) DEFAULT NULL COMMENT '月保收费标准-所属企业公车，单位：元/月',
                                       `monthly_external_car_machinery` varchar(255) DEFAULT NULL COMMENT '月保收费标准-外部车辆(机械车位)，单位：元/月',
                                       `monthly_internal_car_no_machinery` varchar(255) DEFAULT NULL COMMENT '月保收费标准-外部车辆(非机械车位)，单位：元/月',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-收费规则表';

-- ----------------------------
-- Records of system_charge_rules
-- ----------------------------
BEGIN;
INSERT INTO `system_charge_rules` (`id`, `free_duration`, `toll_standard`, `fee_cap`, `monthly_internal_car`, `monthly_enterprise_car`, `monthly_external_car_machinery`, `monthly_internal_car_no_machinery`) VALUES ('1', 15, '10', '0', '0', '0', '1000', '1500');
COMMIT;

-- ----------------------------
-- Table structure for system_parking
-- ----------------------------
DROP TABLE IF EXISTS `system_parking`;
CREATE TABLE `system_parking` (
                                  `id` varchar(32) NOT NULL COMMENT 'ID',
                                  `parking_number` int DEFAULT NULL COMMENT '车位总数量',
                                  `disposable_number` int DEFAULT NULL COMMENT '可分配车位总数量',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-车位数量表';

-- ----------------------------
-- Records of system_parking
-- ----------------------------
BEGIN;
INSERT INTO `system_parking` (`id`, `parking_number`, `disposable_number`) VALUES ('1912457802735972353', 200, 100);
COMMIT;

-- ----------------------------
-- Table structure for system_parking_detail
-- ----------------------------
DROP TABLE IF EXISTS `system_parking_detail`;
CREATE TABLE `system_parking_detail` (
                                         `id` varchar(32) NOT NULL COMMENT 'ID',
                                         `user_id` varchar(32) NOT NULL COMMENT '用户ID',
                                         `assigned_number` int NOT NULL COMMENT '租赁车位数量',
                                         `start_time` datetime DEFAULT NULL COMMENT '开始时间',
                                         `end_time` datetime DEFAULT NULL COMMENT '结束时间',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-车位数量明细表';

-- ----------------------------
-- Records of system_parking_detail
-- ----------------------------
BEGIN;
INSERT INTO `system_parking_detail` (`id`, `user_id`, `assigned_number`, `start_time`, `end_time`) VALUES ('1913050174444310529', '1888862836990537743', 1, '2025-04-17 00:00:00', '2025-05-21 00:00:00');
INSERT INTO `system_parking_detail` (`id`, `user_id`, `assigned_number`, `start_time`, `end_time`) VALUES ('1913132758008336386', '1888862836990537730', 10, '2025-04-01 00:00:00', '2025-05-31 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for system_parking_space
-- ----------------------------
DROP TABLE IF EXISTS `system_parking_space`;
CREATE TABLE `system_parking_space` (
                                        `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                        `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车位名称',
                                        `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '车位类型',
                                        `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '车位父ID',
                                        `is_reserved` tinyint(1) NOT NULL COMMENT '是否预留车位',
                                        `status` int NOT NULL COMMENT '车位状态，0|空闲；1|已预留；2|已占用；3|已禁用;4|已删除',
                                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                        `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                        `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属的设备ID，0为未设置',
                                        `sensor_slot` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '传感器槽位（停用）',
                                        `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                                        `fnum` int DEFAULT NULL COMMENT '3D室内地图楼层编码',
                                        `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '3D室内地图模型名',
                                        PRIMARY KEY (`id`,`device_id`) USING BTREE,
                                        KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-车位表';

-- ----------------------------
-- Records of system_parking_space
-- ----------------------------
BEGIN;
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('1000', '广东技术师范大学', 'AREA', '0', 0, 0, '2025-01-03 17:06:05', '2025-01-03 17:06:21', '0', NULL, NULL, NULL, NULL);
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('1001', 'A区', 'AREA', '1000', 0, 0, '2025-01-03 17:06:05', '2025-01-03 17:06:21', '0', NULL, NULL, NULL, NULL);
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('1002', 'A-035', 'SPACE', '1001', 0, 0, '2025-01-03 17:06:05', '2025-01-03 17:06:21', '10000', '0', NULL, NULL, NULL);
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70083', 'B-001', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-04-20 00:00:00', '100000', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70084', 'B-002', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-04-20 00:00:00', '100001', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70085', 'B-003', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-04-20 00:00:00', '100002', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70086', 'B-004', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-04-20 00:00:00', '100003', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70087', '70087', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100004', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70088', '70088', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100005', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70089', '70089', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100006', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70090', '70090', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100007', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70091', '70091', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100008', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70092', '70092', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100009', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70093', '70093', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100010', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70094', '70094', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100011', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70095', '70095', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100012', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70096', '70096', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100013', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70097', '70097', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100014', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70098', '70098', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100015', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70099', '70099', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100016', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70100', '70100', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100017', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70101', '70101', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100018', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70102', '70102', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100019', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70103', '70103', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100020', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70104', '70104', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100021', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70105', '70105', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100022', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70106', '70106', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100023', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70107', '70107', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100024', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70108', '70108', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100025', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70109', '70109', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100026', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70110', '70110', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100027', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70111', '70111', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100028', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70112', '70112', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100029', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70113', '70113', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100030', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70114', '70114', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100031', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70115', '70115', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100032', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70116', '70116', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100033', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70117', '70117', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100034', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70118', '70118', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100035', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70119', '70119', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100036', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70120', '70120', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100037', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70121', '70121', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100038', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70122', '70122', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100039', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70123', '70123', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100040', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70124', '70124', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100041', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70125', '70125', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100042', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70126', '70126', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100043', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70127', '70127', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100044', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70128', '70128', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100045', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70129', '70129', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100046', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70130', '70130', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100047', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70131', '70131', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100048', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70132', '70132', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100049', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70133', '70133', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100050', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70134', '70134', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100051', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70135', '70135', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100052', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70136', '70136', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100053', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70137', '70137', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100054', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70138', '70138', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100055', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70139', '70139', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100056', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70140', '70140', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100057', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70141', '70141', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100058', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70142', '70142', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100059', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70143', '70143', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100060', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70144', '70144', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100061', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70145', '70145', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100062', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70146', '70146', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100063', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70147', '70147', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100064', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70148', '70148', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100065', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70149', '70149', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100066', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70150', '70150', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100067', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70151', '70151', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100068', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70152', '70152', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100069', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70153', '70153', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100070', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70154', '70154', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100071', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70155', '70155', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100072', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70156', '70156', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100073', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70157', '70157', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100074', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70158', '70158', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100075', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70159', '70159', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100076', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70160', '70160', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100077', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70161', '70161', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100078', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70162', '70162', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-05-16 00:00:00', '1', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70163', '70163', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-05-02 00:00:00', '1000', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70164', '70164', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100081', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70165', '70165', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100082', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70166', '70166', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100083', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70167', '70167', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100084', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70168', '70168', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100085', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70169', '70169', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100086', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70170', '70170', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100087', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70171', '70171', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100088', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70172', '70172', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100089', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70173', '70173', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100090', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70174', '70174', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100091', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70175', '70175', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100092', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70176', '70176', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100093', '0', '', 1, '10005');
INSERT INTO `system_parking_space` (`id`, `name`, `type`, `parent_id`, `is_reserved`, `status`, `create_time`, `update_time`, `device_id`, `sensor_slot`, `comment`, `fnum`, `model_name`) VALUES ('70177', '70177', 'SPACE', '0', 0, 2, '2025-03-15 18:00:00', '2025-03-15 18:00:00', '100094', '0', '', 1, '10005');
COMMIT;

-- ----------------------------
-- Table structure for system_parking_space_reservation_record
-- ----------------------------
DROP TABLE IF EXISTS `system_parking_space_reservation_record`;
CREATE TABLE `system_parking_space_reservation_record` (
                                                           `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                                           `parking_space_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ParkingSpaceId',
                                                           `reservation_start` timestamp NULL DEFAULT NULL COMMENT '预约开始时间',
                                                           `reservation_end` timestamp NULL DEFAULT NULL COMMENT '预约结束时间',
                                                           `reservation_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '预约状态',
                                                           `reservation_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预约用户',
                                                           `created_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                                                           `updated_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                                           PRIMARY KEY (`id`,`parking_space_id`,`reservation_user_id`) USING BTREE,
                                                           UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_parking_space_reservation_record
-- ----------------------------
BEGIN;
INSERT INTO `system_parking_space_reservation_record` (`id`, `parking_space_id`, `reservation_start`, `reservation_end`, `reservation_status`, `reservation_user_id`, `created_at`, `updated_at`) VALUES (1, '70083', '2025-03-26 16:32:30', '2025-03-27 16:32:33', '3', '2', '2025-03-26 16:32:47', '2025-03-28 11:12:10');
INSERT INTO `system_parking_space_reservation_record` (`id`, `parking_space_id`, `reservation_start`, `reservation_end`, `reservation_status`, `reservation_user_id`, `created_at`, `updated_at`) VALUES (2, '70084', '2025-03-26 16:32:33', '2025-04-27 16:32:33', '2', '2', '2025-03-26 18:15:06', '2025-03-26 18:18:08');
INSERT INTO `system_parking_space_reservation_record` (`id`, `parking_space_id`, `reservation_start`, `reservation_end`, `reservation_status`, `reservation_user_id`, `created_at`, `updated_at`) VALUES (3, '70085', '2025-03-26 16:32:33', '2025-04-27 16:32:33', '1', '2', '2025-03-26 18:31:01', '2025-03-28 11:08:49');
INSERT INTO `system_parking_space_reservation_record` (`id`, `parking_space_id`, `reservation_start`, `reservation_end`, `reservation_status`, `reservation_user_id`, `created_at`, `updated_at`) VALUES (4, '70163', '2025-03-28 09:30:17', '2025-03-28 09:45:17', '1', '2', '2025-03-28 09:30:17', NULL);
INSERT INTO `system_parking_space_reservation_record` (`id`, `parking_space_id`, `reservation_start`, `reservation_end`, `reservation_status`, `reservation_user_id`, `created_at`, `updated_at`) VALUES (5, '70171', '2025-04-16 18:49:47', '2025-04-16 19:04:47', '1', '2', '2025-04-16 18:49:47', NULL);
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
                               `id` varchar(32) NOT NULL COMMENT 'ID',
                               `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
                               `is_deactivate` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '是否停用 1｜停用 0｜正常',
                               `role_sort` int NOT NULL COMMENT '显示顺序, 数值越小，排序越靠前',
                               `permissions` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户权限,1|增，2｜改，3｜删，4｜查，5｜API接口;多个权限以逗号拼接',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-角色表';

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` (`id`, `role_name`, `is_deactivate`, `role_sort`, `permissions`) VALUES ('0', '车主用户', 0, 99, '5');
INSERT INTO `system_role` (`id`, `role_name`, `is_deactivate`, `role_sort`, `permissions`) VALUES ('1', '酒店用户', 0, 0, '1');
INSERT INTO `system_role` (`id`, `role_name`, `is_deactivate`, `role_sort`, `permissions`) VALUES ('2', '商户用户', 0, 1, '1');
INSERT INTO `system_role` (`id`, `role_name`, `is_deactivate`, `role_sort`, `permissions`) VALUES ('3', '管理员', 0, 2, '1,2,3,4');
INSERT INTO `system_role` (`id`, `role_name`, `is_deactivate`, `role_sort`, `permissions`) VALUES ('4', '超级管理员', 0, 3, '1,2,3,4');
INSERT INTO `system_role` (`id`, `role_name`, `is_deactivate`, `role_sort`, `permissions`) VALUES ('5', '开发者', 0, 98, '5');
COMMIT;

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
                                    `user_id` varchar(32) NOT NULL COMMENT '用户ID',
                                    `role_id` varchar(32) NOT NULL COMMENT '角色ID',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    PRIMARY KEY (`user_id`,`role_id`),
                                    KEY `roleid` (`role_id`),
                                    CONSTRAINT `roleid` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`),
                                    CONSTRAINT `userid` FOREIGN KEY (`user_id`) REFERENCES `system_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-用户和角色关联表';

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
BEGIN;
INSERT INTO `system_user_role` (`user_id`, `role_id`, `create_time`) VALUES ('1', '4', '2024-12-10 14:49:11');
INSERT INTO `system_user_role` (`user_id`, `role_id`, `create_time`) VALUES ('1888862836990537730', '1', '2025-02-10 16:09:28');
INSERT INTO `system_user_role` (`user_id`, `role_id`, `create_time`) VALUES ('1888862836990537743', '2', '2025-04-18 09:34:38');
INSERT INTO `system_user_role` (`user_id`, `role_id`, `create_time`) VALUES ('2', '0', '2025-03-26 10:24:42');
COMMIT;

-- ----------------------------
-- Table structure for system_users
-- ----------------------------
DROP TABLE IF EXISTS `system_users`;
CREATE TABLE `system_users` (
                                `id` varchar(32) NOT NULL COMMENT 'ID',
                                `account` varchar(32) NOT NULL COMMENT '登录账号',
                                `password` varchar(32) NOT NULL COMMENT '密码',
                                `head_sculpture` varchar(500) DEFAULT NULL COMMENT '头像',
                                `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `free_time` int DEFAULT NULL COMMENT '免费时长，单位:分钟',
                                `phone_number` varchar(32) DEFAULT NULL COMMENT '联系电话',
                                `front_business_license` varchar(500) DEFAULT NULL COMMENT '营业执照正面',
                                `opposite_business_license` varchar(500) DEFAULT NULL COMMENT '营业执照反面',
                                `status` tinyint DEFAULT NULL COMMENT '状态，1|正常;2|锁定;3|禁用',
                                `unit_address` varchar(500) DEFAULT NULL,
                                PRIMARY KEY (`id`,`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-用户表';

-- ----------------------------
-- Records of system_users
-- ----------------------------
BEGIN;
INSERT INTO `system_users` (`id`, `account`, `password`, `head_sculpture`, `user_name`, `create_time`, `free_time`, `phone_number`, `front_business_license`, `opposite_business_license`, `status`, `unit_address`) VALUES ('1', 'admin', '14e1b600b1fd579f47433b88e8d85291', NULL, '超级管理员', '2024-12-10 14:49:10', 15, NULL, NULL, NULL, 1, NULL);
INSERT INTO `system_users` (`id`, `account`, `password`, `head_sculpture`, `user_name`, `create_time`, `free_time`, `phone_number`, `front_business_license`, `opposite_business_license`, `status`, `unit_address`) VALUES ('1888862836990537730', 'rfdjd', '14e1b600b1fd579f47433b88e8d85291', '', '瑞丰大酒店', '2025-02-10 16:09:28', 15, '12345678901', '', NULL, 1, '');
INSERT INTO `system_users` (`id`, `account`, `password`, `head_sculpture`, `user_name`, `create_time`, `free_time`, `phone_number`, `front_business_license`, `opposite_business_license`, `status`, `unit_address`) VALUES ('1888862836990537743', 'btms', '14e1b600b1fd579f47433b88e8d85291', NULL, '贝塔猫舍', '2025-04-16 19:39:14', 15, '12453678910', NULL, NULL, 1, NULL);
INSERT INTO `system_users` (`id`, `account`, `password`, `head_sculpture`, `user_name`, `create_time`, `free_time`, `phone_number`, `front_business_license`, `opposite_business_license`, `status`, `unit_address`) VALUES ('2', 'xiaomi-2744c6b6-57d2-494f', '14e1b600b1fd579f47433b88e8d85291', NULL, '粤A·37490', '2025-03-26 10:21:19', 0, '15712345432', NULL, NULL, 1, NULL);
INSERT INTO `system_users` (`id`, `account`, `password`, `head_sculpture`, `user_name`, `create_time`, `free_time`, `phone_number`, `front_business_license`, `opposite_business_license`, `status`, `unit_address`) VALUES ('3', 'BetaCat', '14e1b600b1fd579f47433b88e8d85291', NULL, 'BetaCat', '2025-04-20 23:22:11', 0, NULL, NULL, NULL, 1, '192.168.0.1');
COMMIT;

-- ----------------------------
-- Table structure for system_we_chat_jsapi_pay
-- ----------------------------
DROP TABLE IF EXISTS `system_we_chat_jsapi_pay`;
CREATE TABLE `system_we_chat_jsapi_pay` (
                                            `id` varchar(32) NOT NULL COMMENT 'ID',
                                            `app_id` varchar(32) NOT NULL COMMENT '公众号appId',
                                            `secret` varchar(55) NOT NULL COMMENT '公众号密钥',
                                            `currency` varchar(12) NOT NULL COMMENT '币种：CNY',
                                            `mac_id` varchar(32) NOT NULL COMMENT '商户号',
                                            `private_key_path` varchar(255) NOT NULL COMMENT '商户私钥文件地址',
                                            `api_v3_key` varchar(255) NOT NULL COMMENT '商户APIv3密钥',
                                            `mac_serial_no` varchar(255) NOT NULL COMMENT '商户API证书序列号',
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理-微信JSAPI支付配置';

-- ----------------------------
-- Records of system_we_chat_jsapi_pay
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
