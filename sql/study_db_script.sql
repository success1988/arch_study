CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号',
  `category` varchar(30) NOT NULL COMMENT '分类',
  `name` varchar(50) NOT NULL COMMENT '字典名称',
  `value` varchar(200) NOT NULL COMMENT '字典值',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `dict_key` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

