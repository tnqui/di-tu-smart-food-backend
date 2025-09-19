package com.tranngocqui.ditusmartfoodbackend.service.address;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {
    AddressAdminResponse create(AddressAdminRequest request);

    AddressAdminResponse update(Long id, AddressAdminRequest request);

    void delete(Long id);

    AddressAdminResponse get(Long id);

    Page<AddressAdminResponse> getPagination(Pageable pageable);
}
