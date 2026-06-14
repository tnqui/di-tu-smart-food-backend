package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethodProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
    Optional<PaymentMethod> findOptionalByCode(PaymentMethodProvider code);
}
