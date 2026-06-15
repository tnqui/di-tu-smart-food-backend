//package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.category;
//
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
//import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CategoryClientResponse;
//import com.tranngocqui.ditusmartfoodbackend.entity.Category;
//import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseService;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseServiceFactory;
//import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.UUID;
//
//@Slf4j
//@Service
//public class CategoryServiceImpl implements CategoryService {
//    private final CategoryMapper categoryMapper;
//    private final BaseService<Category, UUID> categoryBaseService;
//
//    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository, BaseServiceFactory factory) {
//        this.categoryMapper = categoryMapper;
//        this.categoryBaseService = factory.create(categoryRepository);
//    }
//
//    @Override
//    public CategoryAdminResponse create(CategoryAdminRequest request) {
//        return categoryMapper.toCategoryAdminResponse(categoryBaseService.save(categoryMapper.toCategory(request)));
//    }
//
//    @Override
//    public CategoryAdminResponse getById(String id) {
//        return categoryMapper.toCategoryAdminResponse(categoryBaseService.findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id)));
//    }
//
//    @Override
//    public Page<CategoryAdminResponse> getCategoriesPagination(Pageable pageable) {
//        return categoryMapper.toCategoryAdminResponses(categoryBaseService.findAll(pageable));
//    }
//
//
//    @Override
//    public CategoryAdminResponse update(String id, CategoryAdminRequest request) {
//        Category category = categoryBaseService.findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
//
//        categoryMapper.update(request, category);
//
//        return categoryMapper.toCategoryAdminResponse(categoryBaseService.save(category));
//    }
//
//    @Override
//    public void delete(String id) {
//        categoryBaseService.deleteById(UUIDUtils.parseUUUIDFromString(id));
//    }
//
//    @Override
//    public List<CategoryClientResponse> getAll() {
//        return categoryBaseService.findAll().stream().map(categoryMapper::toCategoryClientResponse).toList();
//    }
//
//}
