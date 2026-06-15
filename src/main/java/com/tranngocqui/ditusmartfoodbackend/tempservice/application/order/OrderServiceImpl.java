//package com.tranngocqui.ditusmartfoodbackend.tempservice.application.order;
//
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminResponse;
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminUpdateRequest;
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem.OrderItemCreateRequest;
//import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
//import com.tranngocqui.ditusmartfoodbackend.dto.common.CreateOrderRequest;
//import com.tranngocqui.ditusmartfoodbackend.entity.*;
//import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.deliverymethod.DeliveryMethodDomainService;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.order.OrderDomainService;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.paymentmethod.PaymentMethodDomainService;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.product.ProductService;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.user.UserDomainService;
//import com.tranngocqui.ditusmartfoodbackend.ultis.ParameterValidator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class OrderServiceImpl implements OrderService {
//    private final UserDomainService userDomainService;
//    private final PaymentMethodDomainService paymentMethodDomainService;
//    private final DeliveryMethodDomainService deliveryMethodDomainService;
//    private final OrderDomainService orderDomainService;
//    private final ProductService productService;
//    private final StringRedisTemplate redis;
//    private final OrderMapper orderMapper;
//
//
//    @Override
//    public CreateOrderResponse create(CreateOrderRequest request) {
////        DeliveryMethod deliveryMethod = deliveryMethodService.getById(request.getPaymentMethodId());
////
////        Order order = orderMapper.toOrder(request);
////
////        order.setDeliveryMethod(deliveryMethod);
////
////        User user = userService.getById(request.getUserId());
////
////        order.setUser(user);
////
////        List<OrderItem> Products = request.getProducts().stream()
////                .map(ProductRequest -> {
////                    Product Product = ProductRepository.findById(UUID.fromString(ProductRequest.getProductId())).orElseThrow(() -> new AppException(ErrorCode.MENU_Product_NOT_FOUND));
////
////                    OrderItem OrderItem = new OrderItem();
////
////                    OrderItem.setOrder(order);
////
////                    OrderItem.setProduct(Product);
////
////                    OrderItem.setQuantity(ProductRequest.getQuantity());
////
////                    OrderItem.setPriceAtOrderTime(Product.getPrice());
////
////                    return OrderItem;
////                })
////                .toList();
////
////        order.setProducts(Products);
////
////        order.setDeliveryMethod(deliveryMethodRepository.findById(UUID.fromString(request.getDeliveryMethodId())).orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND)));
////
////        order.setShippingFee(order.getDeliveryMethod().getPrice());
////
////        BigDecimal totalAmount = request.getProducts().stream()
////                .map(ProductRequest -> {
////                    Product Product = ProductRepository.findById(UUID.fromString(ProductRequest.getProductId())).orElseThrow(() -> new AppException(ErrorCode.MENU_Product_NOT_FOUND));
////
////                    BigDecimal price = Product.getPrice();
////                    int quantity = ProductRequest.getQuantity();
////
////                    return price.multiply(BigDecimal.valueOf(quantity));
////                })
////                .reduce(BigDecimal.ZERO, BigDecimal::add)
////                .add(order.getShippingFee() == null ? BigDecimal.ZERO : order.getShippingFee());
////
////        order.setTotalAmount(totalAmount);
////
////        order.setPaymentMethod(paymentMethodRepository.findById(UUID.fromString(request.getPaymentMethodId())).orElseThrow(() -> new AppException(ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED)));
////
////        return orderMapper.toCreateOrderResponse(orderRepository.save(order));
//        return null;
//    }
//
//    @Override
//    public OrderAdminResponse createOrder(CreateOrderRequest request) {
//        if (request.userId() != null) {
//            return orderMapper.toOrderAdminResponse(createCustomerOrder(request));
//        } else {
//            return orderMapper.toOrderAdminResponse(createGuestOrder(request));
//        }
//    }
//
//    @Override
//    public Page<OrderAdminResponse> getOrders(Pageable pageable) {
//        return null;
////        return findAll(pageable).map(orderMapper::toOrderAdminResponse);
//    }
//
//    @Override
//    public OrderAdminResponse getOrderById(String id) {
//        return null;
////        return orderMapper.toOrderAdminResponse(findById(UUID.fromString(id)));
//    }
//
//    @Override
//    public void deleteOrderById(String id) {
////        deleteById(UUID.fromString(id));
//    }
//
//    @Override
//    public OrderAdminResponse updateOrder(String id, OrderAdminUpdateRequest request) {
//        return null;
//    }
//
//
//    @Override
//    public Order order(CreateOrderRequest request) {
//        return null;
////        Order order = orderMapper.toOrder(request);
////
////        order.setUser(userService.getById(request.getUserId()));
////
////        order.setDeliveryMethod(deliveryMethodRepository.findById(UUID.fromString(request.getDeliveryMethodId())).orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND)));
////
////        order.setShippingFee(order.getDeliveryMethod().getPrice());
////
////        return orderRepository.save(order);
//    }
//
//    @Override
//    public void processAfterPayment(Order order) {
//
//    }
//
////    private BigDecimal calculateProductsAmount(List<OrderItem> products) {
////        return products.stream().map(Product -> {
////            return Product.getPriceAtOrderedTime().multiply());
////        }).reduce(BigDecimal.ZERO, BigDecimal::add);
////    }
//
//    private Order createCustomerOrder(CreateOrderRequest request) {
//        User user = userDomainService.getByIdOrThrow(request.userId());
//
//        PaymentMethod paymentMethod = paymentMethodDomainService.getByCodeOrThrow(request.paymentMethodCode());
//
//        DeliveryMethod deliveryMethod = deliveryMethodDomainService.getByCodeOrThrow(request.deliveryMethodCode());
//
//        List<OrderItem> items = ParameterValidator.requireNonEmptyList(listValidProductAndQuantity(request.items()), ErrorCode.EMPTY_LIST_PRODUCT);
//
//        Order order = Order.builder()
//                .orderId(generateOrderCode())
//                .user(user)
//                .paymentMethod(paymentMethod)
//                .paymentMethodName(paymentMethod.getFullName())
//                .deliveryMethod(deliveryMethod)
//                .orderItems(items)
//                .build();
//
//        return orderDomainService.createOrder(order);
//    }
//
//    private Order createGuestOrder(CreateOrderRequest request) {
//        return Order.builder().build();
//    }
//
//
//    private Order buildOrder(User user, PaymentMethod paymentMethod, DeliveryMethod deliveryMethod, List<OrderItem> Products) {
//
////        BigDecimal ProductsAmount = calculateProductsAmount(Products);
//
//        Order order = Order.builder()
//                .user(user)
//                .paymentMethod(paymentMethod)
//                .deliveryMethod(deliveryMethod)
//                .orderItems(Products)
////                .totalAmount(ProductsAmount)
//                .recipientName(user.getFullName())
//                .recipientPhone(user.getPhone())
//                .shippingFee(deliveryMethod.getPricePerKm())
//                .paymentMethodName(paymentMethod.getFullName())
//                .build();
//
//        Products.forEach(i -> i.setOrder(order));
//
//        PaymentTransaction paymentTransaction = PaymentTransaction.builder()
//                .order(order)
////                .amount(ProductsAmount)
//                .build();
//
//        order.setTransaction(paymentTransaction);
//        order.setOrderId(generateOrderCode());
//        return orderDomainService.createOrder(order);
//    }
//
//    private List<OrderItem> listValidProductAndQuantity(List<OrderItemCreateRequest> request) {
//        return request.stream()
//                .map(orderItem -> {
//                    Product product = productService.getProductById(orderItem.itemId());
//
//                    return OrderItem.builder()
//                            .product(product)
//                            .quantity(orderItem.quantity())
//                            .build();
//                })
//                .collect(Collectors.toList());
//    }
//
//    private String generateOrderCode() {
//        String key = "order:seq:20251029";
//        Long seq = redis.opsForValue().increment(key);
//        return String.format("ORD-20251029-%06d", seq);
//    }
//}
