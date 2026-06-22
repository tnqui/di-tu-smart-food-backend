package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMapper {
    public Address from(AddressAdminRequest request) {
        return Address.builder()

                .build();
    }

    public AddressAdminResponse to(Address address) {
        return AddressAdminResponse.builder().build();
    }

    public List<AddressAdminResponse> to(List<Address> address) {
        return address.stream().map(this::to).toList();
    }


}
