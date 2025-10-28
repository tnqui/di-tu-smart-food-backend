package com.tranngocqui.ditusmartfoodbackend.service.application.address;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {
    AddressAdminResponse create(AddressAdminRequest request);

    AddressAdminResponse update(String id, AddressAdminRequest request);

    void delete(String id);

    AddressAdminResponse get(String id);

    Page<AddressAdminResponse> getPagination(Pageable pageable);
}
