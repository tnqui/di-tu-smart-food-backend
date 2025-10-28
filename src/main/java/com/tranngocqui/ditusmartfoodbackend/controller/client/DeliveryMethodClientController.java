package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.service.application.deliverymethod.DeliveryMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery-methods")
@RequiredArgsConstructor
public class DeliveryMethodClientController {
    private final DeliveryMethodService deliveryMethodService;

//    @GetMapping("/all")
//    ApiResponse<List<DeliveryMethodAdminResponse>> getAll(Pageable pageable) {
//        return ApiResponse.<List<DeliveryMethodAdminResponse>>builder()
//                .result(deliveryMethodService.getPagination(pageable))
//                .build();
//
//    }
}