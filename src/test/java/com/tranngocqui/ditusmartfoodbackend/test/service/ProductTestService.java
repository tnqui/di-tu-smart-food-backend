package com.tranngocqui.ditusmartfoodbackend.test.service;

import com.tranngocqui.ditusmartfoodbackend.entity.BaseEntity;
import com.tranngocqui.ditusmartfoodbackend.entity.Product;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.test.record.CreateOrderStep1Request;
import com.tranngocqui.ditusmartfoodbackend.test.record.CreateOrderStep1Response;
import com.tranngocqui.ditusmartfoodbackend.test.repository.ProductTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductTestService {
    private final ProductTestRepository productTestRepository;

    public CreateOrderStep1Response validateItems(CreateOrderStep1Request request) {
        Map<UUID, Integer> mapIdQuantity = request.items().stream().collect(Collectors.groupingBy(CreateOrderStep1Request.OrderItem::productId, Collectors.summingInt(CreateOrderStep1Request.OrderItem::quantity)));
        List<Product> products = findByIdIn(mapIdQuantity.keySet().stream().toList());

        if (products.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_LIST_PRODUCT);
        }

        Map<UUID, Product> mapIdProduct = products.stream().filter(p -> p.getStock() > 0).collect(Collectors.toMap(BaseEntity::getId, p -> p));

        if (mapIdProduct.isEmpty()) {
            throw new AppException(ErrorCode.ALL_PRODUCTS_OUT_OF_STOCK);
        }

        Map<Boolean, List<CreateOrderStep1Request.OrderItem>> mapBinaryListItems = request.items().stream()
                .collect(Collectors.partitioningBy(oi -> {
                    Product p = mapIdProduct.get(oi.productId());
                    return p != null && p.getStock() >= oi.quantity();
                }));

        var validItems = mapBinaryListItems.get(true);
        var invalidItems = mapBinaryListItems.get(false);


        var response = CreateOrderStep1Response.builder().build();
        return response;
    }

    private List<Product> findByIdIn(List<UUID> ids) {
        return productTestRepository.findAllById(ids);
    }

}
