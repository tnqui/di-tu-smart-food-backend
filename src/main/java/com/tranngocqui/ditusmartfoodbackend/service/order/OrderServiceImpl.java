package com.tranngocqui.ditusmartfoodbackend.service.order;

import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.OrderItemRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.MenuItem;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.OrderMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.MenuItemRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
        Order order = orderMapper.toOrder(request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        order.setUser(user);

        order.setItems(new HashSet<>());

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                    .orElseThrow(() -> new AppException(ErrorCode.MENU_ITEM_NOT_FOUND));

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());

            BigDecimal price = itemRequest.getPriceAtOrderTime();
            orderItem.setPriceAtOrderTime(price != null ? price : menuItem.getPrice());

            orderItem.setOrder(order);

            order.getItems().add(orderItem);

            BigDecimal itemTotal = orderItem.getPriceAtOrderTime().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);
        }

        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setShippingFee(BigDecimal.ZERO);
        order.setShippingAddress(null);

        BigDecimal finalTotal = totalAmount.add(order.getShippingFee());

        order.setTotalAmount(finalTotal);

        return orderMapper.toCreateOrderResponse(orderRepository.save(order));
    }
}
