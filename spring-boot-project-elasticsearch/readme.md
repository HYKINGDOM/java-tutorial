# spring boot project demo

### Reference Documentation

docker 运行

```shell


docker run -e ES_JAVA_OPTS="-Xms1024m -Xmx1024m" -e "discovery.type=single-node" -d -p 9222:9200 -p 9333:9300 --name ES01 docker.elastic.co/elasticsearch/elasticsearch:8.10.2

```


检查ES是否存在账户

```curl

   curl -u elastic:Elastic_TmrNSa -X GET "http://localhost:9200/_security/user/myuser"
   

```


创建ES账户

```curl

   curl -u elastic:Elastic_TmrNSa -X POST "http://localhost:9200/_security/user/myuser" -H 'Content-Type: application/json' -d'
   {
     "password" : "myuser_password",
     "roles" : [ "user" ]
   }
```

使用 PUT 方法更新用户 myuser 的角色
```curl
curl -u elastic:Elastic_TmrNSa -X PUT "http://localhost:9200/_security/user/myuser" -H 'Content-Type: application/json' -d'
{
  "password" : "myuser_password",
  "roles" : [ "superuser" ]
}
```
验证用户角色

```curl
curl -u elastic:Elastic_TmrNSa -X GET "http://localhost:9200/_security/user/myuser"


```