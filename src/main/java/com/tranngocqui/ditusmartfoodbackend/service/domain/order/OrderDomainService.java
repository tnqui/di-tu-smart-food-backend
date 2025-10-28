package com.tranngocqui.ditusmartfoodbackend.service.domain.order;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDomainService {
    Order getByIdOrThrow(String id);

    Order getByIdOrNull(String id);

    Page<Order> getOrders(Pageable pageable);

    void deleteOrderById(String id);

    Order createOrder(Order order);

    List<Order> getAllByIds(List<String> ids);

    Order updateOrder(Order order);
}
