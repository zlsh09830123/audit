package com.example.audit.model;

import lombok.Data;

import java.util.Map;

@Data
public class ChangeEntry {
    private String entityName;
    private Map<String, ChangeRecord> changes;

    public ChangeEntry(String entityName, Map<String, ChangeRecord> changes) {
        this.entityName = entityName;
        this.changes = changes;
    }
}
