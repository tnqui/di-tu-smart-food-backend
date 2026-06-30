package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductNameStockResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.projection.ProductNameStock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public List<ProductNameStockResponse> to(List<ProductNameStock> list) {
        return list.stream().map(this::to).toList();
    }

    public ProductNameStockResponse to(ProductNameStock p) {
        return ProductNameStockResponse.builder()
                .productId(p.getId())
                .stock(p.getStock())
                .productName(p.getName())
                .build();
    }


}
