package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.OrderItemRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.OrderItemDTO;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.OrderItemResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.RecipientDTO;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "items", source = "items")
    @Mapping(target = "paymentMethodName", source = "paymentMethod.name")
    CreateOrderResponse toCreateOrderResponse(Order order);

    List<OrderItemResponse> toOrderItemResponses(List<OrderItem> orderItems);

    @Mapping(target = "menuItemName", source = "menuItem.name")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    Order toOrder(CreateOrderRequest request);

    OrderItem toOrderItem(OrderItemRequest request);

}
