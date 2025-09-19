package com.tranngocqui.ditusmartfoodbackend.service.menuitem;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem.MenuItemAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem.MenuItemAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.MenuItemClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.MenuItem;
import com.tranngocqui.ditusmartfoodbackend.mapper.MenuItemMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.CategoryRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<MenuItemClientResponse> getAll() {
        return menuItemMapper.toMenuItemClientResponse(menuItemRepository.findAll());
    }

    @Override
    public MenuItemAdminResponse create(MenuItemAdminRequest menuItemRequest) {
        MenuItem menuItem = menuItemMapper.toMenuItem(menuItemRequest);

        var listValidCategory = categoryRepository.findAllById(menuItemRequest.getCategories());
        menuItem.setCategories(new HashSet<>(listValidCategory));

        return menuItemMapper.toMenuItemAdminResponse(menuItemRepository.save(menuItem));
    }

    @Override
    public Page<MenuItemClientResponse> getMenuItemPaginationByCategoryId(Pageable pageable, Long categoryId) {
        Sort customSort = Sort.by(
                Sort.Order.desc("orderCount"),
                Sort.Order.desc("rating"),
                Sort.Order.desc("createdAt")
        );

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                customSort
        );

        Page<MenuItem> menuItemPage;

        if (categoryId != null) {
            menuItemPage = menuItemRepository.findByEnabledTrueAndStockGreaterThanAndCategoryId(0, categoryId, sortedPageable);
        } else {
            menuItemPage = menuItemRepository.findByEnabledTrueAndStockGreaterThan(0, sortedPageable);
        }

        return menuItemMapper.toMenuItemClientResponsePage(menuItemPage);
    }

    @Override
    public Page<MenuItemAdminResponse> getMenuItemsPagination(Pageable pageable) {
        return menuItemMapper.toMenuItemAdminResponsePage(menuItemRepository.findAll(pageable));
    }
}
