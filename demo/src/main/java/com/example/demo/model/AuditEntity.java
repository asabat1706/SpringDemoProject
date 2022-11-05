package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Data
@MappedSuperclass
public class AuditEntity {

    @Column(name = "creation_time")
    private LocalDateTime create_date;
    private String created_by;
    @Column(name = "last_update_time")
    private LocalDateTime last_update_date;
    private String last_update_by;

}
