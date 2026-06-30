package com.tranngocqui.ditusmartfoodbackend.service.order;

import com.tranngocqui.ditusmartfoodbackend.dto.client.order.*;
import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryFailReason;

public interface OrderService {
    OrderClientCreateResponse create(OrderClientCreateRequest request);

    OrderValidationResponse validate(OrderValidationRequest request);

    void customerConfirmOrder(String orderId, OrderConfirmRequest request);

    void restaurantConfirmOrder(String orderId);

    void prepareOrder(String orderId);

    void orderReady(String orderId);

    void orderDelivering(String orderId);

    void orderArrived(String orderId);

    void orderDelivered(String orderId);

    void orderDeliveryFailed(String orderId, DeliveryFailReason reason);

    void redelivering(String orderId);

    void refund(String orderId);

    void cancelOrder(String orderId);
}
