package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, UUID> {
}
