package com.tranngocqui.ditusmartfoodbackend.service.category;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CategoryClientResponse;
import com.tranngocqui.ditusmartfoodbackend.mapper.CategoryMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<CategoryAdminResponse> getCategoriesPagination(Pageable pageable) {
        return categoryMapper.toCategoryAdminResponses(categoryRepository.findAll(pageable));
    }

    @Override
    public List<CategoryClientResponse> getAll() {
        return categoryMapper.toCategoryClientResponse(categoryRepository.findAll());
    }

}
