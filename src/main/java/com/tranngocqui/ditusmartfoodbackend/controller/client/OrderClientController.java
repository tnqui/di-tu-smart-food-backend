package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderConfirmRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderValidationRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderValidationResponse;
import com.tranngocqui.ditusmartfoodbackend.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderClientController {

    private final OrderService orderService;

    @PostMapping("/validate")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<ApiResponse<OrderValidationResponse>> validate(@Valid @RequestBody OrderValidationRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(orderService.validate(request)));
    }

    @PostMapping("/confirm/{orderId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<ApiResponse<?>> confirm(@PathVariable String orderId, @Valid @RequestBody OrderConfirmRequest request) {
        orderService.customerConfirmOrder(orderId, request);
        return ResponseEntity.ok(ApiResponse.ok("Successfully confirmed order!"));
    }


}
