package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.DishClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {
    List<DishClientResponse> toDishClientResponse(List<Dish> listDishes);

    @Mapping(target = "categories", ignore = true)
    Dish toDish(DishAdminRequest dishAdminRequest);

    DishAdminResponse toDishAdminResponse(Dish dish);
}
