package com.example.audit.aspects;

import com.example.audit.annotation.Trackable;
import com.example.audit.model.ChangeEntry;
import com.example.audit.model.ChangeRecord;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class EntityChangeAspect {

    @Around("execution(* com.example.audit.repository.*.save(..)) && args(entity)")
    public Object trackEntityChanges(ProceedingJoinPoint joinPoint, Object entity) throws Throwable {
        if (entity.getClass().isAnnotationPresent(Trackable.class)) {
            // 在實體保存之前獲取原始狀態
            Object originalState = cloneEntity(entity);

            // 執行實體保存操作
            Object result = joinPoint.proceed();

            // 在實體保存之後獲取變更狀態
            Object changedState = entity;

            // 比較原始狀態和變更狀態
            Map<String, ChangeRecord> changes = compareEntities(originalState, changedState);

            if (!changes.isEmpty()) {
                // 記錄變更到 ChangeContext
                ChangeContext.addEntry(new ChangeEntry(entity.getClass().getSimpleName(), changes));
            }

            return result;
        } else {
            return joinPoint.proceed(); // 如果不需要記錄變更，直接進行保存操作
        }
    }

    private Object cloneEntity(Object entity) {
        // 實現實體的深拷貝
        // 可以使用序列化、Reflection 或其他方法來實現
    }

    private Map<String, ChangeRecord> compareEntities(Object original, Object changed) {
        // 比較實體的原始狀態和變更狀態
        // 返回字段變更的 Map
    }
}

