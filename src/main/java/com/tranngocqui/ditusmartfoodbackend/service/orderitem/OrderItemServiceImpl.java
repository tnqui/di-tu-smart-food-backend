package com.tranngocqui.ditusmartfoodbackend.service.orderitem;

import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderItemRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final EntityManager entityManager;
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getOrderItemsAvailable(List<OrderItem> list) {
        return null;
    }
}
