package com.example.audit.repository;

import com.example.audit.model.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeRepository extends JpaRepository<ChangeLog, Long> {
}
