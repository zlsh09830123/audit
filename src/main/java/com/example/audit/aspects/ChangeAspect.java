package com.example.audit.aspects;

import com.example.audit.model.ChangeEntry;
import com.example.audit.service.ChangeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class ChangeAspect {

    @Autowired
    private ChangeService changeService;

    @AfterReturning("@annotation(com.example.audit.annotation.Trackable)")
    public void recordChangesAfterMethod(JoinPoint joinPoint) {
        List<ChangeEntry> entries = ChangeContext.getEntries();

        if (!entries.isEmpty()) {
            changeService.recordChanges(entries);
        }

        ChangeContext.clear(); // 清理上下文
    }
}

