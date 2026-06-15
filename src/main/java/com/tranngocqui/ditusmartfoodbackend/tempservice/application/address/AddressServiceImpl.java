//package com.tranngocqui.ditusmartfoodbackend.tempservice.application.address;
//
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
//import com.tranngocqui.ditusmartfoodbackend.dto.external.GeocodeResponse;
//import com.tranngocqui.ditusmartfoodbackend.entity.Address;
//import com.tranngocqui.ditusmartfoodbackend.entity.User;
//import com.tranngocqui.ditusmartfoodbackend.mapper.AddressMapper;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.address.AddressDomainService;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.user.UserDomainService;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.external.GeocodingService;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class AddressServiceImpl implements AddressService {
//    AddressDomainService addressDomainService;
//    AddressMapper addressMapper;
//    UserDomainService userDomainService;
//    GeocodingService geocodingService;
//
//
//    @Override
//    public AddressAdminResponse create(AddressAdminRequest request) {
//        User user = userDomainService.getByIdOrThrow(request.userId());
//        Address address = addressMapper.toAddress(request);
//
//        String fullAddress = request.buildFullAddress();
//
//        address.setUser(user);
//        address.setFullAddress(request.buildFullAddress());
//
//        GeocodeResponse response = geocodingService.geocode(fullAddress);
//
//        address.setLongitude(response.longitude());
//
//        address.setLatitude(response.latitude());
//
//        address.setGeocodeMatchedAddress(response.matchedAddress());
//
//        return addressMapper.toAddressAdminResponse(addressDomainService.createAddress(address));
//    }
//
//    @Override
//    public AddressAdminResponse update(String id, AddressAdminRequest request) {
//        return null;
//    }
//
//    @Override
//    public void delete(String id) {
//
//    }
//
//    @Override
//    public AddressAdminResponse get(String id) {
//        return null;
//    }
//
//    @Override
//    public Page<AddressAdminResponse> getPagination(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<AddressAdminResponse> getByUserId(String userId) {
//        return addressDomainService.getAddressByUserId(userId).stream().map(addressMapper::toAddressAdminResponse).collect(Collectors.toList());
//    }
//
//}
