CREATE TABLE `common_file` (
  `fileId` decimal(13,0) NOT NULL COMMENT '文件id',
  `fileName` varchar(200) DEFAULT NULL COMMENT '文件名',
  `filePath` varchar(2000) DEFAULT NULL COMMENT '文件路径',
  `ext` varchar(20) DEFAULT NULL COMMENT '扩展名',
  `fileBlob` blob COMMENT '附件内容',
  PRIMARY KEY (`fileId`),
  KEY `common_file_id` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sys_calendar_event` (
  `id` decimal(13,0) NOT NULL COMMENT 'id',
  `title` varchar(200) DEFAULT NULL COMMENT '事件在日历上显示的title',
  `allDay` varchar(20) DEFAULT 'false' COMMENT '是否是全天事件',
  `startDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '事件的开始时间',
  `endDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '事件的结束时间',
  `url` varchar(200) DEFAULT NULL COMMENT '事件被点击将打开对应url',
  `className` varchar(200) DEFAULT NULL COMMENT '事件的样式',
  `editable` varchar(200) DEFAULT NULL COMMENT '事件是否可编辑',
  `color` varchar(200) DEFAULT NULL COMMENT '背景和边框颜色',
  `backgroundColor` varchar(200) DEFAULT NULL COMMENT '背景颜色',
  `borderColor` varchar(200) DEFAULT NULL COMMENT '边框颜色',
  `textColor` varchar(200) DEFAULT NULL COMMENT '文本颜色',
  `userId` decimal(13,0) NOT NULL COMMENT '人员的的id',
  PRIMARY KEY (`id`),
  KEY `sys_calendar_event_id` (`id`) USING BTREE,
  KEY `sys_calendar_event_userid` (`userId`) USING BTREE,
  CONSTRAINT `sys_calendar_event_sys_user_id` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sys_user` (
  `id` decimal(13,0) NOT NULL COMMENT 'id',
  `account` varchar(200) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `fullname` varchar(200) DEFAULT '' COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `emailLicense` varchar(200) DEFAULT NULL COMMENT '邮箱第三方授权码',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_index_id` (`id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;