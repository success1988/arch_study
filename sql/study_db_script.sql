CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号',
  `category` varchar(30) NOT NULL COMMENT '分类',
  `name` varchar(50) NOT NULL COMMENT '字典名称',
  `value` varchar(200) NOT NULL COMMENT '字典值',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `dict_key` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `task_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL COMMENT '任务编号',
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `expression` varchar(200) NOT NULL,
  `jobClass` varchar(100) NOT NULL,
  `clusterIP` varchar(30) NOT NULL,
  `params` varchar(300) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `stop_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `task_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(50) NOT NULL COMMENT '任务编号',
  `name` varchar(30) DEFAULT NULL COMMENT '任务名称',
  `expression` varchar(50) DEFAULT NULL COMMENT '执行表达式',
  `type` char(1) DEFAULT NULL COMMENT '类型：S-启动 R-运行 P-停止',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间戳',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `exec_time` bigint(20) DEFAULT '0' COMMENT '执行时间，ms',
  `host` varchar(30) DEFAULT NULL COMMENT '主机IP',
  `status` char(1) DEFAULT NULL COMMENT '执行结果',
  `error_msg` varchar(200) DEFAULT NULL COMMENT '错误信息',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


