package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.application.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/addresses")
@RequiredArgsConstructor
public class AddressAdminController {
    private final AddressService addressService;

    @GetMapping("{userId}")
    public ResponseEntity<ApiResponse<List<AddressAdminResponse>>> getAddressByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponse.success(addressService.getByUserId(userId)));
    }


    @PostMapping
    ResponseEntity<ApiResponse<AddressAdminResponse>> create(@RequestBody AddressAdminRequest addressAdminRequest) {
        return ResponseEntity.ok(ApiResponse.success(addressService.create(addressAdminRequest)));
    }


//
//    @GetMapping("{id}")
//    ApiResponse<AddressAdminResponse> get(@PathVariable String id) {
//        return ApiResponse.<AddressAdminResponse>builder()
//                .result(addressService.get(id))
//                .build();
//    }
//
//    @GetMapping()
//    ApiResponse<Page<AddressAdminResponse>> getPagination(Pageable pageable) {
//        return ApiResponse.<Page<AddressAdminResponse>>builder()
//                .result(addressService.getPagination(pageable))
//                .build();
//    }
//
//
//    @PatchMapping("{id}")
//    ApiResponse<AddressAdminResponse> update(@PathVariable String id, @RequestBody AddressAdminRequest request) {
//        return ApiResponse.<AddressAdminResponse>builder()
//                .result(addressService.update(id, request))
//                .build();
//    }
//
//    @DeleteMapping("{id}")
//    void delete(@PathVariable String id) {
//        addressService.delete(id);
//    }

}
