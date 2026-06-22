package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "addresses")
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@NoArgsConstructor
public class Address extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String fullAddress;

    private String houseNumber;

    @Column(nullable = false, length = 255)
    private String street;

    @Column(nullable = false, length = 255)
    private String ward;

    @Column(nullable = false, length = 255)
    private String district;

    @Column(nullable = false, length = 255)
    private String city;

    @Column(nullable = false, length = 255)
    private String country;

    private Double latitude;

    private Double longitude;

    private String geocodeMatchedAddress;

    private Boolean isDefault;
}
