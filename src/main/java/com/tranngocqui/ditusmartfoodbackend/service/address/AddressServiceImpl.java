package com.tranngocqui.ditusmartfoodbackend.service.address;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.AddressMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.AddressRepository;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import com.tranngocqui.ditusmartfoodbackend.tempservice.external.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserService userService;
    private final GeocodingService geocodingService;


    @Override
    public AddressAdminResponse create(AddressAdminRequest request) {
        User user = userService.findById(request.userId());
        Address address = addressMapper.from(request);
        String fullAddress = request.buildFullAddress();

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

        return addressMapper.to(save(address));
    }

    @Override
    public AddressAdminResponse update(String id, AddressAdminRequest request) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        var address = findById(id);
        address.softDelete();
        save(address);
    }


    @Override
    public AddressAdminResponse get(String id) {
        return null;
    }

    @Override
    public Page<AddressAdminResponse> getPagination(Pageable pageable) {
        return null;
    }

    @Override
    public List<AddressAdminResponse> getByUserId(String userId) {
        return addressMapper.to(addressRepository.findByUserId(UUID.fromString(userId)));
    }

    private Address save(Address address) {
        return addressRepository.save(address);
    }

    private Address findById(String id) {
        return addressRepository.findById(UUID.fromString(id)).orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));
    }
}
