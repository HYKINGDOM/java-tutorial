# spring boot project demo

### Reference Documentation

docker 运行
```shell


docker run -e ES_JAVA_OPTS="-Xms1024m -Xmx1024m" -e "discovery.type=single-node" -d -p 9222:9200 -p 9333:9300 --name ES01 docker.elastic.co/elasticsearch/elasticsearch:8.10.2

```
