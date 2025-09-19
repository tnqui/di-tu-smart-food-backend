package com.tranngocqui.ditusmartfoodbackend.enums;

public enum OrderStatus {
    PENDING,        // Đơn hàng mới tạo, chờ xác nhận
    CONFIRMED,      // Đã xác nhận (admin/seller duyệt)
    PROCESSING,     // Đang chuẩn bị món ăn
    SHIPPING,       // Đang giao hàng
    DELIVERED,      // Đã giao thành công
    CANCELLED,      // Bị huỷ
    FAILED,         // Giao hàng thất bại
    REFUNDED        // Đã hoàn tiền
}
