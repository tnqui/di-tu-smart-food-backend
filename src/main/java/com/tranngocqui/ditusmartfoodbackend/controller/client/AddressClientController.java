package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.service.address.AddressService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressClientController {
    private final AddressService addressService;

    @GetMapping("/autocomplete")
    public ResponseEntity<ApiResponse<?>> autocomplete(
            @RequestParam("address")
            @NotBlank(message = "FIELD_NOT_BLANK")
            String address
    ) {
        return ResponseEntity.ok(
                ApiResponse.ok(addressService.autocomplete(address))
        );
    }

}
