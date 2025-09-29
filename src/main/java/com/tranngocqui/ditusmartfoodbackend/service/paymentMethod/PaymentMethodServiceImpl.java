package com.tranngocqui.ditusmartfoodbackend.service.paymentMethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.PaymentMethodResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.PaymentMethodMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.PaymentMethodRepository;
import com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction.factory.PaymentServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;
    private final OrderRepository orderRepository;
    private final PaymentServiceFactory paymentServiceFactory;

    @Override
    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED));
    }

    @Override
    public PaymentMethodResponse create(PaymentMethodRequest request) {
        PaymentMethod paymentMethod = paymentMethodRepository.save(paymentMethodMapper.toPaymentMethod(request));

        return PaymentMethodResponse.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName().name())
                .description(paymentMethod.getDescription())
                .build();
    }

//    @Override
//    public PaymentMethod findByProvider(PaymentProvider provider) {
//        return null;
//    }
//
//    @Override
//    @Transactional
//    public PaymentResponse processPayment(UUID orderId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
//            throw new AppException(ErrorCode.UNAUTHORIZED);
//        }
//
//        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
//        UUID userId = user.getUser().getId();
//        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
//
//        if (!order.getUser().getId().equals(userId)) {
//            throw new AppException(ErrorCode.ORDER_FORBIDDEN);
//        }
//
//        PaymentProvider provider = order.getPaymentMethod().getName();
//        try {
//            PaymentStrategy strategy = paymentServiceFactory.getStrategy(provider);
//            return strategy.pay(order);
//        } catch (UnsupportedPaymentException e) {
//            throw new AppException(ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED);
//        } catch (Exception e) {
//            throw new AppException(ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED);
//        }
//    }
}
