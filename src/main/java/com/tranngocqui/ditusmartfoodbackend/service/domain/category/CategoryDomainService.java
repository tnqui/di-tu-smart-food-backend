package com.tranngocqui.ditusmartfoodbackend.service.domain.category;

import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryDomainService {
    Category getByIdOrThrow(String id);

    Page<Category> getCategories(Pageable pageable);

    void deleteCategoryById(String id);

    Category createCategory(Category category);
}
