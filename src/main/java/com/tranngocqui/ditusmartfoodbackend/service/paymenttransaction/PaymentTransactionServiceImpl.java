package com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction;

import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentWebhookResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentTransaction;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentStatus;
import com.tranngocqui.ditusmartfoodbackend.enums.TransactionStatus;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.PaymentTransactionMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.PaymentTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentTransactionServiceImpl implements PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final PaymentTransactionMapper paymentTransactionMapper;
    private final OrderRepository orderRepository;

    @Override
    public PaymentTransaction findByTransactionId(UUID transactionId) {
        return null;
    }

    @Override
    public PaymentTransaction save(PaymentTransactionRequest request) {
        PaymentTransaction paymentTransaction = paymentTransactionMapper.toPaymentTransaction(request);
        return null;
    }

    @Override
    public PaymentTransaction update(PaymentTransaction transaction) {
        return null;
    }

    @Override
    public PaymentTransaction update(PaymentWebhookResponse response) {
        return null;
    }

    @Override
    public PaymentTransactionResponse updateFromCallbackData(PaymentWebhookResponse response) {
        PaymentTransaction paymentTransaction = new PaymentTransaction();

        paymentTransaction.setAmount(new BigDecimal(response.getAmount()));

        paymentTransaction.setTransactionId(String.valueOf(response.getTransId()));

        paymentTransaction.setOrder(orderRepository.findById(UUID.fromString(response.getOrderId()))
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND)));

        if (response.getResultCode() == 0) {
            paymentTransaction.setStatus(TransactionStatus.COMPLETED);
        } else {
            paymentTransaction.setStatus(TransactionStatus.FAILED);
        }

        return paymentTransactionMapper.toPaymentTransactionResponse(paymentTransactionRepository.save(paymentTransaction));
    }
}
