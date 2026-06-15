package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class OrderAdminController {
//    private final OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<ApiResponse<OrderAdminResponse>> createOrder(@RequestBody CreateOrderRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(orderService.createOrder(request)));
//    }
//
//    @GetMapping
//    public ResponseEntity<ApiResponse<Page<OrderAdminResponse>>> getOrders(Pageable pageable) {
//        return ResponseEntity.ok(ApiResponse.success(orderService.getOrders(pageable)));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<OrderAdminResponse>> getOrderById(@PathVariable String id) {
//        return ResponseEntity.ok(ApiResponse.success(orderService.getOrderById(id)));
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<ApiResponse<OrderAdminResponse>> update(@PathVariable String id, @RequestBody OrderAdminUpdateRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(orderService.updateOrder(id, request)));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
//        orderService.deleteOrderById(id);
//        return ResponseEntity.ok(ApiResponse.success("Successfully deleted order"));
//    }

}
