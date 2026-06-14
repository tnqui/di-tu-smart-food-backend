package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import jakarta.persistence.*;
import lombok.*;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@SQLRestriction("deleted = false")
public abstract class BaseEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Builder.Default
    private Boolean deleted = false;

    private Instant deletedAt;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUIDUtils.generateUUID();
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    public void softDelete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
