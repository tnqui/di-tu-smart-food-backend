package com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction.factory;

import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentTransaction;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentStatus;
import com.tranngocqui.ditusmartfoodbackend.enums.TransactionStatus;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.PaymentTransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentServiceFactory paymentServiceFactory;
    private final OrderRepository orderRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentService(PaymentServiceFactory paymentServiceFactory, OrderRepository orderRepository, PaymentTransactionRepository paymentTransactionRepository) {
        this.paymentServiceFactory = paymentServiceFactory;
        this.orderRepository = orderRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    @Transactional
    public PaymentResponse processPayment(String orderId) throws Exception {
        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow(() -> new AppException(ErrorCode.ORDER_DOES_NOT_EXIST));

        PaymentProvider provider = order.getPaymentMethod().getName();

        PaymentStrategy strategy = paymentServiceFactory.getStrategy(provider);

        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setOrder(order);
        paymentTransaction.setTransactionId(UUID.randomUUID().toString());
        paymentTransaction.setAmount(order.getTotalAmount());
        paymentTransaction.setProvider(provider);
        paymentTransaction.setStatus(TransactionStatus.PENDING);

        paymentTransactionRepository.save(paymentTransaction);

        PaymentResponse response = strategy.pay(order);

        if (response == null) {
            paymentTransaction.setStatus(TransactionStatus.FAILED);
            paymentTransactionRepository.save(paymentTransaction);
        }

        return response;
    }

}