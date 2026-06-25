package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.product;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response.ProductClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Product;
import com.tranngocqui.ditusmartfoodbackend.mapper.ProductMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.ProductRepository;
import com.tranngocqui.ditusmartfoodbackend.service.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public Page<ProductAdminResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> saveAll(List<Product> list) {
        return productRepository.saveAll(list);
    }

    @Override
    public ProductAdminResponse getById(String id) {
        return null;
    }

    @Override
    public ProductAdminResponse create(ProductAdminRequest request) {
        return null;
    }

    @Override
    public ProductAdminResponse update(String id, ProductAdminUpdateRequest request) {
        return null;
    }

    @Override
    public void deleteById(String id) {
    }

    @Override
    public Page<ProductClientResponse> getByCategoryId(Pageable pageable, String categoryId) {
        return null;
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }

    @Override
    public Page<ProductClientResponse> getPagination(Pageable pageable, String categoryId) {
        return null;
    }

    @Override
    public List<Product> findByIds(List<UUID> ids) {
        return productRepository.findByIds(ids);
    }

    @Override
    @Transactional
    public List<Product> findByIdsWithLock(List<UUID> ids) {
        return productRepository.findByIdIn(ids);
    }


}
