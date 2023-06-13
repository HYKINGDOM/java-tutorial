## spring boot 集成缓存


#### Caffeine
* expireAfterAccess, duration, 最后一次写入或访问后，指定经过多长的时间过期
* expireAfterWrite, duration, 最后一次写入后，指定经过多长的时间缓存过期
* initialCapacity, integer, 初始的缓存空间大小
* maximumSize, long, 缓存的最大条数
* maximumWeight, long, 缓存的最大权重
* refreshAfterWrite, duration, 创建缓存或者最近一次更新缓存后，经过指定的时间间隔后刷新缓存




#### 参考文档
https://juejin.cn/post/7133469059898671112

https://github.com/trunks2008/double-cache


枚举优化
https://mp.weixin.qq.com/s/CLr5bcxsG7C8v6qSsagEbw