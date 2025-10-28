package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CategoryClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    Category toCategory(CategoryAdminRequest categoryAdminRequest);

    CategoryAdminResponse toCategoryAdminResponse(Category category);

    void update(CategoryAdminRequest categoryAdminRequest, @MappingTarget Category category);

    List<CategoryClientResponse> toCategoryClientResponse(List<Category> category);

    default Page<CategoryAdminResponse> toCategoryAdminResponses(Page<Category> categoryPage) {
        return categoryPage.map(this::toCategoryAdminResponse);
    }
}
