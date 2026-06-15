package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.address;

import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressDomainService {
    Address getByIdOrThrow(String id);

    Page<Address> getAddresses(Pageable pageable);

    void deleteAddressById(String id);

    List<Address> getAddressByUserId(String userId);

    Address createAddress(Address address);
}
