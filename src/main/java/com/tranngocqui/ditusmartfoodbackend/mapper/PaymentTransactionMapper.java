package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentWebhookResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentTransactionMapper {


    PaymentTransaction toPaymentTransaction(PaymentWebhookResponse response);

    PaymentTransaction toPaymentTransaction(PaymentTransactionRequest request);

    PaymentTransactionRequest toPaymentTransactionRequest(PaymentWebhookResponse response);

    @Mapping(target = "orderId", source = "order.id")
    PaymentWebhookResponse toPaymentCallbackResponse(PaymentTransaction paymentTransaction);

    @Mapping(target = "order", ignore = true)
    PaymentTransactionResponse toPaymentTransactionResponse(PaymentTransaction paymentTransaction);

}
