package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    String fullAddress;

    String houseNumber;

    @Column(nullable = false, length = 255)
    String street;

    @Column(nullable = false, length = 255)
    String ward;

    @Column(nullable = false, length = 255)
    String district;

    @Column(nullable = false, length = 255)
    String city;

    @Column(nullable = false, length = 255)
    String country;

    Double latitude;

    Double longitude;

    String geocodeMatchedAddress;

    Boolean isDefault;
}
