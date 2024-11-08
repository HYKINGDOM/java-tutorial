wget https://dl.photoprism.app/docker/compose.yaml


```cmd

-- 登录到MariaDB
mysql -u root -p

mariadb_tiEpR3

-- 创建新用户
CREATE USER 'photoprism'@'localhost' IDENTIFIED BY 'insecure';

-- 创建名为spug的数据库
CREATE DATABASE photoprism;

-- 授予新用户对spug数据库的所有权限
GRANT ALL PRIVILEGES ON photoprism.* TO 'photoprism'@'localhost';
-- 允许通过任何网卡访问
GRANT ALL PRIVILEGES ON photoprism.* TO 'photoprism'@'%' IDENTIFIED BY 'insecure';

-- 刷新权限
FLUSH PRIVILEGES;

-- 退出MariaDB
EXIT;


```
