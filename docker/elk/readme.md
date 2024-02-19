#### kibana 使用

当kibana链接上ES之后, 打开Discover会看到对应的数据集,
打开之后在左下角可以进行数据字段的展示配置, 一般使用 message 和 fields.type 两个字段就行

检索使用KQL语言 使用方法

例如在message字段中检索信息,

message:"XNIO-1 task-8"

就会检索message中包含'XNIO-1 task-8'的数据

#### ES 配置修改

* 试用完全版30天,可以链接数据库工具
  POST
  localhost:9200/_license/start_trial?acknowledge=true&pretty

* 修改索引最大限制

```text

PUT
http://localhost:9200/_all/_settings?preserve_existing=true
body

{
"index.highlight.max_analyzed_offset" : "999999999"
}

{
"acknowledged": true
}
同时，在es的运行控制台会输出：

updating [index.highlight.max_analyzed_offset] from [1000000] to [999999999]

```

ES yellow 状态 执行ES.postman_collection.json中的请求