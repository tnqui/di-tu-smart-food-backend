package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "review_ratings", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "menu_item_id" }))
public class ReviewRating {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "UUID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @NotNull(message = "Rating is required")
    private Integer rating;

    @Size(max = 1000, message = "Comment cannot exceed 1000 characters")
    @Column(length = 1000)
    private String comment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
        this.createdAt = LocalDateTime.now();

    }
}
