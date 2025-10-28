package com.tranngocqui.ditusmartfoodbackend.service.domain.category;

import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryDomainServiceImpl extends BaseService<Category, UUID> implements CategoryDomainService {
    public CategoryDomainServiceImpl(JpaRepository<Category, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.CATEGORY_NOT_FOUND;
    }

    @Override
    public Category getByIdOrThrow(String id) {
        return findByIdOrThrow(UUID.fromString(id));
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    public void deleteCategoryById(String id) {
        deleteById(UUID.fromString(id));
    }

    @Override
    public Category createCategory(Category category) {
        return save(category);
    }
}
