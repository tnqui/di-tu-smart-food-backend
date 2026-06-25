package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("{id}")
    ResponseEntity<ApiResponse<AddressAdminResponse>> get(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(addressService.get(id)));
    }

    @GetMapping()
    ResponseEntity<ApiResponse<Page<AddressAdminResponse>>> getPagination(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(addressService.getPagination(pageable)));
    }


    @PatchMapping("{id}")
    ResponseEntity<ApiResponse<AddressAdminResponse>> update(@PathVariable String id, @RequestBody AddressAdminRequest request) {
        return ResponseEntity.ok(ApiResponse.success(addressService.update(id, request)));
    }

    @DeleteMapping("{id}")
    ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable String id) {
        addressService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Successfully deleted address"));
    }

}
