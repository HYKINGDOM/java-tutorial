#! /bin/bash

# 创建目录
if [ ! -d "./elasticsearch/" ]; then
        mkdir -p ./elasticsearch/master/conf ./elasticsearch/master/data ./elasticsearch/master/logs
fi

if [ ! -d "./kibana/" ]; then
        mkdir -p ./kibana/conf ./kibana/logs
fi

if [ -d "./elasticsearch/" ]; then
        chmod 777 ./elasticsearch/master/data/ ./elasticsearch/master/logs/
fi

# 移动配置文件
if [ -f "./es-master.yml" ]; then
        cp ./es-master.yml ./elasticsearch/master/conf
fi

if [ -f "./kibana.yml" ]; then
        cp ./kibana.yml ./kibana/conf
fi


# 部署项目
docker-compose up --build -d