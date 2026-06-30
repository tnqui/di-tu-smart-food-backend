package com.tranngocqui.ditusmartfoodbackend.service.fee;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.service.address.AddressService;
import com.tranngocqui.ditusmartfoodbackend.ultis.BigDecimalUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {
    private final AddressService addressService;

    @Override
    public BigDecimal calculateShippingFee(Order order, Double distance) {
        BigDecimal shippingFee;
        
        if (distance <= 3) {
            shippingFee = BigDecimal.valueOf(15000);
        } else {
            shippingFee = BigDecimal.valueOf(distance).multiply(BigDecimal.valueOf(5000));
        }

        BigDecimal lineItemTotal = order.getLineItemTotal();

        if (BigDecimalUtils.isGreaterOrEqual(lineItemTotal, BigDecimal.valueOf(500000))) {
            return BigDecimal.ZERO;
        }

        if (BigDecimalUtils.isGreaterOrEqual(lineItemTotal, BigDecimal.valueOf(200000))) {
            return shippingFee.multiply(new BigDecimal("0.5"));
        }

        return shippingFee;
    }


}
