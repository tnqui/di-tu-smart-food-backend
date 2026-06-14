package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethodProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, UUID> {

    Optional<DeliveryMethod> findOptionalByCode(DeliveryMethodProvider code);

}
