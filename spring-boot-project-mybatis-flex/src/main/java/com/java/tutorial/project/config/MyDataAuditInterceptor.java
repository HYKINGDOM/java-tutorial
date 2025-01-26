package com.java.tutorial.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.tutorial.project.domain.DataAuditEntity;
import com.java.tutorial.project.mapper.DataAuditEntityMapper;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

/**
 * @author kscs
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Component
public class MyDataAuditInterceptor implements Interceptor {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Resource
    private DataAuditEntityMapper dataAuditMapper;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object parameter = args[1];

        // 1. 检查是否需要审计
        if (!isAuditable(parameter)) {
            return invocation.proceed();
        }

        // 2. 获取旧数据
        Object oldData = getOldData(parameter);

        // 3. 执行原update操作
        Object result = invocation.proceed();
        int affectedRows = (Integer)result;

        if (affectedRows > 0) {
            // 4. 注册事务同步处理审计
            handleAuditAsync(oldData, parameter);
        }

        return result;
    }

    private boolean isAuditable(Object obj) {
        return obj != null && obj.getClass().isAnnotationPresent(Auditable.class);
    }

    private Object getOldData(Object entity) throws Exception {
        Class<?> clazz = entity.getClass();
        Auditable auditable = clazz.getAnnotation(Auditable.class);

        // 获取主键值
        Method pkGetter = clazz.getMethod(auditable.pkGetter());
        Object id = pkGetter.invoke(entity);

        // 获取Mapper并查询旧数据
        Object mapper = sqlSessionTemplate.getMapper(clazz);
        Method selectMethod = mapper.getClass().getMethod(auditable.selectMethod(), id.getClass());
        return selectMethod.invoke(mapper, id);
    }

    private void handleAuditAsync(Object oldData, Object newData) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    processAudit(oldData, newData);
                }
            });
        } else {
            processAudit(oldData, newData);
        }
    }

    private void processAudit(Object oldData, Object newData) {
        try {
            Map<String, Map<String, Object>> changes = compareData(oldData, newData);
            if (!changes.isEmpty()) {
                DataAuditEntity audit = buildAudit(oldData, newData, changes);
                dataAuditMapper.insert(audit);
            }
        } catch (Exception e) {
            // 异常处理保证不影响主业务
            e.printStackTrace();
        }
    }

    private Map<String, Map<String, Object>> compareData(Object oldObj, Object newObj) throws IllegalAccessException {

        Map<String, Map<String, Object>> changes = new LinkedHashMap<>();

        for (Field field : oldObj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object oldVal = field.get(oldObj);
            Object newVal = field.get(newObj);

            if (!Objects.equals(oldVal, newVal)) {
                Map<String, Object> change = new HashMap<>();
                change.put("old", oldVal);
                change.put("new", newVal);
                changes.put(field.getName(), change);
            }
        }
        return changes;
    }

    private DataAuditEntity buildAudit(Object oldData, Object newData, Map<String, Map<String, Object>> changes)
        throws Exception {

        Class<?> clazz = newData.getClass();
        Auditable auditable = clazz.getAnnotation(Auditable.class);

        // 获取记录ID
        Method pkGetter = clazz.getMethod(auditable.pkGetter());
        Object recordId = pkGetter.invoke(newData);

        DataAuditEntity audit = new DataAuditEntity();
        audit.setTableName(clazz.getSimpleName());
        audit.setRecordId(recordId.toString());
        audit.setOperateTime(new Date());
        audit.setChangedFields(String.join(",", changes.keySet()));
        audit.setChangeContent(objectMapper.writeValueAsString(changes));

        // 获取操作人（需要实现）
        audit.setOperator(getCurrentUser());
        return audit;
    }

    private String getCurrentUser() {
        // 实现获取当前用户逻辑
        return "system";
    }

}
