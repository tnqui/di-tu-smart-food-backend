package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class District extends BaseEntity {

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Ward> wards;

    @ManyToOne(fetch = FetchType.LAZY)
    Province province;
    
}
