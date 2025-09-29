package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import com.tranngocqui.ditusmartfoodbackend.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentTransactionResponse {
    private UUID id;

    private Order order;

    private String provider;

    private String transactionId;

    private BigDecimal amount;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime paidAt;

    private LocalDateTime expiredAt;

    private String callbackData;

    private String errorMessage;

}
