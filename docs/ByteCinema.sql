create database bytecinema;
use bytecinema;
create table `t_user` (
  `id` bigint not null auto_increment,
  `email` varchar(255) default null,
  `username` varchar(255) default null,
  `password` varchar(255) default null,
  `avatar` varchar(255) default null,
  `default_favorites_id` bigint default null,
  `create_time` datetime(6) default null,
  `update_time` datetime(6) default null,
  `del_flag` int default 0,
  primary key (`id`)
) engine=innodb default charset=utf8mb4;

create table `t_permission` (
  `id` bigint(11) not null auto_increment,
  `p_id` bigint(11) default '0',
  `path` varchar(255) default null,
  `href` varchar(255) default null,
  `icon` varchar(255) default null,
  `name` varchar(255) default null,
  `is_menu` tinyint(2) default null,
  `target` varchar(255) default null,
  `sort` int(11) default '1',
  `state` tinyint(4) default '0',
  `create_time` datetime(6) default null,
  `update_time` datetime(6) default null,
  `del_flag` int default 0,
  primary key (`id`)
) engine=innodb auto_increment=127 default charset=utf8mb4;

create table `t_role` (
  `id` bigint(11) not null auto_increment,
  `name` varchar(255) default null,
  `remark` varchar(255) default null,
  primary key (`id`)
) engine=innodb auto_increment=17 default charset=utf8mb4;

insert into `t_role` values ('1', '超级管理员', null);
insert into `t_role` values ('16', '审核员', '');

create table `t_role_permission` (
  `id` bigint(11) not null auto_increment,
  `permission_id` bigint(11) default null,
  `role_id` bigint(11) default null,
  primary key (`id`)
) engine=innodb auto_increment=1513 default charset=utf8mb4;

create table `t_user_role` (
  `id` bigint(11) not null auto_increment,
  `role_id` bigint(255) default null,
  `user_id` bigint(255) default null,
  primary key (`id`)
) engine=innodb auto_increment=330 default charset=utf8mb4;