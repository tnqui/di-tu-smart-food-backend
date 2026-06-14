package com.tranngocqui.ditusmartfoodbackend.service.domain.order;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.service.BaseServiceFactory;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDomainServiceImpl implements OrderDomainService {
    private final BaseService<Order, UUID> baseService;

    public OrderDomainServiceImpl(BaseServiceFactory baseServiceFactory, OrderRepository orderRepository) {
        this.baseService = baseServiceFactory.create(orderRepository);
    }

    @Override
    public Order getByIdOrThrow(String id) {
        return baseService.findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Order getByIdOrNull(String id) {
        return baseService.findOptionalById(UUIDUtils.parseUUUIDFromString(id)).orElse(null);
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return baseService.findAll(pageable);
    }

    @Override
    public void deleteOrderById(String id) {
        baseService.deleteById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Order createOrder(Order order) {
        return baseService.save(order);
    }

    @Override
    public List<Order> getAllByIds(List<String> ids) {
        return List.of();
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }
}
