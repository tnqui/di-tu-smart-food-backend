package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/addresses")
@RequiredArgsConstructor
public class AddressAdminController {
    private final AddressService addressService;

    @PostMapping
    ApiResponse<AddressAdminResponse> create(@RequestBody AddressAdminRequest addressAdminRequest) {
        return ApiResponse.<AddressAdminResponse>builder()
                .result(addressService.create(addressAdminRequest))
                .build();
    }

    @GetMapping("{id}")
    ApiResponse<AddressAdminResponse> get(@PathVariable Long id) {
        return ApiResponse.<AddressAdminResponse>builder()
                .result(addressService.get(id))
                .build();
    }

    @GetMapping()
    ApiResponse<Page<AddressAdminResponse>> getPagination(Pageable pageable) {
        return ApiResponse.<Page<AddressAdminResponse>>builder()
                .result(addressService.getPagination(pageable))
                .build();
    }


    @PatchMapping("{id}")
    ApiResponse<AddressAdminResponse> update(@PathVariable Long id, @RequestBody AddressAdminRequest request) {
        return ApiResponse.<AddressAdminResponse>builder()
                .result(addressService.update(id, request))
                .build();
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        addressService.delete(id);
    }


}
