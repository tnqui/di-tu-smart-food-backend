package com.tranngocqui.ditusmartfoodbackend.service.dish;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.DishClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Dish;
import com.tranngocqui.ditusmartfoodbackend.mapper.DishMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<DishClientResponse> getAll() {
        return dishMapper.toDishClientResponse(dishRepository.findAll());
    }

    @Override
    public DishAdminResponse create(DishAdminRequest dishAdminRequest) {
        Dish dish = dishMapper.toDish(dishAdminRequest);

        var listValidCategory = categoryRepository.findAllById(dishAdminRequest.getCategories());

        dish.setCategories(new HashSet<>(listValidCategory));

        return dishMapper.toDishAdminResponse(dishRepository.save(dish));
    }

    @Override
    public Page<DishClientResponse> getDishesPagination(String categoryName, int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());

        Page<Dish> dishes = (categoryName != null && !categoryName.isEmpty())
                ? dishRepository.findByCategories_Name(categoryName, pageable)
                : dishRepository.findAll(pageable);

        return new PageImpl<>(
                dishMapper.toDishClientResponse(dishes.getContent()),
                pageable,
                dishes.getTotalElements()
        );
    }
}
