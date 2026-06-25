package com.tranngocqui.ditusmartfoodbackend.service.product;


import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response.ProductClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    Page<ProductAdminResponse> getAll(Pageable pageable);

    List<Product> saveAll(List<Product> list);

    ProductAdminResponse getById(String id);

    ProductAdminResponse create(ProductAdminRequest menuProductRequest);

    ProductAdminResponse update(String id, ProductAdminUpdateRequest menuProductRequest);

    void deleteById(String id);

    Page<ProductClientResponse> getByCategoryId(Pageable pageable, String categoryId);

    Product getProductById(String id);

    Page<ProductClientResponse> getPagination(Pageable pageable, String categoryId);

    List<Product> findByIds(List<UUID> ids);

    List<Product> findByIdsWithLock(List<UUID> ids);
}
