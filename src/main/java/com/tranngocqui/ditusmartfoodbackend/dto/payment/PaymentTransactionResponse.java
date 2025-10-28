package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class PaymentTransactionResponse {
    private UUID id;

    private Order order;

    private String provider;

    private String transactionId;

    private BigDecimal amount;

    private String status;

    private Instant createdAt;

    private Instant paidAt;

    private Instant expiredAt;

    private String callbackData;

    private String errorMessage;

}
