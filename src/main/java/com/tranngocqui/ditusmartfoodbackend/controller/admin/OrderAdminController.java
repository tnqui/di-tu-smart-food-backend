package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.service.application.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.http.HttpStatusCode;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class OrderAdminController {
    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderAdminCreateResponse> createOrder(@RequestBody OrderAdminCreateRequest request) {
        return ApiResponse.<OrderAdminCreateResponse>builder()
                .code(HttpStatusCode.CREATED)
                .message("Successfully created order")
                .result(orderService.createOrder(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<OrderAdminResponse>> getOrders(Pageable pageable) {
        return ApiResponse.<Page<OrderAdminResponse>>builder()
                .result(orderService.getOrders(pageable))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<OrderAdminResponse> getOrderById(@PathVariable String id) {
        return ApiResponse.<OrderAdminResponse>builder()
                .result(orderService.getOrderById(id))
                .build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<OrderAdminResponse> update(@PathVariable String id, @RequestBody OrderAdminUpdateRequest request) {
        return ApiResponse.<OrderAdminResponse>builder()
                .result(orderService.updateOrder(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable String id) {
        orderService.deleteOrderById(id);
        return ApiResponse.<Void>builder()
                .message("Successfully deleted order")
                .build();
    }

}
