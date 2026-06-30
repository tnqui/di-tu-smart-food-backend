package com.tranngocqui.ditusmartfoodbackend.service.fee;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;

import java.math.BigDecimal;

public interface FeeService {
    BigDecimal calculateShippingFee(Order order, Double distance);

}
