
地址


```text

方式二：📦 容器化安装
⚠️ 注意：容器化安装方式需要先安装 docker，点击跳转docker安装文档

一条命令安装
docker run -p 2122:2122 --name jpom-server jpomdocker/jpom
使用挂载方式存储相关数据（在部分环境可能出现兼容性问题）
docker pull jpomdocker/jpom
mkdir -p /home/jpom-server/logs
mkdir -p /home/jpom-server/data
mkdir -p /home/jpom-server/conf
docker run -d -p 2122:2122 \
	--name jpom-server \
	-v /home/jpom-server/logs:/usr/local/jpom-server/logs \
	-v /home/jpom-server/data:/usr/local/jpom-server/data \
	-v /home/jpom-server/conf:/usr/local/jpom-server/conf \
	jpomdocker/jpom
使用容器卷方式存储相关数据
docker pull jpomdocker/jpom
docker volume create jpom-server-data
docker volume create jpom-server-logs
docker volume create jpom-server-conf
docker run -d -p 2122:2122 \
	--name jpom-server \
	-v jpom-server-data:/usr/local/jpom-server/data \
	-v jpom-server-logs:/usr/local/jpom-server/logs \
	-v jpom-server-conf:/usr/local/jpom-server/conf \
	jpomdocker/jpom
容器化安装仅提供服务端版。由于容器和宿主机环境隔离，而导致插件端的很多功能无法正常使用，因此对插件端容器化意义不大。

安装docker、配置镜像、自动启动、查找安装后所在目录等可参考文档 https://jpom.top/pages/b63dc5/

在低版本 docker 中运行可能出现 ls: cannot access'/usr/local/jpom-server/lib/': Operation not permitted 错误，此时需要添加 --privileged 参数 如：docker run -p 2122:2122 --name jpom-server jpomdocker/jpom --privileged

```