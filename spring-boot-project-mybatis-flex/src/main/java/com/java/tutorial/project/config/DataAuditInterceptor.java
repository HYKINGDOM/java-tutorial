package com.java.tutorial.project.config;

import com.java.tutorial.project.domain.AuditLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;


@Intercepts({
    @Signature(type = Executor.class, method = "update",
        args = {MappedStatement.class, Object.class})
})
@Slf4j
@Component
public class DataAuditInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = null;
        try {
            MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
            Object parameter = invocation.getArgs()[1];

            // 检查是否有@DataAudit注解
            if (!hasDataAuditAnnotation(parameter)) {
                return invocation.proceed();
            }

            // 获取更新前的数据
            Map<String, Object> beforeData = getBeforeData(ms, parameter);

            // 执行更新操作
            result = invocation.proceed();

            // 只有更新成功才记录审计日志
            if (result != null && (Integer)result > 0) {
                // 获取更新后的数据
                Map<String, Object> afterData = getAfterData(ms, parameter);

                // 比较差异并记录审计日志
                saveAuditLog(beforeData, afterData, parameter);
            }

        } catch (Exception e) {
            // 记录错误日志但不影响主业务
            log.error("Data audit failed", e);
            result = invocation.proceed();
        }

        return result;
    }

    private Map<String, Object> getAfterData(MappedStatement ms, Object parameter) {
        return null;
    }

    private Map<String, Object> getBeforeData(MappedStatement ms, Object parameter) {
        return null;
    }

    private boolean hasDataAuditAnnotation(Object parameter) {
        return parameter != null &&
            parameter.getClass().isAnnotationPresent(DataAudit.class);
    }

    private void saveAuditLog(Map<String, Object> beforeData,
        Map<String, Object> afterData,
        Object parameter) {
        // 计算变更的字段
        Map<String, Object> changes = new HashMap<>();
        Set<String> changedFields = new HashSet<>();

        afterData.forEach((key, value) -> {
            Object oldValue = beforeData.get(key);
            if (!Objects.equals(value, oldValue)) {
                //changes.put(key, new Change(oldValue, value));
                changedFields.add(key);
            }
        });

        if (!changes.isEmpty()) {
            AuditLog log = new AuditLog();
//            log.setTableName(getTableName(parameter.getClass()));
            log.setOperatorId(getCurrentUserId());
            log.setOperateTime(new Date());
//            log.setChangeContent(new ObjectMapper().writeValueAsString(changes));
            log.setChangeFields(String.join(",", changedFields));

            //auditLogMapper.insert(log);
        }
    }

    private String getCurrentUserId() {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
