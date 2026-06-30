package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryFailReason;
import com.tranngocqui.ditusmartfoodbackend.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class OrderAdminController {
    private final OrderService orderService;

    @PatchMapping("/restaurant-confirm/{orderId}")
    public ResponseEntity<ApiResponse<Void>> restaurantConfirmOrder(@PathVariable String orderId) {
        orderService.restaurantConfirmOrder(orderId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PatchMapping("/prepare/{orderId}")
    public ResponseEntity<ApiResponse<Void>> prepareOrder(@PathVariable String orderId) {
        orderService.prepareOrder(orderId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PatchMapping("/ready/{orderId}")
    public ResponseEntity<ApiResponse<Void>> orderReadyForDelivery(@PathVariable String orderId) {
        orderService.orderReady(orderId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PatchMapping("/delivering/{orderId}")
    public ResponseEntity<ApiResponse<Void>> deliveryOrder(@PathVariable String orderId) {
        orderService.orderDelivering(orderId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PatchMapping("/arrived/{orderId}")
    public ResponseEntity<ApiResponse<Void>> arrived(@PathVariable String orderId) {
        orderService.orderArrived(orderId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PatchMapping("/delivered/{orderId}")
    public ResponseEntity<ApiResponse<Void>> delivered(@PathVariable String orderId) {
        orderService.orderDelivered(orderId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PatchMapping("/delivery-failed/{orderId}")
    public ResponseEntity<ApiResponse<Void>> deliveryFail(@PathVariable String orderId, @RequestParam DeliveryFailReason reason) {
        orderService.orderDeliveryFailed(orderId, reason);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PatchMapping("/re-delivering/{orderId}")
    public ResponseEntity<ApiResponse<Void>> reDelivering(@PathVariable String orderId) {
        orderService.redelivering(orderId);
        return ResponseEntity.ok(ApiResponse.ok());
    }


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
