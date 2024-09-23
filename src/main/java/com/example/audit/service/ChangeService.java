package com.example.audit.service;

import com.example.audit.model.ChangeEntry;
import com.example.audit.model.ChangeLog;
import com.example.audit.model.ChangeRecord;
import com.example.audit.repository.ChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChangeService {

    @Autowired
    private ChangeRepository changeRepository;

    @Async
    public void recordChanges(List<ChangeEntry> entries) {
        Map<String, Map<String, ChangeRecord>> mergedChanges = new HashMap<>();

        for (ChangeEntry entry : entries) {
            String entityName = entry.getEntityName();
            Map<String, ChangeRecord> changes = entry.getChanges();

            if (mergedChanges.containsKey(entityName)) {
                mergedChanges.get(entityName).putAll(changes);
            } else {
                mergedChanges.put(entityName, changes);
            }
        }

        ChangeLog changeLog = new ChangeLog();
        changeLog.setChanges(mergedChanges);
        changeLog.setTimestamp(LocalDateTime.now());

        changeRepository.save(changeLog);
    }
}

