package com.tranngocqui.ditusmartfoodbackend.service.application.address;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.external.GeocodeResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.AddressMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.AddressRepository;
import com.tranngocqui.ditusmartfoodbackend.service.GeocodingService;
import com.tranngocqui.ditusmartfoodbackend.service.domain.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserDomainService userDomainService;
    private final GeocodingService geocodingService;

    @Override
    public AddressAdminResponse create(AddressAdminRequest request) {
        User user = userDomainService.getByIdOrThrow(request.getUserId());

        Address address = addressMapper.toAddress(request);

        address.setUser(user);

        geocodingService.geocode(request.getFullAddress());

        try {
            GeocodeResponse geocodeResponse = geocodingService.geocode(request.getFullAddress());

            address.setLongitude(geocodeResponse.getLongitude());
            address.setLatitude(geocodeResponse.getLatitude());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.ILLEGAL_ARGUMENTS);
        } catch (Exception e) {
            throw new AppException(ErrorCode.MAPBOX_ERROR);
        }

        AddressAdminResponse response = addressMapper.toAddressAdminResponse(addressRepository.save(address));

        response.setUserId(user.getId());

        return response;
    }

    @Override
    public AddressAdminResponse update(String id, AddressAdminRequest request) {

        Address address = addressRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        // Cập nhật các field từ request vào address
        addressMapper.update(request, address);

        if (request.getUserId() != null) {
            User user = userDomainService.getByIdOrThrow(request.getUserId());
            address.setUser(user);
        }

        Address savedAddress = addressRepository.save(address);

        return addressMapper.toAddressAdminResponse(savedAddress);
    }


    @Override
    public void delete(String id) {
        addressRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public AddressAdminResponse get(String id) {
        Address address = addressRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        return addressMapper.toAddressAdminResponse(address);
    }

    @Override
    public Page<AddressAdminResponse> getPagination(Pageable pageable) {
        return addressMapper.toAddressAdminResponsePage(addressRepository.findAll(pageable));
    }

}
