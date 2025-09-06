package com.tranngocqui.ditusmartfoodbackend.service.dish;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.DishClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService {
    List<DishClientResponse> getAll();
    DishAdminResponse create(DishAdminRequest dishAdminRequest);
    Page<DishClientResponse> getDishesPagination(String categoryId, int page, int limit);

}
