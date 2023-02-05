package com.cas.backend.repository;

import com.cas.backend.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface CodeRepository extends JpaRepository<Code, Long> {
}
