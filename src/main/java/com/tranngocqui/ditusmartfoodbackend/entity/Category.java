package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "categories")
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@NoArgsConstructor
public class Category extends BaseEntity {
    private String name;
    private String description;
    private String imageUrl;
}