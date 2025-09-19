package com.tranngocqui.ditusmartfoodbackend.service.category;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CategoryClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    CategoryAdminResponse create(CategoryAdminRequest request);

    Page<CategoryAdminResponse> getCategoriesPagination(Pageable pageable);

    List<CategoryClientResponse> getAll();
//    List<CategoryAdminResponse> findAll();
}
