package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentQueryResponse {
    private PaymentStatus status;
    private String transactionId;
    private BigDecimal amount;
    private String errorMessage;
    private LocalDateTime paidAt;
}