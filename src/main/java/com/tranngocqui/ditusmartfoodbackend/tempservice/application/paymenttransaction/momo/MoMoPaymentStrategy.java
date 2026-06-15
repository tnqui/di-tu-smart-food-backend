//package com.tranngocqui.ditusmartfoodbackend.tempservice.application.paymenttransaction.momo;
//
//import com.tranngocqui.ditusmartfoodbackend.tempservice.application.paymenttransaction.factory.PaymentStrategy;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class MoMoPaymentStrategy implements PaymentStrategy {
////    private final MoMoService moMoService;
////
////    @Override
////    public PaymentProvider getProvider() {
////        return PaymentProvider.MOMO;
////    }
////
////    @Override
////    public PaymentResponse pay(Order order) throws Exception {
////        String payUrl = moMoService.createPaymentRequest(order);
////        return PaymentResponse.builder()
////                .provider(PaymentProvider.MOMO)
////                .paymentUrl(payUrl)
////                .build();
////    }
//}
