package com.tranngocqui.ditusmartfoodbackend.controller.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderClientController {
//
//    private final OrderService orderService;
//
//    @PostMapping("/order")
//    ApiResponse<CreateOrderResponse> create(@RequestBody CreateOrderRequest request) {
//        return ApiResponse.<CreateOrderResponse>builder()
//                .result(orderService.create(request))
//                .build();
//    }
//
//    @GetMapping("{orderId}")
//    ApiResponse<OrderAdminResponse> getOrder(@PathVariable String orderId) {
//        return ApiResponse.<OrderAdminResponse>builder()
//                .result(orderService.getOrderByOrderId(orderId))
//                .build();
//    }

//    @PostMapping("/order")
//    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
//        return ResponseEntity.ok(orderService.order(createOrderRequest));
//    }


}
