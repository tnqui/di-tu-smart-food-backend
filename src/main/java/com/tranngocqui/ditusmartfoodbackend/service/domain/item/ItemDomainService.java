package com.tranngocqui.ditusmartfoodbackend.service.domain.item;

import com.tranngocqui.ditusmartfoodbackend.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface ItemDomainService {
    Item getByIdOrThrow(String id);

    Item getByIdOrNull(String id);

    Page<Item> getItems(Pageable pageable);

    void deleteItemById(String id);

    Item createItem(Item item);

    List<Item> getAllByIds(List<String> ids);

    Item updateItem(Item item);
}
