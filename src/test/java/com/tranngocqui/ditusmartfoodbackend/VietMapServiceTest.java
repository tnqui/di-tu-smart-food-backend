package com.tranngocqui.ditusmartfoodbackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranngocqui.ditusmartfoodbackend.enums.vietmap.Vehicle;
import com.tranngocqui.ditusmartfoodbackend.service.external.vietmap.VietMapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class VietMapServiceTest {
    @Autowired
    private VietMapService vietMapService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGeocoding() throws JsonProcessingException {
        var result = vietMapService.geocoding("197 Trần Phú, phường 4, quận 5, thành phố Hồ Chí Minh");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result.getFirst()));
    }

    @Test
    public void testReverseGeocoding() throws JsonProcessingException {
        var result = vietMapService.reverseGeocoding(10.759221, 106.675901);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

    @Test
    public void testAutoComplete() throws JsonProcessingException {
        var result = vietMapService.autoComplete("Cà phê");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

    @Test
    public void testRouting() throws JsonProcessingException {
        var result = vietMapService.routing("10.79628438955497,106.70592293472612", "10.801891047584164,106.70660958023404", Vehicle.MOTORCYCLE);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

    @Test
    public void testMatrix() throws JsonProcessingException {
        var result = vietMapService.matrix(List.of("10.768897,106.678505", "10.765496,106.67626", "10.7627936,106.6750729", "10.7616745,106.6792429", "10.765605,106.685383", "10.766843,106.674029"), "0;1", "2;3;4;5");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

    @Test
    public void testPlaceDetail() throws JsonProcessingException {
        var result = vietMapService.place("auto:RAkPcicmZ3d-NQhac2kADHYlbFAkBiEeAQAkCV0EXwdF_KakgoWOrg0FFWZfh4jFXA1kXfbZFlNRGFUYCAJXAF8BUQBVCAZUC18EA1VMAwUYXwJQBBQFBQVTHVFacANBQlU");
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

}
