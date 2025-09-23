package com.tranngocqui.ditusmartfoodbackend.service.menuitem;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem.MenuItemAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem.MenuItemAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.MenuItemClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuItemService {
    List<MenuItemClientResponse> getAll();

    MenuItemAdminResponse create(MenuItemAdminRequest menuItemRequest);

    Page<MenuItemClientResponse> getMenuItemPaginationByCategoryId(Pageable pageable, Long categoryId);

    Page<MenuItemAdminResponse> getMenuItemsPagination(Pageable pageable);

    MenuItem findById(Long id);
}
