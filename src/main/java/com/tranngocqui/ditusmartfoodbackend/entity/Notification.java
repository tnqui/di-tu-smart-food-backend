package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.enums.NotificationStatus;
import com.tranngocqui.ditusmartfoodbackend.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.time.Instant;

@Entity
@Table(name = "notifications")
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@NoArgsConstructor
public class Notification extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private String redirectUrl;

    private Instant readAt;

    public void prePersist() {
        super.prePersist();
        if (this.type == null) {
            this.type = NotificationType.ORDER;
        }
        if (this.status == null) {
            this.status = NotificationStatus.UNREAD;
        }
    }

    public void markAsRead() {
        this.status = NotificationStatus.READ;
        this.readAt = Instant.now();
    }

}