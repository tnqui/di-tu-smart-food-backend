package com.tranngocqui.ditusmartfoodbackend.service.payment;

import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentServiceFactory paymentServiceFactory;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentServiceFactory paymentServiceFactory, OrderRepository orderRepository) {
        this.paymentServiceFactory = paymentServiceFactory;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public PaymentResponse processPayment(String orderId) throws Exception {
        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow(() -> new AppException(ErrorCode.ORDER_DOES_NOT_EXIST));

        PaymentProvider provider = order.getPaymentMethod().getName();

        PaymentStrategy strategy = paymentServiceFactory.getStrategy(provider);

        return strategy.pay(order);
    }

    List<User> users;
}