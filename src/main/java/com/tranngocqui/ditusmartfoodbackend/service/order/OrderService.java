package com.tranngocqui.ditusmartfoodbackend.service.order;


import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;

import java.util.UUID;

public interface OrderService {
    CreateOrderResponse create(CreateOrderRequest request);

    Order save(Order order);

    Order findById(UUID orderId);

    Order order(CreateOrderRequest request);

    void processAfterPayment(Order order);
}
