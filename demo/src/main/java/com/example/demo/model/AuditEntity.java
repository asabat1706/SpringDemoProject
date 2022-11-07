package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {

    @CreatedDate
    @Column(name = "creation_time",updatable = false)
    private LocalDateTime create_date;
    @CreatedBy
    private String created_by;
    @LastModifiedDate
    @Column(name = "last_update_time",insertable = false)
    private LocalDateTime last_update_date;
    @LastModifiedBy
    @Column(insertable = false)
    private String last_update_by;

}
