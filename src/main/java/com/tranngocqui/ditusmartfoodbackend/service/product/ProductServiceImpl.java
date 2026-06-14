package com.tranngocqui.ditusmartfoodbackend.service.product;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.ProductClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Product;
import com.tranngocqui.ditusmartfoodbackend.mapper.ProductMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.ProductRepository;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.service.BaseServiceFactory;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final BaseService<Product, UUID> productBaseService;

    public ProductServiceImpl(ProductMapper productMapper, BaseServiceFactory factory, ProductRepository ProductRepository) {
        this.productMapper = productMapper;
        this.productBaseService = factory.create(ProductRepository);
    }

    @Override
    public Page<ProductAdminResponse> getAll(Pageable pageable) {
        return productBaseService.findAll(pageable).map(productMapper::toProductAdminResponse);
    }

    @Override
    public ProductAdminResponse getById(String id) {
        return productMapper.toProductAdminResponse(productBaseService.findByIdOrThrow(UUID.fromString(id)));
    }

    @Override
    public ProductAdminResponse create(ProductAdminRequest request) {
        return productMapper.toProductAdminResponse(productBaseService.save(productMapper.toProduct(request)));
    }

    @Override
    public ProductAdminResponse update(String id, ProductAdminUpdateRequest request) {
        Product Product = productBaseService.findByIdOrThrow(UUID.fromString(id));

        productMapper.update(request, Product);

        return productMapper.toProductAdminResponse(productBaseService.save(Product));
    }

    @Override
    public void deleteById(String id) {
        productBaseService.deleteById(UUID.fromString(id));
    }

    @Override
    public Page<ProductClientResponse> getByCategoryId(Pageable pageable, String categoryId) {
        return productBaseService.findAll(pageable).map(product -> productMapper.toProductClientResponse(productBaseService.findByIdOrThrow(UUID.fromString(categoryId))));
    }

    @Override
    public Product getProductById(String id) {
        return productBaseService.findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Page<ProductClientResponse> getPagination(Pageable pageable, String categoryId) {
        return null;
    }

}
