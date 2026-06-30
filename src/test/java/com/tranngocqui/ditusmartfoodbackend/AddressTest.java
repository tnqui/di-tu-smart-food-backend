package com.tranngocqui.ditusmartfoodbackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranngocqui.ditusmartfoodbackend.configuration.properties.RestaurantProperties;
import com.tranngocqui.ditusmartfoodbackend.dto.common.ShippingAddressRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.internal.Location;
import com.tranngocqui.ditusmartfoodbackend.service.address.AddressService;
import com.tranngocqui.ditusmartfoodbackend.service.external.vietmap.VietMapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AddressTest {
    @Autowired
    AddressService addressService;

    @Autowired
    VietMapService vietMapService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestaurantProperties restaurantProperties;

    @Test
    public void testValidate() throws JsonProcessingException {
        ShippingAddressRequest request = ShippingAddressRequest.builder()
                .houseNumber("510/17")
                .street("Đào Sư Tích")
                .ward("Xã Nhà Bè")
                .city("Thành phố Hồ Chí Minh")
                .build();
        var result = addressService.validate(request);

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));

    }

    @Test
    public void testPlace() throws JsonProcessingException {
        var result = vietMapService.place("geocode:RAldCSImcWBoNWFYdBR1Bg1XGVJXc1IUAghWA10AVwBKCgNC9vT2kwxCZvSdRmahn05cDg5SHUxXD1AeCQdQClYFVAJdDQVZA1QDHVVaBwUYVAZRAh4MBQZaFiQAWhFICgVUAkkHUwuhqPXCXURm9dNCYfGABVo");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));

    }

    @Test
    public void testCalculateDistance() throws JsonProcessingException {
        Location customerAddress = Location.builder()
                .lng(103.96834472930294)
                .lat(10.190105596350335)
                .build();

        Double result = addressService.calculateDistanceAsKm(customerAddress);
        System.out.println(result);
    }

}
