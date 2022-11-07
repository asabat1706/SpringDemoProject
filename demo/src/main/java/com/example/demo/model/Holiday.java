package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;


@Data
@Entity
@Table(name="holidays")
public class Holiday extends AuditEntity {

    @Id
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
