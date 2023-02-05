package com.cas.backend.repository;

import com.cas.backend.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Long> { }