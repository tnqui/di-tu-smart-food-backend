package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "review_ratings")
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@NoArgsConstructor
public class ReviewRating extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "UUID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "Rating is required")
    private Integer rating;

    @Column(length = 1000)
    private String comment;

}
