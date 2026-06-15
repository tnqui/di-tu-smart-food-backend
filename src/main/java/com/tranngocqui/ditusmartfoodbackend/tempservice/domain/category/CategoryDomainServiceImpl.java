package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.category;

import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseService;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseServiceFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryDomainServiceImpl implements CategoryDomainService {
    private final CategoryRepository categoryRepository;
    private final BaseService<Category, UUID> baseService;

    public CategoryDomainServiceImpl(CategoryRepository categoryRepository, BaseServiceFactory factory) {
        this.categoryRepository = categoryRepository;
        this.baseService = factory.create(categoryRepository);
    }

    @Override
    public Category getByIdOrThrow(String id) {
        return baseService.findByIdOrThrow(UUID.fromString(id));
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return baseService.findAll(pageable);
    }

    @Override
    public void deleteCategoryById(String id) {
        baseService.deleteById(UUID.fromString(id));
    }

    @Override
    public Category createCategory(Category category) {
        return baseService.save(category);
    }
}
