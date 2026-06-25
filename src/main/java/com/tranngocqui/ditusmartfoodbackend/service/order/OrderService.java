package com.tranngocqui.ditusmartfoodbackend.service.order;

import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderClientCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderClientCreateResponse;

public interface OrderService {
    OrderClientCreateResponse create(OrderClientCreateRequest request);


}
