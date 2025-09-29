package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import com.tranngocqui.ditusmartfoodbackend.enums.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentTransactionRequest {
    private PaymentProvider provider;
    private String orderId;
    private String transactionId;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime paidAt;
    private LocalDateTime expiredAt;
    private String callbackData;
    private String errorMessage;
}
