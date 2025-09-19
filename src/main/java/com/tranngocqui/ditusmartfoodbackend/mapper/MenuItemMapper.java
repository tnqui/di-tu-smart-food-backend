package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem.MenuItemAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem.MenuItemAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.MenuItemClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuItemMapper {

    List<MenuItemClientResponse> toMenuItemClientResponse(List<MenuItem> menuItems);

    @Mapping(target = "categories", ignore = true)
    MenuItem toMenuItem(MenuItemAdminRequest MenuItemAdminRequest);

    MenuItemAdminResponse toMenuItemAdminResponse(MenuItem menuItem);

    MenuItemClientResponse toMenuItemClientResponse(MenuItem menuItem);

    default Page<MenuItemAdminResponse> toMenuItemAdminResponsePage(Page<MenuItem> menuItemPage) {
        return menuItemPage.map(this::toMenuItemAdminResponse);
    }

    default Page<MenuItemClientResponse> toMenuItemClientResponsePage(Page<MenuItem> menuItemPage) {
        return menuItemPage.map(this::toMenuItemClientResponse);
    }
}
