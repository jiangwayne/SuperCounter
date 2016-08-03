/*
SQLyog v10.2
MySQL - 5.7.12-log : Database - super_counter
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE `super_counter`;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `full_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `password_hash` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '加密后的密码',
  `password_salt` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT 'salt',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织id',
  `last_long_lat` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '最后登录时的位置经纬度',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `brand_ids` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌ids(逗号分隔)',
  `state` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '省份',
  `city` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '城市',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user` */

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user_role` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_role` */
INSERT INTO t_role VALUES (1,'admin',1,now(),now());
INSERT INTO t_role VALUES (2,'客服经理',1,now(),now());
INSERT INTO t_role VALUES (3,'品牌经理',1,now(),now());
INSERT INTO t_role VALUES (4,'供应商管理员',1,now(),now());
INSERT INTO t_role VALUES (5,'BA',1,now(),now());
INSERT INTO t_role VALUES (6,'安装工',1,now(),now());
/*Table structure for table `t_organization` */

DROP TABLE IF EXISTS `t_organization`;

CREATE TABLE `t_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '组织名',
  `type` varchar(64) DEFAULT NULL COMMENT '组织类型(admin,品牌，柜台，供应商，物流公司，安装公司)(1：品牌，2：柜台，3：供应商，4：物流，5：陈列,6安装公司)',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父组织id',
  `phone` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `email` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `wx_unique_code` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '微信号',
  `style_id` bigint(20) DEFAULT NULL COMMENT '柜台样式id',
  `media_type` varchar(64) DEFAULT NULL COMMENT '柜台媒介类型',
  `counter_no` varchar(64) DEFAULT NULL COMMENT '柜台编号',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `long_lat` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '位置经纬度',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `brand_ids` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌ids(逗号分隔)',
  `state` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '省份',
  `city` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '城市',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_organization` */
INSERT INTO t_organization(id,name,type,valid,gmt_create,gmt_modify) VALUES (1,'admin','0',1,now(),now());
INSERT INTO t_organization(id,name,type,valid,gmt_create,gmt_modify) VALUES (2,'安装公司A','5',1,now(),now());


/*Table structure for table `t_counter_style` */

DROP TABLE IF EXISTS `t_counter_style`;

CREATE TABLE `t_counter_style` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '样式名',
  `org_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `description` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_counter_style` */

/*Table structure for table `t_counter_template` */

DROP TABLE IF EXISTS `t_counter_template`;

CREATE TABLE `t_counter_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `counter_style_id` bigint(20) DEFAULT NULL COMMENT '柜台样式id',
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
  `furniture_id` bigint(20) DEFAULT NULL COMMENT '家具id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_counter_template` */

/*Table structure for table `t_object_child` */

