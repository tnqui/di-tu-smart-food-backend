package com.tranngocqui.ditusmartfoodbackend.service.order;


import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;

public interface OrderService {
    CreateOrderResponse create(CreateOrderRequest request);

}
