package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.service.deliverymethod.DeliveryMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-methods")
@RequiredArgsConstructor
public class DeliveryMethodClientController {
    private final DeliveryMethodService deliveryMethodService;

    @GetMapping("/all")
    ApiResponse<List<DeliveryMethodAdminResponse>> getAll() {
        return ApiResponse.<List<DeliveryMethodAdminResponse>>builder()
                .result(deliveryMethodService.getAll())
                .build();
    }
}
