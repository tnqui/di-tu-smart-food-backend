package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
}
