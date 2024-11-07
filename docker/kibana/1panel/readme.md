

#### 创建服务账户
```curl

   curl -u elastic:Elastic_TmrNSa -X POST "http://localhost:9200/_security/user/kibana_user" -H "Content-Type: application/json" -d'
   {
     "password" : "Kibana_User_Pass",
     "roles" : [ "kibana_user" ]
   }'
   
```

#### 创建角色

```curl

   curl -u elastic:Elastic_TmrNSa -X POST "http://localhost:9200/_security/role/kibana_monitor" -H "Content-Type: application/json" -d'
   {
     "cluster": ["monitor"],
     "indices": [
       {
         "names": ["*"],
         "privileges": ["read", "view_index_metadata"]
       }
     ],
     "applications": [
       {
         "application": "kibana-.kibana",
         "privileges": ["all"],
         "resources": ["*"]
       }
     ]
   }'
   

```


#### 验证角色配置
```curl

   curl -u elastic:Elastic_TmrNSa -X GET "http://localhost:9200/_security/role/kibana_monitor"
   
```

#### 更新用户角色
更新 kibana_user 用户的角色
```curl

   curl -u elastic:Elastic_TmrNSa -X POST "http://localhost:9200/_security/user/kibana_user" -H "Content-Type: application/json" -d'
   {
     "password" : "Kibana_User_Pass",
     "roles" : [ "kibana_monitor" ]
   }'
   
```

#### 验证用户角色

```curl

   curl -u elastic:Elastic_TmrNSa -X GET "http://localhost:9200/_security/user/kibana_user"
   
```