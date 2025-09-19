package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
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
