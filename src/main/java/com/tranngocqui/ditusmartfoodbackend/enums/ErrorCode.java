package com.tranngocqui.ditusmartfoodbackend.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BAD_REQUEST(400, "Bad Request", HttpStatus.BAD_REQUEST),

    // ========== SYSTEM ERRORS (1000-1099) ==========
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_SERVER_ERROR(1001, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_MESSAGE_KEY(1002, "Invalid message key", HttpStatus.BAD_REQUEST),
    SERVICE_UNAVAILABLE(1003, "Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE),
    TIMEOUT_ERROR(1004, "Request timeout", HttpStatus.REQUEST_TIMEOUT),
    DATABASE_ERROR(1005, "Database connection error", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_PROCESSING_ERROR(1006, "File processing error", HttpStatus.INTERNAL_SERVER_ERROR),
    SOMETHING_WENT_WRONG(1007, "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR),
    CANNOT_SEND_VERIFICATION_CODE(1008, "Cannot send verification code", HttpStatus.INTERNAL_SERVER_ERROR),
    TOO_MANY_REQUESTS(1009, "Too many requests, please try again later", HttpStatus.TOO_MANY_REQUESTS),
    ILLEGAL_ARGUMENTS(1010, "Illegal arguments", HttpStatus.BAD_REQUEST),
    MAPBOX_ERROR(1011, "Mapbox service error", HttpStatus.BAD_GATEWAY),
    NOT_FOUND(1012, "Resource not found", HttpStatus.NOT_FOUND),
    ENTITY_NOT_FOUND(1013, "Entity not found", HttpStatus.NOT_FOUND),

    // ========== AUTHENTICATION ERRORS (1100-1199) ==========
    UNAUTHENTICATED(1100, "Authentication required", HttpStatus.UNAUTHORIZED),
    INVALID_USERNAME_OR_PASSWORD(1101, "Invalid username or password", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(1102, "Invalid or expired token", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(1103, "Token has expired", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_INVALID(1104, "Invalid refresh token", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_EXPIRED(1105, "Refresh token has expired", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN_SESSION(1106, "Invalid token session", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED(1107, "Account has been locked", HttpStatus.FORBIDDEN),
    ACCOUNT_DISABLED(1108, "Account has been disabled", HttpStatus.FORBIDDEN),
    ACCOUNT_NOT_VERIFIED(1109, "Account not verified", HttpStatus.FORBIDDEN),
    PASSWORD_EXPIRED(1110, "Password has expired", HttpStatus.FORBIDDEN),
    TOO_MANY_LOGIN_ATTEMPTS(1111, "Too many failed login attempts", HttpStatus.TOO_MANY_REQUESTS),
    TWO_FACTOR_REQUIRED(1112, "Two-factor authentication required", HttpStatus.UNAUTHORIZED),
    TWO_FACTOR_MISMATCH(1113, "Two-factor code does not match", HttpStatus.UNAUTHORIZED),
    TWO_FACTOR_NOT_ENABLED(1114, "Two-factor authentication is not enabled", HttpStatus.BAD_REQUEST),
    INVALID_TWO_FACTOR_CODE(1115, "Invalid two-factor code", HttpStatus.UNAUTHORIZED),
    USER_HAS_BEEN_DELETED(1116, "User has been deleted", HttpStatus.GONE),

    // ========== AUTHORIZATION ERRORS (1200-1299) ==========
    FORBIDDEN(1200, "Access forbidden", HttpStatus.FORBIDDEN),
    UNAUTHORIZED(1201, "Unauthorized access", HttpStatus.FORBIDDEN),
    INSUFFICIENT_PERMISSION(1202, "Insufficient permissions", HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND(1203, "Role not found", HttpStatus.NOT_FOUND),
    ACCESS_DENIED(1204, "Access denied to this resource", HttpStatus.FORBIDDEN),

    // ========== VALIDATION ERRORS (1300-1399) ==========
    VALIDATION_ERROR(1300, "Validation failed", HttpStatus.BAD_REQUEST),
    INVALID_INPUT_FORMAT(1301, "Invalid input format", HttpStatus.BAD_REQUEST),
    REQUIRED_FIELD_MISSING(1302, "Required field is missing", HttpStatus.BAD_REQUEST),
    FIELD_TOO_LONG(1303, "Field exceeds maximum length", HttpStatus.BAD_REQUEST),
    FIELD_TOO_SHORT(1304, "Field is below minimum length", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_FORMAT(1305, "Invalid email format", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_FORMAT(1306, "Invalid phone number format", HttpStatus.BAD_REQUEST),
    INVALID_DATE_FORMAT(1307, "Invalid date format", HttpStatus.BAD_REQUEST),
    INVALID_NUMBER_FORMAT(1308, "Invalid number format", HttpStatus.BAD_REQUEST),
    VALUE_OUT_OF_RANGE(1309, "Value is out of allowed range", HttpStatus.BAD_REQUEST),
    INVALID_ENUM_VALUE(1310, "Invalid enum value", HttpStatus.BAD_REQUEST),

    // ========== USER MANAGEMENT ERRORS (1400-1499) ==========
    USER_NOT_FOUND(1400, "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(1401, "User already exists", HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXISTS(1402, "Email already exists", HttpStatus.CONFLICT),
    PHONE_ALREADY_EXISTS(1403, "Phone number already exists", HttpStatus.CONFLICT),
    USERNAME_ALREADY_EXISTS(1404, "Username already exists", HttpStatus.CONFLICT),
    INVALID_USER_STATUS(1405, "Invalid user status", HttpStatus.BAD_REQUEST),
    USER_PROFILE_INCOMPLETE(1406, "User profile is incomplete", HttpStatus.BAD_REQUEST),
    PHONE_IS_REQUIRED(1407, "Phone number is required", HttpStatus.BAD_REQUEST),
    PHONE_OR_EMAIL_IS_REQUIRED(1408, "Phone or email is required", HttpStatus.BAD_REQUEST),
    PHONE_OR_EMAIL_INVALID(1409, "Invalid phone or email format", HttpStatus.BAD_REQUEST),

    // ========== PASSWORD ERRORS (1500-1599) ==========
    PASSWORD_IS_REQUIRED(1500, "Password is required", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_SHORT(1501, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_WEAK(1502, "Password must contain uppercase, lowercase, number and special character", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH(1503, "Passwords do not match", HttpStatus.BAD_REQUEST),
    OLD_PASSWORD_INCORRECT(1504, "Current password is incorrect", HttpStatus.BAD_REQUEST),
    PASSWORD_RECENTLY_USED(1505, "Password was recently used", HttpStatus.BAD_REQUEST),
    PASSWORD_CONTAINS_PERSONAL_INFO(1506, "Password cannot contain personal information", HttpStatus.BAD_REQUEST),
    PASSWORD_BETWEEN_8_TO_20_CHARACTERS(1507, "Password must be between 8 to 20 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_CONFIRMATION_REQUIRED(1508, "Password confirmation is required", HttpStatus.BAD_REQUEST),
    PASSWORD_DOES_NOT_MATCH(1509, "Password does not match", HttpStatus.BAD_REQUEST),

    // ========== REGISTRATION & VERIFICATION ERRORS (1600-1699) ==========
    REGISTRATION_FAILED(1600, "Registration failed", HttpStatus.BAD_REQUEST),
    EMAIL_VERIFICATION_REQUIRED(1601, "Email verification required", HttpStatus.BAD_REQUEST),
    PHONE_VERIFICATION_REQUIRED(1602, "Phone verification required", HttpStatus.BAD_REQUEST),
    VERIFICATION_CODE_INVALID(1603, "Invalid verification code", HttpStatus.BAD_REQUEST),
    VERIFICATION_CODE_EXPIRED(1604, "Verification code has expired", HttpStatus.BAD_REQUEST),
    ACCOUNT_SETUP_INCOMPLETE(1605, "Account setup is incomplete", HttpStatus.BAD_REQUEST),

    // ========== ADDRESS ERRORS (1700-1799) ==========
    ADDRESS_NOT_FOUND(1700, "Address not found", HttpStatus.NOT_FOUND),
    ADDRESS_INVALID(1701, "Invalid address format", HttpStatus.BAD_REQUEST),
    ADDRESS_GEOCODING_FAILED(1702, "Failed to geocode address", HttpStatus.BAD_REQUEST),
    ADDRESS_OUTSIDE_DELIVERY_AREA(1703, "Address is outside delivery area", HttpStatus.BAD_REQUEST),
    DEFAULT_ADDRESS_REQUIRED(1704, "At least one default address is required", HttpStatus.BAD_REQUEST),
    ADDRESS_ALREADY_DEFAULT(1705, "Address is already set as default", HttpStatus.BAD_REQUEST),
    ADDRESS_COORDINATES_INVALID(1706, "Invalid address coordinates", HttpStatus.BAD_REQUEST),
    ADDRESS_STREET_REQUIRED(1707, "Street is required", HttpStatus.BAD_REQUEST),
    ADDRESS_WARD_REQUIRED(1708, "Ward is required", HttpStatus.BAD_REQUEST),
    ADDRESS_DISTRICT_REQUIRED(1709, "District is required", HttpStatus.BAD_REQUEST),
    ADDRESS_CITY_REQUIRED(1710, "City is required", HttpStatus.BAD_REQUEST),
    ADDRESS_COUNTRY_REQUIRED(1711, "Country is required", HttpStatus.BAD_REQUEST),

    // ========== ROLE & PERMISSION ERRORS (1800-1899) ==========
    PERMISSION_NOT_FOUND(1800, "Permission not found", HttpStatus.NOT_FOUND),
    PERMISSION_ALREADY_EXISTS(1801, "Permission already exists", HttpStatus.CONFLICT),
    PERMISSION_IN_USE(1802, "Permission is currently assigned to roles", HttpStatus.CONFLICT),
    ROLE_ALREADY_EXISTS(1803, "Role already exists", HttpStatus.CONFLICT),
    ROLE_DUPLICATE_NAME(1804, "Role name already taken", HttpStatus.CONFLICT),
    ROLE_DELETE_FORBIDDEN(1805, "Cannot delete role with assigned users", HttpStatus.CONFLICT),
    ROLE_IN_USE(1806, "Role is currently assigned to users", HttpStatus.CONFLICT),

    // ========== PRODUCT ERRORS (2000-2099) ==========
    PRODUCT_NOT_FOUND(2000, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_ALREADY_EXISTS(2001, "Product already exists", HttpStatus.CONFLICT),
    PRODUCT_OUT_OF_STOCK(2002, "Product is out of stock", HttpStatus.BAD_REQUEST),
    PRODUCT_UNAVAILABLE(2003, "Product is currently unavailable", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_INVALID(2004, "Invalid product price", HttpStatus.BAD_REQUEST),
    PRODUCT_STOCK_INSUFFICIENT(2005, "Insufficient product stock", HttpStatus.BAD_REQUEST),
    PRODUCT_IMAGE_REQUIRED(2006, "Product image is required", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_REQUIRED(2007, "Product name is required", HttpStatus.BAD_REQUEST),
    PRODUCT_CATEGORY_REQUIRED(2008, "Product category is required", HttpStatus.BAD_REQUEST),
    EMPTY_LIST_PRODUCT(2009, "Empty list product", HttpStatus.BAD_REQUEST),

    // ========== CATEGORY ERRORS (2100-2199) ==========
    CATEGORY_NOT_FOUND(2100, "Category not found", HttpStatus.NOT_FOUND),
    CATEGORY_ALREADY_EXISTS(2101, "Category name already exists", HttpStatus.CONFLICT),
    CATEGORY_IN_USE(2102, "Category is in use and cannot be deleted", HttpStatus.CONFLICT),
    CATEGORY_VALIDATION_FAILED(2103, "Invalid category data", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_REQUIRED(2104, "Category name is required", HttpStatus.BAD_REQUEST),
    CATEGORY_HAS_PRODUCTS(2105, "Category has products and cannot be deleted", HttpStatus.CONFLICT),

    // ========== ORDER ERRORS (2200-2299) ==========
    ORDER_NOT_FOUND(2200, "Order not found", HttpStatus.NOT_FOUND),
    ORDER_DOES_NOT_EXIST(2201, "Order does not exist", HttpStatus.NOT_FOUND),
    ORDER_ALREADY_CANCELLED(2202, "Order has already been cancelled", HttpStatus.BAD_REQUEST),
    ORDER_CANNOT_BE_CANCELLED(2203, "Order cannot be cancelled at this stage", HttpStatus.BAD_REQUEST),
    ORDER_ALREADY_COMPLETED(2204, "Order has already been completed", HttpStatus.BAD_REQUEST),
    INVALID_ORDER_STATUS(2205, "Invalid order status", HttpStatus.BAD_REQUEST),
    ORDER_EMPTY(2206, "Order cannot be empty", HttpStatus.BAD_REQUEST),
    ORDER_AMOUNT_MISMATCH(2207, "Order amount calculation mismatch", HttpStatus.BAD_REQUEST),
    ORDER_TIME_EXPIRED(2208, "Order time has expired", HttpStatus.BAD_REQUEST),
    DELIVERY_ADDRESS_REQUIRED(2209, "Delivery address is required", HttpStatus.BAD_REQUEST),
    INVALID_DELIVERY_TIME(2210, "Invalid delivery time", HttpStatus.BAD_REQUEST),
    ORDER_FORBIDDEN(2211, "You are not allowed to access this order", HttpStatus.FORBIDDEN),
    ORDER_ITEM_NOT_FOUND(2212, "Order item not found", HttpStatus.NOT_FOUND),
    ORDER_ITEM_QUANTITY_INVALID(2213, "Invalid order item quantity", HttpStatus.BAD_REQUEST),
    ORDER_MINIMUM_AMOUNT_NOT_MET(2214, "Order does not meet minimum amount", HttpStatus.BAD_REQUEST),

    // ========== PAYMENT METHOD ERRORS (2300-2399) ==========
    PAYMENT_METHOD_NOT_FOUND(2300, "Payment method not found", HttpStatus.NOT_FOUND),
    PAYMENT_METHOD_NOT_SUPPORTED(2301, "Payment method not supported", HttpStatus.BAD_REQUEST),
    PAYMENT_METHOD_INACTIVE(2302, "Payment method is inactive", HttpStatus.BAD_REQUEST),
    PAYMENT_METHOD_ALREADY_EXISTS(2303, "Payment method already exists", HttpStatus.CONFLICT),

    // ========== PAYMENT TRANSACTION ERRORS (2400-2499) ==========
    PAYMENT_FAILED(2400, "Payment processing failed", HttpStatus.BAD_REQUEST),
    PAYMENT_TRANSACTION_NOT_FOUND(2401, "Payment transaction not found", HttpStatus.NOT_FOUND),
    INSUFFICIENT_BALANCE(2402, "Insufficient account balance", HttpStatus.BAD_REQUEST),
    PAYMENT_ALREADY_PROCESSED(2403, "Payment has already been processed", HttpStatus.BAD_REQUEST),
    INVALID_PAYMENT_AMOUNT(2404, "Invalid payment amount", HttpStatus.BAD_REQUEST),
    PAYMENT_TIMEOUT(2405, "Payment processing timeout", HttpStatus.REQUEST_TIMEOUT),
    REFUND_FAILED(2406, "Refund processing failed", HttpStatus.BAD_REQUEST),
    REFUND_NOT_ALLOWED(2407, "Refund not allowed for this transaction", HttpStatus.BAD_REQUEST),
    CARD_EXPIRED(2408, "Credit card has expired", HttpStatus.BAD_REQUEST),
    CARD_DECLINED(2409, "Credit card was declined", HttpStatus.BAD_REQUEST),
    PAYMENT_CALLBACK_INVALID(2410, "Invalid payment callback data", HttpStatus.BAD_REQUEST),
    PAYMENT_VERIFICATION_FAILED(2411, "Payment verification failed", HttpStatus.BAD_REQUEST),

    // ========== DELIVERY METHOD ERRORS (2500-2599) ==========
    DELIVERY_METHOD_NOT_FOUND(2500, "Delivery method not found", HttpStatus.NOT_FOUND),
    DELIVERY_METHOD_INACTIVE(2501, "Delivery method is inactive", HttpStatus.BAD_REQUEST),
    DELIVERY_METHOD_ALREADY_EXISTS(2502, "Delivery method already exists", HttpStatus.CONFLICT),
    DELIVERY_ALREADY_ASSIGNED(2503, "Delivery already assigned", HttpStatus.BAD_REQUEST),
    NO_DELIVERY_AVAILABLE(2504, "No delivery personnel available", HttpStatus.BAD_REQUEST),
    DELIVERY_CANCELLED(2505, "Delivery has been cancelled", HttpStatus.BAD_REQUEST),
    INVALID_DELIVERY_STATUS(2506, "Invalid delivery status", HttpStatus.BAD_REQUEST),
    DELIVERY_ADDRESS_INVALID(2507, "Invalid delivery address", HttpStatus.BAD_REQUEST),
    DELIVERY_DISTANCE_EXCEEDED(2508, "Delivery distance exceeds limit", HttpStatus.BAD_REQUEST),
    DELIVERY_FEE_CALCULATION_FAILED(2509, "Failed to calculate delivery fee", HttpStatus.BAD_REQUEST),

    // ========== REVIEW & RATING ERRORS (2600-2699) ==========
    REVIEW_NOT_FOUND(2600, "Review not found", HttpStatus.NOT_FOUND),
    ALREADY_REVIEWED(2601, "You have already reviewed this item", HttpStatus.BAD_REQUEST),
    CANNOT_REVIEW_OWN_ITEM(2602, "Cannot review your own item", HttpStatus.FORBIDDEN),
    REVIEW_NOT_ALLOWED(2603, "Review not allowed for this order", HttpStatus.BAD_REQUEST),
    INVALID_RATING_VALUE(2604, "Invalid rating value", HttpStatus.BAD_REQUEST),
    REVIEW_COMMENT_TOO_LONG(2605, "Review comment exceeds maximum length", HttpStatus.BAD_REQUEST),
    REVIEW_RATING_REQUIRED(2606, "Rating is required", HttpStatus.BAD_REQUEST),
    REVIEW_ORDER_NOT_COMPLETED(2607, "Can only review completed orders", HttpStatus.BAD_REQUEST),

    // ========== NOTIFICATION ERRORS (2700-2799) ==========
    NOTIFICATION_NOT_FOUND(2700, "Notification not found", HttpStatus.NOT_FOUND),
    NOTIFICATION_SEND_FAILED(2701, "Failed to send notification", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_NOTIFICATION_TYPE(2702, "Invalid notification type", HttpStatus.BAD_REQUEST),
    NOTIFICATION_ALREADY_READ(2703, "Notification already marked as read", HttpStatus.BAD_REQUEST),
    NOTIFICATION_TITLE_REQUIRED(2704, "Notification title is required", HttpStatus.BAD_REQUEST),
    NOTIFICATION_CONTENT_REQUIRED(2705, "Notification content is required", HttpStatus.BAD_REQUEST),

    // ========== FILE & MEDIA ERRORS (2800-2899) ==========
    FILE_NOT_FOUND(2800, "File not found", HttpStatus.NOT_FOUND),
    FILE_TOO_LARGE(2801, "File size exceeds limit", HttpStatus.PAYLOAD_TOO_LARGE),
    INVALID_FILE_TYPE(2802, "Invalid file type", HttpStatus.BAD_REQUEST),
    FILE_UPLOAD_FAILED(2803, "File upload failed", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_PROCESSING_FAILED(2804, "Image processing failed", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_CORRUPTED(2805, "File is corrupted", HttpStatus.BAD_REQUEST),
    FILE_NAME_INVALID(2806, "Invalid file name", HttpStatus.BAD_REQUEST),

    // ========== PROMOTION & DISCOUNT ERRORS (2900-2999) ==========
    COUPON_NOT_FOUND(2900, "Coupon not found", HttpStatus.NOT_FOUND),
    COUPON_EXPIRED(2901, "Coupon has expired", HttpStatus.BAD_REQUEST),
    COUPON_ALREADY_USED(2902, "Coupon has already been used", HttpStatus.BAD_REQUEST),
    COUPON_NOT_APPLICABLE(2903, "Coupon not applicable for this order", HttpStatus.BAD_REQUEST),
    INVALID_DISCOUNT_AMOUNT(2904, "Invalid discount amount", HttpStatus.BAD_REQUEST),
    PROMOTION_INACTIVE(2905, "Promotion is not active", HttpStatus.BAD_REQUEST),
    MINIMUM_ORDER_NOT_MET(2906, "Minimum order amount not met for discount", HttpStatus.BAD_REQUEST),
    COUPON_USAGE_LIMIT_EXCEEDED(2907, "Coupon usage limit exceeded", HttpStatus.BAD_REQUEST),

    // ========== ANALYTICS & REPORTING ERRORS (3000-3099) ==========
    REPORT_GENERATION_FAILED(3000, "Report generation failed", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_DATE_RANGE(3001, "Invalid date range for report", HttpStatus.BAD_REQUEST),
    DATA_NOT_AVAILABLE(3002, "Data not available for requested period", HttpStatus.BAD_REQUEST),
    EXPORT_FAILED(3003, "Data export failed", HttpStatus.INTERNAL_SERVER_ERROR),

    // ========== THIRD-PARTY INTEGRATION ERRORS (3100-3199) ==========
    EXTERNAL_SERVICE_ERROR(3100, "External service error", HttpStatus.BAD_GATEWAY),
    API_RATE_LIMIT_EXCEEDED(3101, "API rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS),
    EXTERNAL_API_TIMEOUT(3102, "External API timeout", HttpStatus.GATEWAY_TIMEOUT),
    INTEGRATION_CONFIGURATION_ERROR(3103, "Integration configuration error", HttpStatus.INTERNAL_SERVER_ERROR),
    WEBHOOK_PROCESSING_FAILED(3104, "Webhook processing failed", HttpStatus.BAD_REQUEST),
    GENERATE_QR_FAILED(3105, "QR code generation failed", HttpStatus.INTERNAL_SERVER_ERROR),
    GEOCODING_SERVICE_ERROR(3106, "Geocoding service error", HttpStatus.BAD_GATEWAY),
    GEOCODING_API_LIMIT_EXCEEDED(3107, "Geocoding API limit exceeded", HttpStatus.TOO_MANY_REQUESTS),

    // ========== BUSINESS LOGIC ERRORS (3200-3299) ==========
    BUSINESS_RULE_VIOLATION(3200, "Business rule violation", HttpStatus.BAD_REQUEST),
    OPERATION_NOT_ALLOWED(3201, "Operation not allowed", HttpStatus.BAD_REQUEST),
    DEPENDENCY_EXISTS(3202, "Cannot delete due to existing dependencies", HttpStatus.CONFLICT),
    DUPLICATE_OPERATION(3203, "Duplicate operation detected", HttpStatus.CONFLICT),
    RESOURCE_IN_USE(3204, "Resource is currently in use", HttpStatus.CONFLICT),
    ENTITY_ALREADY_EXISTS(3205, "Entity already exists", HttpStatus.CONFLICT),

    // ========== JWT & TOKEN ERRORS (3300-3399) ==========
    JWT_NOT_FOUND(3300, "JWT token not found", HttpStatus.UNAUTHORIZED),
    JWT_INVALID(3301, "Invalid JWT token", HttpStatus.UNAUTHORIZED),
    JWT_EXPIRED(3302, "JWT token has expired", HttpStatus.UNAUTHORIZED),
    JWT_REFRESH_FAILED(3303, "Failed to refresh JWT token", HttpStatus.UNAUTHORIZED),
    JWT_BLACKLISTED(3304, "JWT token has been blacklisted", HttpStatus.UNAUTHORIZED);

    // ========== RESERVED FOR FUTURE USE (3400-9999) ==========
    // Cart errors: 3400-3499
    // Wishlist errors: 3500-3599
    // Inventory errors: 3600-3699
    // Restaurant/Vendor errors: 3700-3799
    // Loyalty/Rewards errors: 3800-3899
    // Chat/Support errors: 3900-3999
    // ...extending to 9999

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}