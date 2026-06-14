package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ward extends BaseEntity {

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "ward", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Street> streets;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    District district;


}
