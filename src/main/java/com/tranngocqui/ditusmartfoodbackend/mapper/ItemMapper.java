package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemMapper {
    @Mapping(target = "categories", ignore = true)
    Item toItem(ItemAdminRequest itemAdminRequest);

    @Mapping(target = "categories", ignore = true)
    ItemAdminResponse toItemAdminResponse(Item item);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "primaryCategory", ignore = true)
    void update(ItemAdminUpdateRequest request, @MappingTarget Item item);
//
//    List<MenuItemClientResponse> toMenuItemClientResponse(List<Item> menuItems);
//
//    @Mapping(target = "categories", ignore = true)
//    Item toMenuItem(ItemAdminRequest ItemAdminRequest);
//
//    ItemAdminResponse toMenuItemAdminResponse(MenuItem menuItem);
//
//    MenuItemClientResponse toMenuItemClientResponse(MenuItem menuItem);
//
//    default Page<ItemAdminResponse> toMenuItemAdminResponsePage(Page<MenuItem> menuItemPage) {
//        return menuItemPage.map(this::toMenuItemAdminResponse);
//    }
//
//    default Page<MenuItemClientResponse> toMenuItemClientResponsePage(Page<MenuItem> menuItemPage) {
//        return menuItemPage.map(this::toMenuItemClientResponse);
//    }
}
