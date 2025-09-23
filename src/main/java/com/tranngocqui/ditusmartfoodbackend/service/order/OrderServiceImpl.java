package com.tranngocqui.ditusmartfoodbackend.service.order;

import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.*;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.OrderMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.MenuItemRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.PaymentMethodRepository;
import com.tranngocqui.ditusmartfoodbackend.service.deliverymethod.DeliveryMethodService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final DeliveryMethodService deliveryMethodService;
    private final PaymentMethodRepository paymentMethodRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
        DeliveryMethod deliveryMethod = deliveryMethodService.findById(request.getDeliveryMethodId());

        Order order = orderMapper.toOrder(request);

        order.setDeliveryMethod(deliveryMethod);

        User user = userService.findById(UUID.fromString(request.getUserId()));

        order.setUser(user);

        List<OrderItem> items = request.getItems().stream()
                .map(itemRequest -> {
                    MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId()).orElseThrow(() -> new AppException(ErrorCode.MENU_ITEM_NOT_FOUND));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setMenuItem(menuItem);
                    orderItem.setQuantity(itemRequest.getQuantity());
                    orderItem.setPriceAtOrderTime(menuItem.getPrice());

                    return orderItem;
                })
                .toList();

        order.setItems(items);

        order.setDeliveryMethod(deliveryMethodService.findById(request.getDeliveryMethodId()));

        order.setShippingFee(order.getDeliveryMethod().getPrice());

        BigDecimal totalAmount = request.getItems().stream()
                .map(itemRequest -> {
                    MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId()).orElseThrow(() -> new AppException(ErrorCode.MENU_ITEM_NOT_FOUND));

                    BigDecimal price = menuItem.getPrice();
                    int quantity = itemRequest.getQuantity();

                    return price.multiply(BigDecimal.valueOf(quantity));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(order.getShippingFee() == null ? BigDecimal.ZERO : order.getShippingFee());

        order.setTotalAmount(totalAmount);

        order.setPaymentMethod(paymentMethodRepository.findById(request.getPaymentMethodId()).orElseThrow(() -> new AppException(ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED)));

        return orderMapper.toCreateOrderResponse(orderRepository.save(order));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    }

    @Override
    public Order order(CreateOrderRequest request) {
        Order order = orderMapper.toOrder(request);

        order.setUser(userService.findById(UUID.fromString(request.getUserId())));

        order.setDeliveryMethod(deliveryMethodService.findById(request.getDeliveryMethodId()));

        order.setShippingFee(order.getDeliveryMethod().getPrice());

        return orderRepository.save(order);
    }

    @Override
    public void processAfterPayment(Order order) {

    }
}
