package com.tranngocqui.ditusmartfoodbackend.service.category;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    CategoryAdminResponse create(CategoryAdminRequest request);

    List<CategoryResponse> getAll();
//    List<CategoryAdminResponse> findAll();
}
