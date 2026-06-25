package com.tranngocqui.ditusmartfoodbackend.service.orderitem;

import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getOrderItemsAvailable(List<OrderItem> list);


}
