package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.client.response.AddressClientResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.AuthClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    @Mapping(source = "addresses", target = "addresses")
    @Mapping(source = "id", target = "userId")
    AuthClientResponse toAuthClientResponse(User user);


//    AddressClientResponse toAddressClientResponse(Address address);
//
//    List<AddressClientResponse> toListAddressClientResponse(List<Address> addresses);
}
