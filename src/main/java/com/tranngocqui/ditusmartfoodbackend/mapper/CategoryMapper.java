package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response.CategoryClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {
    public Category update(Category dbCategory, CategoryAdminRequest request) {
        return dbCategory.toBuilder()
                .name(request.name() != null ? request.name() : dbCategory.getName())
                .description(request.description() != null ? request.description() : dbCategory.getDescription())
                .build();
    }


    public Page<CategoryAdminResponse> toCategoryAdminResponsePage(Page<Category> categories) {
        return categories.map(this::toCategoryAdminResponse);
    }

    public CategoryAdminResponse toCategoryAdminResponse(Category category) {
        return CategoryAdminResponse.builder()
                .id(String.valueOf(category.getId()))
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .name(category.getName())
                .build();
    }

    public Category from(CategoryAdminRequest request) {
        return Category.builder()
                .description(request.description())
                .name(request.name())
                .build();
    }

    public CategoryClientResponse toCategoryClientResponse(Category category) {
        return CategoryClientResponse.builder().build();
    }


    public List<CategoryClientResponse> toCategoryClientResponses(List<Category> categories) {
        return categories.stream().map(this::toCategoryClientResponse).toList();
    }


}
