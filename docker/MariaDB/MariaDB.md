```cmd

-- 登录到MariaDB
mysql -u root -p

mariadb_tiEpR3

-- 创建新用户
CREATE USER 'spug'@'localhost' IDENTIFIED BY 'spug_tiEpR3';

-- 创建名为spug的数据库
CREATE DATABASE spug;

-- 授予新用户对spug数据库的所有权限
GRANT ALL PRIVILEGES ON spug.* TO 'spug'@'localhost';
-- 允许通过任何网卡访问
GRANT ALL PRIVILEGES ON spug.* TO 'spug'@'%' IDENTIFIED BY 'spug_tiEpR3';

-- 刷新权限
FLUSH PRIVILEGES;

-- 退出MariaDB
EXIT;

```
