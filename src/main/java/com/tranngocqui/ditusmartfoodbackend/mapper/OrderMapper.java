package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.OrderItemDTO;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.RecipientDTO;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "shippingFee", source = "shippingFee")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    @Mapping(target = "deliveryMethod", source = "deliveryMethod.id")
    @Mapping(target = "recipient", source = ".", qualifiedByName = "mapRecipient")
    @Mapping(target = "items", source = "items")
    CreateOrderResponse toCreateOrderResponse(Order order);

    @Named("mapRecipient")
    default RecipientDTO mapRecipient(Order order) {
        if (order == null) return null;
        RecipientDTO dto = new RecipientDTO();
        dto.setName(order.getRecipientName());
        dto.setPhone(order.getRecipientPhone());
        dto.setAddress(order.getShippingAddress());
        return dto;
    }

    @Mapping(target = "menuItem", source = "menuItem.id")
    @Mapping(target = "name", source = "menuItem.name")
    @Mapping(target = "price", source = "priceAtOrderTime")
    @Mapping(target = "total", expression = "java(item.getPriceAtOrderTime().multiply(java.math.BigDecimal.valueOf(item.getQuantity())))")
    OrderItemDTO toOrderItemDTO(OrderItem item);

    @Mapping(target="deliveryMethod", ignore=true)
    @Mapping(target = "items", ignore = true)
    Order toOrder(CreateOrderRequest request);
}
