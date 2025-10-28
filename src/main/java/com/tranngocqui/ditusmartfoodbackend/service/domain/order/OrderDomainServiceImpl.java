package com.tranngocqui.ditusmartfoodbackend.service.domain.order;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;
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
public class OrderDomainServiceImpl extends BaseService<Order, UUID> implements OrderDomainService {
    public OrderDomainServiceImpl(JpaRepository<Order, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.ORDER_NOT_FOUND;
    }


    @Override
    public Order getByIdOrThrow(String id) {
        return findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Order getByIdOrNull(String id) {
        return findOptionalById(UUIDUtils.parseUUUIDFromString(id)).orElse(null);
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    public void deleteOrderById(String id) {
        deleteById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Order createOrder(Order order) {
        return save(order);
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
