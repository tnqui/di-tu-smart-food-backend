package com.tranngocqui.ditusmartfoodbackend.test;

import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderConfirmRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderValidationRequest;
import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.service.order.OrderService;
import com.tranngocqui.ditusmartfoodbackend.test.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class TestApp {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private OrderService orderService;

    @Test
    public void testOrder1() {
        OrderValidationRequest request = OrderValidationRequest.builder()
                .requestItems(List.of(
                        OrderValidationRequest.OrderItemRequest.builder()
                                .productId(UUID.fromString("770e8400-e29b-41d4-a716-446655440003"))
                                .quantity(3)
                                .build(),
                        OrderValidationRequest.OrderItemRequest.builder()
                                .productId(UUID.fromString("770e8400-e29b-41d4-a716-446655440002"))
                                .quantity(2)
                                .build(),
                        OrderValidationRequest.OrderItemRequest.builder()
                                .productId(UUID.fromString("770e8400-e29b-41d4-a716-446655440004"))
                                .quantity(100)
                                .build(),
                        OrderValidationRequest.OrderItemRequest.builder()
                                .productId(UUID.fromString("770e8400-e29b-41d4-a716-446655440006"))
                                .quantity(100)
                                .build(),
                        OrderValidationRequest.OrderItemRequest.builder()
                                .productId(UUID.fromString("770e8400-e29b-41d4-a716-446655440022"))
                                .quantity(2)
                                .build()))
                .build();

        var response = orderService.validate(request);
    }

    @Test
    public void testConfirm() {
        OrderConfirmRequest request = OrderConfirmRequest.builder()
                .refId("auto:RAkPYDcwcHd5XFBRCh4MUVQCHVFTVQRLBgZWAgQBVFYDXlNSBlBQVVVQUVZPVgQDBEsMBQcATFBQDgdPCFIBUAMMBFUGXVADBFMOCKfy84LM3a8MVQ3wpPbDQkIS8NINRfPIUQ4VVwtXCRhQClYMAVBZBAIbSABSBBgNDABTHVJRBlIcCnYEXhVQ")
                .note("")
                .paymentMethod(PaymentMethod.COD)
                .deliveryMethod(DeliveryMethod.IN_HOUSE)
                .build();
        String orderId = "28062026ORDER019F0F8A";

        orderService.customerConfirmOrder(orderId, request);
    }


}
