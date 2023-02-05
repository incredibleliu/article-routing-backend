package com.cas.backend.service;

import com.cas.backend.entity.Audit;
import com.cas.backend.repository.AuditRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class AuditAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditAspect.class);

    @Autowired
    private AuditRepository auditRepository;

    @After("execution(* com.cas.backend.controller.CodeController.*(..)) && @annotation(Auditable)")
    public void audit(JoinPoint joinPoint) {
        LOGGER.info("Intercepted method: {}", joinPoint.getSignature().toShortString());

        Audit audit = new Audit();
        audit.setCreatedAt(LocalDateTime.now());
        audit.setModifiedBy(getUsername());
        audit.setOperation(joinPoint.getSignature().toShortString());
        audit.setData(Arrays.toString(joinPoint.getArgs()));
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<joinPoint.getArgs().length; i++) {
            sb.append(i + "=>" + joinPoint.getArgs()[i].toString());
        }
        LOGGER.info("joinPoint.getArgs():{}", Arrays.toString(joinPoint.getArgs()) + "," + sb);

        auditRepository.save(audit);
    }

    private String getUsername() {
        // get the current user's username
        return "david";
    }
}