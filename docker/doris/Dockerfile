# 选择基础镜像
FROM apache/doris:build-env-for-3.0

# 设置环境变量
ENV JAVA_HOME=/usr/lib/jvm/jdk-17.0.2/
ENV PATH=$JAVA_HOME/bin/:$PATH

# 挂载源码目录
WORKDIR /root/doris-branch-3.0

# 编译 Doris
RUN sh build.sh