package com.tranngocqui.ditusmartfoodbackend.service.application.order;


import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {
    CreateOrderResponse create(CreateOrderRequest request);

    OrderAdminCreateResponse createOrder(OrderAdminCreateRequest request);

    Page<OrderAdminResponse> getOrders(Pageable pageable);

    OrderAdminResponse getOrderById(String id);
    void deleteOrderById(String id);

    OrderAdminResponse updateOrder(String id, OrderAdminUpdateRequest request);

    Order order(CreateOrderRequest request);

    void processAfterPayment(Order order);




}