DROP TABLE IF EXISTS `t_object_child`;
CREATE TABLE `t_object_child` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '子件名称',
  `qr_code` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '二维码',
  `length` int(11) DEFAULT NULL COMMENT '长',
  `width` int(11) DEFAULT NULL COMMENT '宽',
  `height` int(11) DEFAULT NULL COMMENT '高',
  `pic_url` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `reduce` int(11) NOT NULL DEFAULT '0' COMMENT '出库数',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `remark` varchar(4000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_object_child` */

/*Table structure for table `t_object_parent` */

DROP TABLE IF EXISTS `t_object_parent`;

CREATE TABLE `t_object_parent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '父件名称',
  `qr_code` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '二维码',
  `org_id` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `obj_parent_no` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '二维码',
  `type` int(11) DEFAULT NULL COMMENT '父件类型(1：图片，2：道具，3：灯片)',
  `counter_id` bigint(20) DEFAULT NULL COMMENT '如果是灯片，则柜台id不可为空',
  `length` int(11)DEFAULT NULL COMMENT '长',
  `width` int(11)DEFAULT NULL COMMENT '宽',
  `height` int(11)DEFAULT NULL COMMENT '高',
  `length_up` int(11)DEFAULT NULL COMMENT '出血长',
  `width_up` int(11)DEFAULT NULL COMMENT '出血宽',
  `height_up` int(11)DEFAULT NULL COMMENT '出血高',
  `pic_url` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `material` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '材质',
  `content` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `site_no` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '位置编号',
  `reduce` int(11) NOT NULL DEFAULT '0' COMMENT '出库数',
  `counts` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `remark` varchar(4000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_object_parent` */

/*Table structure for table `t_parent_child` */

DROP TABLE IF EXISTS `t_parent_child`;

CREATE TABLE `t_parent_child` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
  `obj_child_id` bigint(20) DEFAULT NULL COMMENT '子件id',
  `obj_child_count` int(11) DEFAULT NULL COMMENT '子件数量',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_parent_child` */

/*Table structure for table `t_furniture` */

DROP TABLE IF EXISTS `t_furniture`;

CREATE TABLE `t_furniture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '家具名称',
  `org_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `furniture_no` varchar(64) DEFAULT NULL COMMENT '家具编号',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_furniture` */

/*Table structure for table `t_counter_details` */

DROP TABLE IF EXISTS `t_counter_details`;

CREATE TABLE `t_counter_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) DEFAULT NULL COMMENT '柜台id',
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
  `furniture_id` bigint(20) DEFAULT NULL COMMENT '家具id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `site_no` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '位置编号',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_counter_details` */

/*Table structure for table `t_stock` */

DROP TABLE IF EXISTS `t_stock`;

CREATE TABLE `t_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `good_id` bigint(20) DEFAULT NULL COMMENT '货物id',
  `good_type` int(11) DEFAULT NULL COMMENT '货物类型（1：父件，2：子件）',
  `good_count` int(11) DEFAULT NULL COMMENT '数量',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_stock` */

/*Table structure for table `t_express` */

DROP TABLE IF EXISTS `t_express`;

CREATE TABLE `t_express` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_org_id` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `to_org_id` bigint(20) DEFAULT NULL COMMENT '柜台id或供应商id',
  `order_counter_id` bigint(20) DEFAULT NULL COMMENT '柜台订单id',
  `pic_url` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '12张图片url',
  `express_number` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '快递单号',
  `express_type` int(11) DEFAULT NULL COMMENT '快递类型（1：系统快递，2：申通快递',
  `status` int(11) DEFAULT NULL COMMENT '1：待发货，2：待收货，3：待安装，4：已拒收，5：暂存，6：安装完成',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_express` */


/*Table structure for table `t_express_box` */

DROP TABLE IF EXISTS `t_express_box`;

CREATE TABLE `t_express_box` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `express_id` bigint(20) DEFAULT NULL COMMENT '快递单id',
--  `order_counter_id` bigint(20) DEFAULT NULL COMMENT '柜台订单id',
--  `pic_url` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '12张图片url',
  `express_box_number` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '快递箱号',
  `qr_code` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '二维码',
--  `express_type` int(11) DEFAULT NULL COMMENT '快递类型（1：系统快递，2：申通快递',
--  `status` int(11) DEFAULT NULL COMMENT '1：待发货，2：待收货，3：待安装，4：已拒收，5：暂存，6：安装完成',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_express_box` */






/*Table structure for table `t_express_box_details` */

DROP TABLE IF EXISTS `t_express_box_details`;

CREATE TABLE `t_express_box_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `express_box_id` bigint(20) DEFAULT NULL COMMENT '快递箱id',
  `express_id` bigint(20) DEFAULT NULL COMMENT '快递单id',
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
  `obj_parent_count` int(11) DEFAULT NULL COMMENT '父件数量',
--  `pic_url` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `status` int(11) DEFAULT NULL COMMENT '1：待发货，2：待收货，3：待安装，4：已拒收，5：暂存，6：安装完成',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_express_box_details` */





/*Table structure for table `t_express_record` */

DROP TABLE IF EXISTS `t_express_record`;

CREATE TABLE `t_express_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `express_id` bigint(20) DEFAULT NULL COMMENT '快递箱id',
  `express_number` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '快递单号',
  `receive_site` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '收货站点',
  `address` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '收货地址',
  `ip_address` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'id地址',
  `long_lat` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '经纬度',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_express_record` */

/*Table structure for table `t_receive_record` */

DROP TABLE IF EXISTS `t_receive_record`;

CREATE TABLE `t_receive_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `express_id` bigint(20) DEFAULT NULL COMMENT '快递箱id',
  `org_id` bigint(20) DEFAULT NULL COMMENT '柜台id',
  `long_lat` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '经纬度',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_receive_record` */


/*Table structure for table `t_damage` */

DROP TABLE IF EXISTS `t_damage`;

CREATE TABLE `t_damage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `express_id` bigint(20) DEFAULT NULL COMMENT '快递箱id',
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
  `obj_child_id` bigint(20) DEFAULT NULL COMMENT '子件id',
  `pic_url` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `reason` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '报损原因',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_damage` */

/*Table structure for table `t_replenish` */

DROP TABLE IF EXISTS `t_replenish`;

CREATE TABLE `t_replenish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_org_id` bigint(20) DEFAULT NULL COMMENT '柜台id',
  `to_org_id` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '发起人id',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_replenish` */

/*Table structure for table `t_replenish_details` */

DROP TABLE IF EXISTS `t_replenish_details`;

CREATE TABLE `t_replenish_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `replenish_id` bigint(20) DEFAULT NULL COMMENT '补货单id',
  `good_id` bigint(20) DEFAULT NULL COMMENT '货物id',
  `good_type` int(11) DEFAULT NULL COMMENT '货物类型（1：父件，2：子件）',
  `good_count` int(11) DEFAULT NULL COMMENT '数量',
  `pic_url` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `discription` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_replenish_details` */

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '下单人id',
  `org_brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
--  `status` int(11) DEFAULT NULL COMMENT '1：未开始，2：生产中，3：生产完成, 4:待收货， 5：待安装， 6：补货中，7：已完成',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_order` */

/*Table structure for table `t_order_counter` */

DROP TABLE IF EXISTS `t_order_counter`;

CREATE TABLE `t_order_counter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `org_counter_id` bigint(20) DEFAULT NULL COMMENT '柜台id',
  `order_counter_no` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '柜台订单编号',
--  `user_id` bigint(20) DEFAULT NULL COMMENT '下单人id',
--  `status` int(11) DEFAULT NULL COMMENT '1：未开始，2：生产中，3：生产完成, 4:待收货， 5：待安装， 6：补货中，7：已完成',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_order_counter` */


/*Table structure for table `t_order_counter_detail` */

DROP TABLE IF EXISTS `t_order_counter_detail`;

CREATE TABLE `t_order_counter_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_counter_id` bigint(20) DEFAULT NULL COMMENT '柜台订单id',
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
--  `order_counter_no` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '柜台订单编号',
  `obj_parent_count` int(11) DEFAULT NULL COMMENT '父件数量',
--  `status` int(11) DEFAULT NULL COMMENT '1：未开始，2：生产中，3：生产完成, 4:待收货， 5：待安装， 6：补货中，7：已完成',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_order_counter_detail` */



/*Table structure for table `t_order_supplier` */

DROP TABLE IF EXISTS `t_order_supplier`;

CREATE TABLE `t_order_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `org_supplier_id` bigint(20) DEFAULT NULL COMMENT '供应商组织id',
  `order_supplier_no` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '供应商订单编号',
--  `user_id` bigint(20) DEFAULT NULL COMMENT '下单人id',
--  `status` int(11) DEFAULT NULL COMMENT '1：未开始，2：生产中，3：生产完成, 4:待收货， 5：待安装， 6：补货中，7：已完成',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_order_supplier` */


/*Table structure for table `t_order_supplier_detail` */

DROP TABLE IF EXISTS `t_order_supplier_detail`;

CREATE TABLE `t_order_supplier_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_supplier_id` bigint(20) DEFAULT NULL COMMENT '生产加工单id',
--  `org_id` bigint(20) DEFAULT NULL COMMENT '柜台id',
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
  `obj_parent_count` int(11) DEFAULT NULL COMMENT '父件数量',
  `backup_count` int(11) DEFAULT NULL COMMENT '备份数量',
  `in_stock_time` datetime DEFAULT NULL COMMENT '预计入库时间',
  `out_stock_time` datetime DEFAULT NULL COMMENT '出库时间',
  `price_cny` int(11) DEFAULT NULL COMMENT '报价-RMB',
  `price_usd` int(11) DEFAULT NULL COMMENT '报价-美元',
  `price_eur` int(11) DEFAULT NULL COMMENT '报价-欧元',
  `unit_price_cny` int(11) DEFAULT NULL COMMENT '单价-RMB',
  `unit_price_usd` int(11) DEFAULT NULL COMMENT '单价-美元',
  `unit_price_eur` int(11) DEFAULT NULL COMMENT '单价-欧元',
  `final_price_cny` int(11) DEFAULT NULL COMMENT '最终价格-RMB',
  `final_price_usd` int(11) DEFAULT NULL COMMENT '最终价格-美元',
  `final_price_eur` int(11) DEFAULT NULL COMMENT '最终价格-欧元',
  `final_unit_price_cny` int(11) DEFAULT NULL COMMENT '最终单价-RMB',
  `final_unit_price_usd` int(11) DEFAULT NULL COMMENT '最终单价-美元',
  `final_unit_price_eur` int(11) DEFAULT NULL COMMENT '最终单价-欧元',
  `po_number` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'PO号',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_order_supplier_detail` */




/*Table structure for table `t_order_setup` */

DROP TABLE IF EXISTS `t_order_setup`;

CREATE TABLE `t_order_setup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `damage_id` bigint(20) DEFAULT NULL COMMENT '补货单id',
  `order_counter_ids` varchar(512) DEFAULT NULL COMMENT '柜台订单id（逗号分隔）',
  `org_counter_id` bigint(20) DEFAULT NULL COMMENT '柜台id',
  `user_setup_id` bigint(20) DEFAULT NULL COMMENT '安装工id',
--  `order_counter_no` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '柜台订单编号',
--  `user_id` bigint(20) DEFAULT NULL COMMENT '下单人id',
--  `status` int(11) DEFAULT NULL COMMENT '1：未开始，2：生产中，3：生产完成, 4:待收货， 5：待安装， 6：补货中，7：已完成',
  `setup_time` datetime DEFAULT NULL COMMENT '安装时间',
  `task_type` int(11) DEFAULT NULL COMMENT '任务类型(1:上市任务，2：上市补货任务，3：日常补货任务)',
  `comment` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `pic_url` varchar(4096) COLLATE utf8_bin DEFAULT NULL COMMENT '12图片url',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_order_setup` */


/*Table structure for table `t_setup_record` */

DROP TABLE IF EXISTS `t_setup_record`;

CREATE TABLE `t_setup_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_setup_id` bigint(20) DEFAULT NULL COMMENT '安装工订单id',
  `user_setup_id` bigint(20) DEFAULT NULL COMMENT '安装工id',
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
--  `org_id` bigint(20) DEFAULT NULL COMMENT '柜台id',
  `pic_url` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `discription` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `long_lat` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '经纬度',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_setup_record` */

/*Table structure for table `t_display_handbook` */

DROP TABLE IF EXISTS `t_display_handbook`;

CREATE TABLE `t_display_handbook` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `description` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `pic_url` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `obj_parent_id` bigint(20) DEFAULT NULL COMMENT '父件id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父目录id',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_display_handbook` */


/*Table structure for table `t_everyday_info` */

DROP TABLE IF EXISTS `t_everyday_info`;

CREATE TABLE `t_everyday_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `description` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `pic_url` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `valid` int(11) DEFAULT NULL COMMENT '逻辑删除（1:有效数据,-1:已删除）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_everyday_info` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
