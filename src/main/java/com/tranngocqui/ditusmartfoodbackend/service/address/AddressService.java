package com.tranngocqui.ditusmartfoodbackend.service.address;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss.AddressAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.address.RouteResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.common.ShippingAddressRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.internal.Location;
import com.tranngocqui.ditusmartfoodbackend.dto.internal.ShippingAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    AddressAdminResponse create(AddressAdminRequest request);

    AddressAdminResponse update(String id, AddressAdminRequest request);

    void deleteById(String id);

    AddressAdminResponse get(String id);

    Page<AddressAdminResponse> getPagination(Pageable pageable);

    List<AddressAdminResponse> getByUserId(String userId);

    ShippingAddress validate(ShippingAddressRequest request);

    Double calculateDistanceAsKm(Location customerAddress);

    Location getCurrentLocation(Double lat, Double lng);

    Location getLocationAndCheckValidByRefId(String refId);

    List<Location> autocomplete(String text);

    RouteResponse routeFromRestaurant(double lat, double lng);
}
