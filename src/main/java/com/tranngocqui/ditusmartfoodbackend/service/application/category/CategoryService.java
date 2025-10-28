package com.tranngocqui.ditusmartfoodbackend.service.application.category;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CategoryClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryAdminResponse create(CategoryAdminRequest request);

    CategoryAdminResponse getById(String id);

    Page<CategoryAdminResponse> getCategoriesPagination(Pageable pageable);

    CategoryAdminResponse update(String id, CategoryAdminRequest request);

    void delete(String id);
//    List<CategoryAdminResponse> findAll();
}
