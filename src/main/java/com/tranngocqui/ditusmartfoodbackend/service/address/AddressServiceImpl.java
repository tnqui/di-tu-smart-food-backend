package com.tranngocqui.ditusmartfoodbackend.service.address;

import com.tranngocqui.ditusmartfoodbackend.configuration.properties.RestaurantProperties;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.address.RouteResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.common.ShippingAddressRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap.VietMapGeocodeResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap.VietMapPlaceDetailResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap.VietMapRoutingResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.internal.Location;
import com.tranngocqui.ditusmartfoodbackend.dto.internal.ShippingAddress;
import com.tranngocqui.ditusmartfoodbackend.entity.Address;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.vietmap.Vehicle;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.AddressMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.AddressRepository;
import com.tranngocqui.ditusmartfoodbackend.service.external.vietmap.VietMapService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
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
    private final VietMapService vietMapService;
    private final RestaurantProperties restaurantProperties;

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

    @Override
    public ShippingAddress validate(ShippingAddressRequest request) {
        String rawAddress = request.houseNumber() + " " + request.street() + ", " + request.ward() + ", " + request.city();
        VietMapGeocodeResponse match = vietMapService.geocoding(rawAddress).stream().findFirst().orElseThrow(() -> new AppException(ErrorCode.ADDRESS_INVALID));

        String refId = match.refId();
        String matchAddress = match.display();

        VietMapPlaceDetailResponse place = vietMapService.place(refId);

        Double lat = place.lat();
        Double lng = place.lng();


        return ShippingAddress.builder()
                .rawAddress(rawAddress)
                .matchAddress(matchAddress)
                .lat(lat)
                .lng(lng)
                .refId(refId)
                .build();
    }

    @Override
    public Double calculateDistanceAsKm(Location customerAddress) {
        String latlngStart = restaurantProperties.lat() + "," + restaurantProperties.lng();
        String latlngEnd = customerAddress.lat() + "," + customerAddress.lng();
        VietMapRoutingResponse route = vietMapService.routing(latlngStart, latlngEnd, Vehicle.MOTORCYCLE);

        if (route.paths().isEmpty()) {
            throw new AppException(ErrorCode.ADDRESS_INVALID);
        }

        Double distance = route.paths().getFirst().distance();

        if (distance >= 10000) {
            throw new AppException(ErrorCode.ADDRESS_NOT_SUPPORTED);
        }

        return distance / 1000;
    }

    @Override
    public Location getCurrentLocation(Double lat, Double lng) {
        var result = vietMapService.reverseGeocoding(lat, lng).getFirst();

        if (result == null) {
            throw new AppException(ErrorCode.ADDRESS_INVALID);
        }

        return Location.builder()
                .display(result.display())
                .lat(result.lat())
                .lng(result.lng())
                .refId(result.refId())
                .build();
    }

    @Override
    public Location getLocationAndCheckValidByRefId(String refId) {
        var result = vietMapService.place(refId);

        if (result == null) {
            throw new AppException(ErrorCode.INVALID_REF_ID);
        }

        double distance = getDistanceFromRestaurantAsKm(result.lat(), result.lng());
        System.out.println(distance);
        if (distance >= 10) {
            throw new AppException(ErrorCode.ADDRESS_NOT_SUPPORTED);
        }

        return Location.builder()
                .refId(refId)
                .display(result.display())
                .lng(result.lng())
                .lat(result.lat())
                .distance(distance)
                .build();
    }

    @Override
    public List<Location> autocomplete(String text) {
        var result = vietMapService.autoComplete(text);

        if (result == null) {
            throw new AppException(ErrorCode.ADDRESS_INVALID);
        }

        return vietMapService.autoComplete(text).stream().map(l -> Location.builder()
                .refId(l.refId())
                .display(l.display())
                .build()).toList();
    }

    @Override
    public RouteResponse routeFromRestaurant(double lat, double lng) {
        String latlngStart = restaurantProperties.lat() + "," + restaurantProperties.lng();
        String latlngEnd = lat + "," + lng;
        VietMapRoutingResponse route = vietMapService.routing(latlngStart, latlngEnd, Vehicle.MOTORCYCLE);
        return RouteResponse.builder()

                .build();
    }

    private double getDistanceFromRestaurantAsKm(Double lat, Double lng) {
        String latlngStart = restaurantProperties.lat() + "," + restaurantProperties.lng();
        String latlngEnd = lat + "," + lng;
        VietMapRoutingResponse route = vietMapService.routing(latlngStart, latlngEnd, Vehicle.MOTORCYCLE);
        return route.paths().getFirst().distance() / 1000;
    }


    private Address save(Address address) {
        return addressRepository.save(address);
    }

    private Address findById(String id) {
        return addressRepository.findById(UUID.fromString(id)).orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));
    }

}
