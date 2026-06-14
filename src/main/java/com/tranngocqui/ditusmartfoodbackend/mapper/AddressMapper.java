package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface AddressMapper {
    @Mapping(target = "userId", source = "user.id")
    AddressAdminResponse toAddressAdminResponse(Address address);

    Address toAddress(AddressAdminRequest addressAdminRequest);
}
