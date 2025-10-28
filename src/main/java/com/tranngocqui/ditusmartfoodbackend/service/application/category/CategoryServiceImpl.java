package com.tranngocqui.ditusmartfoodbackend.service.application.category;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import com.tranngocqui.ditusmartfoodbackend.mapper.CategoryMapper;
import com.tranngocqui.ditusmartfoodbackend.service.domain.category.CategoryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryDomainService categoryDomainService;

    @Override
    public CategoryAdminResponse create(CategoryAdminRequest request) {
        return categoryMapper.toCategoryAdminResponse(categoryDomainService.createCategory(categoryMapper.toCategory(request)));
    }

    @Override
    public CategoryAdminResponse getById(String id) {
        return categoryMapper.toCategoryAdminResponse(categoryDomainService.getByIdOrThrow(id));
    }

    @Override
    public Page<CategoryAdminResponse> getCategoriesPagination(Pageable pageable) {
        return categoryMapper.toCategoryAdminResponses(categoryDomainService.getCategories(pageable));
    }


    @Override
    public CategoryAdminResponse update(String id, CategoryAdminRequest request) {
        Category category = categoryDomainService.getByIdOrThrow(id);

        categoryMapper.update(request, category);

        return categoryMapper.toCategoryAdminResponse(categoryDomainService.createCategory(category));
    }

    @Override
    public void delete(String id) {
        categoryDomainService.deleteCategoryById(id);
    }

}
