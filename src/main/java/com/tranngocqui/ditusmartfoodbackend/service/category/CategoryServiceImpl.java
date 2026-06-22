package com.tranngocqui.ditusmartfoodbackend.service.category;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response.CategoryClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.CategoryMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryAdminResponse create(CategoryAdminRequest request) {
        return categoryMapper.toCategoryAdminResponse(save(categoryMapper.from(request)));
    }

    @Override
    public CategoryAdminResponse getById(String id) {
        return categoryMapper.toCategoryAdminResponse(findById(id));
    }

    @Override
    public Page<CategoryAdminResponse> getCategoriesPagination(Pageable pageable) {
        return categoryMapper.toCategoryAdminResponsePage(findAll(pageable));
    }

    @Override
    public CategoryAdminResponse update(String id, CategoryAdminRequest request) {
        Category dbCategory = findById(id);

        Category updateCategory = categoryMapper.update(dbCategory, request);

        Category savedCategory = save(updateCategory);

        return categoryMapper.toCategoryAdminResponse(savedCategory);
    }

    @Override
    public void delete(String id) {
        var category = findById(id);
        category.softDelete();
        save(category);
    }

    @Override
    public List<CategoryClientResponse> getAll() {
        return categoryMapper.toCategoryClientResponses(findAll());
    }

    private Category save(Category category) {
        return categoryRepository.save(category);
    }

    private List<Category> findAll() {
        return categoryRepository.findAll();
    }

    private Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    private Category findById(String id) {
        return categoryRepository.findById(UUID.fromString(id)).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    private void deleteById(String id) {
        Category category = findById(id);
        category.softDelete();
        save(category);
    }

}
