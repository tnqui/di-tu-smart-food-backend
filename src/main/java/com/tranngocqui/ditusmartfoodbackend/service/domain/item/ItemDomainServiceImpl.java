package com.tranngocqui.ditusmartfoodbackend.service.domain.item;

import com.tranngocqui.ditusmartfoodbackend.entity.Item;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemDomainServiceImpl extends BaseService<Item, UUID> implements ItemDomainService {
    public ItemDomainServiceImpl(JpaRepository<Item, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.ITEM_NOT_FOUND;
    }

    @Override
    public Item getByIdOrThrow(String id) {
        return findByIdOrThrow(UUID.fromString(id));
    }

    @Override
    public Item getByIdOrNull(String id) {
        return findOptionalById(UUIDUtils.parseUUUIDFromString(id)).orElse(null);
    }

    @Override
    public Page<Item> getItems(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    public void deleteItemById(String id) {
        deleteById(UUID.fromString(id));
    }

    @Override
    public Item createItem(Item item) {
        return save(item);
    }

    @Override
    public List<Item> getAllByIds(List<String> ids) {
        return findAllById(UUIDUtils.fromListString(ids));
    }

    @Override
    public Item updateItem(Item item) {
        return null;
    }
}
