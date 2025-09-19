package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String label;
    private String fullAddress;
    private Double latitude;
    private Double longitude;
    private boolean defaultAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
