# 工程简介
H2数据库是一个开源的关系型数据库。H2采用java语言编写，不受平台的限制，同时支持网络版和嵌入式版本，有比较好的兼容性，支持相当标准的sql标准
提供JDBC、ODBC访问接口，提供了非常友好的基于web的数据库管理界面
官网：http://www.h2database.com/


# 延伸阅读

查看全部数据
由于设置了数据库脚本，所以SpringBoot项目每次启动都会运行一遍sql文件

#架构 (DDL) 脚本资源引用
spring.datasource.schema=classpath:db/schema.sql
#数据 (DML) 脚本资源引用
spring.datasource.data=classpath:db/data.sql
#SQL脚本编码
spring.datasource.sql-script-encoding=UTF-8
#初始化模式
spring.datasource.initialization-mode=ALWAYS
#如果在初始化数据库时发生错误，是否停止
spring.datasource.continue-on-error=true

H2数据库文件
数据库文件位置通过spring.datasource.url来指定

spring.datasource.url=jdbc:h2:file:./data;AUTO_SERVER=TRUE

运行方式
1.在内存中运行
数据库只在内存中运行，关闭连接后数据库将被清空，适合测试环境
连接字符串：

jdbc:h2:mem:DBName;DB_CLOSE_DELAY=-1
1
2.嵌入式
数据库持久化存储为单个文件
连接字符串：

jdbc:h2:file:~/.h2/DBName;AUTO_SERVER=TRUE
1
3.服务模式
H2支持三种服务模式：

web server：此种运行方式支持使用浏览器访问H2 Console
TCP server：支持客户端/服务器端的连接方式
PG server：支持PostgreSQL客户端
启动tcp服务连接字符串示例：

jdbc:h2:tcp://localhost/~/test 使用用户主目录
jdbc:h2:tcp://localhost//data/test 使用绝对路径
1
2
4.连接字符串参数

DB_CLOSE_DELAY：要求最后一个正在连接的连接断开后，不要关闭数据库

MODE=MySQL：兼容模式，H2兼容多种数据库，该值可以为：DB2、Derby、HSQLDB、MSSQLServer、MySQL、Oracle、PostgreSQL

AUTO_RECONNECT=TRUE：连接丢失后自动重新连接

AUTO_SERVER=TRUE：启动自动混合模式，允许开启多个连接，该参数不支持在内存中运行模式

TRACE_LEVEL_SYSTEM_OUT、TRACE_LEVEL_FILE：输出跟踪日志到控制台或文件， 取值0为OFF，1为ERROR（默认值），2为INFO，3为DEBUG

SET TRACE_MAX_FILE_SIZE mb：设置跟踪日志文件的大小，默认为16M

原文链接：https://blog.csdn.net/qq_31762741/article/details/122967384
