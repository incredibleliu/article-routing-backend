package com.cas.backend.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Code {

    private @Id @GeneratedValue Long id;

    private BigDecimal value;

    public Code() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Code(Long id, BigDecimal value) {
        this.id = id;
        this.value = value;
    }


}
