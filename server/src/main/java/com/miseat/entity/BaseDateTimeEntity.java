package com.miseat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseDateTimeEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime lastModifiedDateTime;
}
