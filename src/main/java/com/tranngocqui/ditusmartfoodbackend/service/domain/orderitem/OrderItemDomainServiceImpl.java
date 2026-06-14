package com.tranngocqui.ditusmartfoodbackend.service.domain.orderitem;

import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderItemRepository;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.service.BaseServiceFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemDomainServiceImpl implements OrderItemDomainService {
    private final BaseService<OrderItem, UUID> baseService;

    public OrderItemDomainServiceImpl(BaseServiceFactory baseServiceFactory, OrderItemRepository orderItemRepository) {
        this.baseService = baseServiceFactory.create(orderItemRepository);
    }

    @Override
    public List<OrderItem> listItemAvailable(List<OrderItem> request) {
        return request.stream().map(item -> baseService.findOptionalById(item.getId()).orElse(null)).toList();
    }
}
