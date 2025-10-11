package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
