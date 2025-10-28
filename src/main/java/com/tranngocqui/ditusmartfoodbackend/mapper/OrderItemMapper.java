package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem.OrderItemAdminCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderItemMapper {
    @Mapping(target = "itemName", source = "item.name")
    OrderItemAdminCreateResponse toOrderItemAdminCreateResponse(OrderItem orderItem);

}

