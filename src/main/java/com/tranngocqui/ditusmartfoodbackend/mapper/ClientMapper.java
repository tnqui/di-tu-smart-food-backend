package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.client.response.AuthClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    @Mapping(source = "id", target = "userId")
    AuthClientResponse toAuthClientResponse(User user);


//    AddressClientResponse toAddressClientResponse(Address address);
//
//    List<AddressClientResponse> toListAddressClientResponse(List<Address> addresses);
}
