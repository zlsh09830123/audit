package com.example.audit.model;

import lombok.Data;

@Data
public class ChangeRecord {
    private String oldValue;
    private String newValue;

    public ChangeRecord(String oldValue, String newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
