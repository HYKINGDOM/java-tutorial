```cmd
sudo docker pull registry.cn-hangzhou.aliyuncs.com/openspug/spug-service

```

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





初始化
以下操作会创建一个用户名为 admin 密码为 spug.cc 的管理员账户，可自行替换管理员账户/密码。

docker exec spug init_spug admin spug.cc



注意
通过 系统管理 / 系统设置 / 安全设置 / 访问IP校验 来关闭



     sudo systemctl daemon-reload
     sudo systemctl restart docker
     