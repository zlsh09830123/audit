package com.example.audit.aspects;

import com.example.audit.model.ChangeEntry;

import java.util.ArrayList;
import java.util.List;

public class ChangeContext {

    private static final ThreadLocal<List<ChangeEntry>> auditEntries = ThreadLocal.withInitial(ArrayList::new);

    public static void addEntry(ChangeEntry entry) {
        auditEntries.get().add(entry);
    }

    public static List<ChangeEntry> getEntries() {
        return auditEntries.get();
    }

    public static void clear() {
        auditEntries.remove(); // 清理 ThreadLocal
    }
}

