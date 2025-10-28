package com.tranngocqui.ditusmartfoodbackend.service.application.item;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.entity.Item;
import com.tranngocqui.ditusmartfoodbackend.mapper.ItemMapper;
import com.tranngocqui.ditusmartfoodbackend.service.domain.item.ItemDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemMapper itemMapper;
    private final ItemDomainService itemDomainService;

    @Override
    public Page<ItemAdminResponse> getAll(Pageable pageable) {
        return itemDomainService.getItems(pageable).map(itemMapper::toItemAdminResponse);
    }

    @Override
    public ItemAdminResponse getById(String id) {
        return itemMapper.toItemAdminResponse(itemDomainService.getByIdOrThrow(id));
    }

    @Override
    public ItemAdminResponse create(ItemAdminRequest request) {
        return itemMapper.toItemAdminResponse(itemDomainService.createItem(itemMapper.toItem(request)));
    }

    @Override
    public ItemAdminResponse update(String id, ItemAdminUpdateRequest request) {
        Item item = itemDomainService.getByIdOrThrow(id);

        itemMapper.update(request, item);

        return itemMapper.toItemAdminResponse(itemDomainService.createItem(item));
    }

    @Override
    public void deleteById(String id) {
        itemDomainService.deleteItemById(id);
    }

}
