sudo docker pull registry.cn-hangzhou.aliyuncs.com/photoprism/photoprism:240915-ce



sudo docker pull docker.mirrors.ustc.edu.cn/photoprism/photoprism:latest

sudo docker pull photoprism/photoprism:latest

sudo systemctl daemon-reload
sudo systemctl restart

```json
     {
         "registry-mirrors": [
             "https://docker.mirrors.ustc.edu.cn",
             "https://hub-mirror.c.163.com",
             "https://registry.docker-cn.com",
			 "https://registry.cn-hangzhou.aliyuncs.com"
         ],
         "dns": ["114.114.114.114", "8.8.8.8"]
     }

```
     
```json

{
  "registry-mirrors": [
    "https://do.nark.eu.org",
    "https://dc.j8.work",
    "https://docker.m.daocloud.io",
    "https://dockerproxy.com",
    "https://docker.mirrors.ustc.edu.cn",
    "https://docker.nju.edu.cn"
  ]
}


```
	 
	 
	 

EOF
sudo systemctl daemon-reload
sudo systemctl restart docker


docker pull atomhub.openatom.cn/amd64/redis:7.0.13