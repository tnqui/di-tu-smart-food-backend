package com.tranngocqui.ditusmartfoodbackend.service.domain.orderitem;

import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;

import java.util.List;

public interface OrderItemDomainService {
    List<OrderItem> listItemAvailable(List<OrderItem> request);

}
