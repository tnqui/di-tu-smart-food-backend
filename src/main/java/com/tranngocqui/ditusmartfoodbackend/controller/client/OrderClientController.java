package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderClientCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderClientCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderClientController {

    private final OrderService orderService;

    @PostMapping("/order")
    ResponseEntity<ApiResponse<OrderClientCreateResponse>> create(@Valid @RequestBody OrderClientCreateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(orderService.create(request)));
    }

//
//    @GetMapping("{orderId}")
//    ApiResponse<OrderAdminResponse> getOrder(@PathVariable String orderId) {
//        return ApiResponse.<OrderAdminResponse>builder()
//                .result(orderService.getOrderByOrderId(orderId))
//                .build();
//    }
//
//    @PostMapping("/order")
//    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
//        return ResponseEntity.ok(orderService.order(createOrderRequest));
//    }


}
