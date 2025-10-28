package com.tranngocqui.ditusmartfoodbackend.service.domain.orderitem;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem.OrderItemCheckAvailableRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem.OrderItemCheckAvailableResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemDomainServiceImpl extends BaseService<OrderItem, UUID> implements OrderItemDomainService {
    public OrderItemDomainServiceImpl(JpaRepository<OrderItem, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.ORDER_ITEM_NOT_FOUND;
    }


    @Override
    public List<OrderItem> listItemAvailable(List<OrderItem> request) {
        return request.stream().map(item -> findOptionalById(item.getId()).orElse(null)).toList();
    }
}
