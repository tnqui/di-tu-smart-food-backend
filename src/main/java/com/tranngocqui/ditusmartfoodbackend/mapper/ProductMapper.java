package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.ProductClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mapping(target = "categories", ignore = true)
    Product toProduct(ProductAdminRequest productAdminRequest);

    @Mapping(target = "categories", ignore = true)
    ProductAdminResponse toProductAdminResponse(Product product);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "primaryCategory", ignore = true)
    void update(ProductAdminUpdateRequest request, @MappingTarget Product product);
//

    ProductClientResponse toProductClientResponse(Product product);
//    List<MenuProductClientResponse> toMenuProductClientResponse(List<Product> menuProducts);
//
//    @Mapping(target = "categories", ignore = true)
//    Product toMenuProduct(ProductAdminRequest ProductAdminRequest);
//
//    ProductAdminResponse toMenuProductAdminResponse(MenuProduct menuProduct);
//
//    MenuProductClientResponse toMenuProductClientResponse(MenuProduct menuProduct);
//
//    default Page<ProductAdminResponse> toMenuProductAdminResponsePage(Page<MenuProduct> menuProductPage) {
//        return menuProductPage.map(this::toMenuProductAdminResponse);
//    }
//
//    default Page<MenuProductClientResponse> toMenuProductClientResponsePage(Page<MenuProduct> menuProductPage) {
//        return menuProductPage.map(this::toMenuProductClientResponse);
//    }
}
