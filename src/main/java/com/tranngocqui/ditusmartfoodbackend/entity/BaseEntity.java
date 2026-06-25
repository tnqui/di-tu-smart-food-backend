package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
@SQLRestriction("deleted = false")
@NoArgsConstructor
@Getter
public abstract class BaseEntity {

    @Id
    protected UUID id;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Builder.Default
    protected Boolean deleted = false;

    protected Instant deletedAt;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected Instant createdAt;

    @LastModifiedDate
    protected Instant updatedAt;

    @PrePersist
    protected void prePersist() {
        if (id == null) {
            id = UUIDUtils.generateUUID();
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = Instant.now();
    }

    public void softDelete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
