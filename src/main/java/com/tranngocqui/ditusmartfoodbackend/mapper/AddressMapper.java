package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    @Mapping(source = "user.id", target = "userId")
    AddressAdminResponse toAddressAdminResponse(Address address);

    //tránh khi update thì bị ghi đè id mới
    @Mapping(target = "id", ignore = true)
    void update(AddressAdminRequest request, @MappingTarget Address address);

    Address toAddress(AddressAdminRequest request);

    default Page<AddressAdminResponse> toAddressAdminResponsePage(Page<Address> addresses) {
        return addresses.map(this::toAddressAdminResponse);
    }
}
