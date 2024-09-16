#  表设计
##  基础字段BaseDO
创建时间

更新时间

删除标识

##  用户

id 主键

email 邮箱

username 用户名 

password 加密后的密码

avatar 头像

default_favorites_id 喜欢id

```sql
CREATE TABLE `t_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) DEFAULT NULL,
  `username` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `avatar` VARCHAR(255) DEFAULT NULL,
  `default_favorites_id` BIGINT DEFAULT NULL,
  `create_time` DATETIME(6) DEFAULT NULL,
  `update_time` DATETIME(6) DEFAULT NULL,
  `del_flag` INT DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```


