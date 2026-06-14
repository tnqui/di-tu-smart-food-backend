package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Street extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    Ward ward;

    String name;

}
