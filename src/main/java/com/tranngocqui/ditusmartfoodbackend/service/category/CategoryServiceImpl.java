package com.tranngocqui.ditusmartfoodbackend.service.category;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryResponse;
import com.tranngocqui.ditusmartfoodbackend.mapper.CategoryMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryAdminResponse create(CategoryAdminRequest request) {
        return categoryMapper.toCategoryAdminResponse(categoryRepository.save(categoryMapper.toCategory(request)));
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryMapper.toListCategoryResponse(categoryRepository.findAll());
    }

}
