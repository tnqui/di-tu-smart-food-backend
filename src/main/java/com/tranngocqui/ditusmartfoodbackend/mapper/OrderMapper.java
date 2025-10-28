package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem.OrderItemAdminCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.OrderItemRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.OrderItemResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "paymentMethodName", source = "paymentMethod.fullName")
    CreateOrderResponse toCreateOrderResponse(Order order);

    List<OrderItemResponse> toOrderItemResponses(List<OrderItem> orderItems);

    @Mapping(target = "menuItemName", source = "item.name")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    Order toOrder(CreateOrderRequest request);

    OrderItem toOrderItem(OrderItemRequest request);

    Order toOrder(OrderAdminCreateRequest request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "deliveryMethodId", source = "deliveryMethod.id")
    @Mapping(target = "deliveryMethodName", source = "deliveryMethod.fullName")
    @Mapping(target = "paymentMethodId", source = "paymentMethod.id")
    @Mapping(target = "paymentMethodName", source = "paymentMethod.fullName")
    @Mapping(target = "paymentTransactionId", source = "paymentMethod.id")
    OrderAdminResponse toOrderAdminResponse(Order order);

    @Mapping(target = "itemName", source = "item.name")
    @Mapping(target = "quantity", ignore = true)
    OrderItemAdminCreateResponse toOrderItemAdminCreateResponse(OrderItem orderItem);


    OrderAdminCreateResponse toOrderAdminCreateResponse(Order order);

}
