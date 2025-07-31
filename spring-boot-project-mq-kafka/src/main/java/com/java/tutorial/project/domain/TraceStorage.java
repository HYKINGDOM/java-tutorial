//package com.java.tutorial.project.domain;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//public class TraceStorage {
//    private final ClickHouse timeseriesDB;  // 明细数据
//    private final Elasticsearch searchDB;    // 搜索服务
//    private final Redis cacheDB;            // 实时缓存
//
//    public void store(TraceEvent event) {
//        // 1. 写入实时缓存
//        cacheDB.setex(buildKey(event), TTL_SECONDS, event);
//
//        // 2. 异步写入明细存储
//        CompletableFuture.runAsync(() ->
//            timeseriesDB.insert(convertToClickHouseModel(event)));
//
//        // 3. 异步更新搜索索引
//        CompletableFuture.runAsync(() ->
//            searchDB.index(convertToSearchModel(event)));
//    }
//
//    public TraceChain getTraceChain(String traceId) {
//        // 1. 查询缓存
//        List<TraceEvent> cachedEvents = cacheDB.get(buildKey(traceId));
//        if (!cachedEvents.isEmpty()) {
//            return buildChain(cachedEvents);
//        }
//
//        // 2. 查询明细库
//        List<TraceEvent> events = timeseriesDB.query(
//            "SELECT * FROM trace_events WHERE trace_id = ? ORDER BY timestamp",
//            traceId
//        );
//
//        return buildChain(events);
//    }
//}
