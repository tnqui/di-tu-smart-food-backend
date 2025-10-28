package com.tranngocqui.ditusmartfoodbackend.service.application.item;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Page<ItemAdminResponse> getAll(Pageable pageable);

    ItemAdminResponse getById(String id);

    ItemAdminResponse create(ItemAdminRequest menuItemRequest);

    ItemAdminResponse update(String id, ItemAdminUpdateRequest menuItemRequest);

    void deleteById(String id);
}
