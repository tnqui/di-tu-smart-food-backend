package com.tranngocqui.ditusmartfoodbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // ========== SYSTEM ERRORS (9000-9999) ==========
    NOT_FOUND(404, "Not Found"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    INTERNAL_SERVER_ERROR(9001, "Internal server error"),
    INVALID_MESSAGE_KEY(9002, "Invalid message key"),
    SERVICE_UNAVAILABLE(9003, "Service temporarily unavailable"),
    TIMEOUT_ERROR(9004, "Request timeout"),
    DATABASE_ERROR(9005, "Database connection error"),
    FILE_PROCESSING_ERROR(9006, "File processing error"),
    SOMETHING_WENT_WRONG(9007, "Something went wrong"),
    CANNOT_SEND_VERIFICATION_CODE(9008, "Cannot send verification code"),
    TOO_MANY_REQUEST(9009, "Too many requests, please try again later"),
    ILLEGAL_ARGUMENTS(9011, "Illegal arguments"),
    MAPBOX_ERROR(9013, "Mapbox Error"),

    // ========== AUTHENTICATION ERRORS (1000-1099) ==========

    UNAUTHENTICATED(1001, "Authentication required"),
    INVALID_TOKEN(1002, "Invalid or expired token"),
    TOKEN_EXPIRED(1003, "Token has expired"),
    REFRESH_TOKEN_INVALID(1004, "Invalid refresh token"),
    REFRESH_TOKEN_EXPIRED(1005, "Refresh token has expired"),
    LOGIN_FAILED(1006, "Invalid username or password"),
    ACCOUNT_LOCKED(1007, "Account has been locked"),
    ACCOUNT_DISABLED(1008, "Account has been disabled"),
    ACCOUNT_NOT_VERIFIED(1009, "Account not verified"),
    PASSWORD_EXPIRED(1010, "Password has expired"),
    TOO_MANY_LOGIN_ATTEMPTS(1011, "Too many failed login attempts"),
    SUCCESS(1012, "Success"),
    TWO_FACTOR_REQUIRED(1013, "Two factor code required"),
    TWO_FACTOR_MISMATCH(1014, "Two factor code does not match"),
    TWO_FACTOR_NOT_ENABLED(1015, "Two factor code is not enabled"),
    INVALID_TWO_FACTOR_CODE(1016, "Invalid two factor code provided"),
    INVALID_USERNAME_OR_PASSWORD(1017, "Invalid username or password"),
    INVALID_TOKEN_SESSION(1018, "Invalid token session"),

    // ========== AUTHORIZATION ERRORS (1100-1199) ==========
    UNAUTHORIZED(1100, "Unauthorized"),
    INSUFFICIENT_PERMISSION(1101, "Insufficient permissions"),
    PERMISSION_NOT_FOUND(1102, "Permission not found"),
    ROLE_NOT_FOUND(1103, "Role not found"),
    ACCESS_DENIED(1104, "Access denied to this resource"),

    // ========== VALIDATION ERRORS (1200-1299) ==========
    VALIDATION_ERROR(1200, "Validation failed"),
    INVALID_INPUT_FORMAT(1201, "Invalid input format"),
    REQUIRED_FIELD_MISSING(1202, "Required field is missing"),
    FIELD_TOO_LONG(1203, "Field exceeds maximum length"),
    FIELD_TOO_SHORT(1204, "Field is below minimum length"),
    INVALID_EMAIL_FORMAT(1205, "Invalid email format"),
    INVALID_PHONE_FORMAT(1206, "Invalid phone number format"),
    INVALID_DATE_FORMAT(1207, "Invalid date format"),
    INVALID_NUMBER_FORMAT(1208, "Invalid number format"),
    VALUE_OUT_OF_RANGE(1209, "Value is out of allowed range"),
    INVALID_ENUM_VALUE(1210, "Invalid enum value"),

    // ========== USER MANAGEMENT ERRORS (1300-1399) ==========
    USER_NOT_FOUND(1300, "User not found"),
    USER_ALREADY_EXISTS(1301, "User already exists"),
    EMAIL_ALREADY_EXISTS(1302, "Email already exists"),
    PHONE_ALREADY_EXISTS(1303, "Phone number already exists"),
    USERNAME_ALREADY_EXISTS(1304, "Username already exists"),
    INVALID_USER_STATUS(1305, "Invalid user status"),
    USER_PROFILE_INCOMPLETE(1306, "User profile is incomplete"),
    PHONE_IS_REQUIRED(1307, "phone number is required"),
    ADDRESS_NOT_FOUND(1308, "Address not found"),

    // ========== PASSWORD ERRORS (1400-1499) ==========
    PASSWORD_IS_REQUIRED(1400, "Password is required"),
    PASSWORD_TOO_SHORT(1401, "Password must be at least 8 characters"),
    PASSWORD_TOO_WEAK(1402, "Password must contain uppercase, lowercase, number and special character"),
    PASSWORD_MISMATCH(1403, "Passwords do not match"),
    OLD_PASSWORD_INCORRECT(1404, "Current password is incorrect"),
    PASSWORD_RECENTLY_USED(1405, "Password was recently used"),
    PASSWORD_CONTAINS_PERSONAL_INFO(1406, "Password cannot contain personal information"),
    PASSWORD_BETWEEN_8_TO_20_CHARACTERS(1407, "Password between 8 to 20 characters"),
    PASSWORD_CONFIRMED(1408, "Please confirm your password"),
    PASSWORD_DOES_NOT_MATCH(1409, "Password does not match"),

    // ========== FOOD & MENU ERRORS (2000-2099) ==========
    FOOD_NOT_FOUND(2000, "Food item not found"),
    FOOD_OUT_OF_STOCK(2001, "Food item is out of stock"),
    FOOD_UNAVAILABLE(2002, "Food item is currently unavailable"),
    INVALID_FOOD_CATEGORY(2003, "Invalid food category"),
    MENU_NOT_FOUND(2004, "Menu not found"),
    MENU_INACTIVE(2005, "Menu is not active"),
    FOOD_PRICE_INVALID(2006, "Invalid food price"),
    FOOD_ALREADY_EXISTS(2007, "Food item already exists"),
    MENU_ITEM_NOT_FOUND(2008, "Menu item not found"),
    // ========== ORDER ERRORS (2100-2199) ==========
    ORDER_NOT_FOUND(2100, "Order not found"),
    ORDER_ALREADY_CANCELLED(2101, "Order has already been cancelled"),
    ORDER_CANNOT_BE_CANCELLED(2102, "Order cannot be cancelled at this stage"),
    ORDER_ALREADY_COMPLETED(2103, "Order has already been completed"),
    INVALID_ORDER_STATUS(2104, "Invalid order status"),
    ORDER_EMPTY(2105, "Order cannot be empty"),
    ORDER_AMOUNT_MISMATCH(2106, "Order amount calculation mismatch"),
    ORDER_TIME_EXPIRED(2107, "Order time has expired"),
    DELIVERY_ADDRESS_REQUIRED(2108, "Delivery address is required"),
    INVALID_DELIVERY_TIME(2109, "Invalid delivery time"),
    ORDER_FORBIDDEN(2110, "You are not allowed to access this order"),
    ORDER_DOES_NOT_EXIST(2111,"Order does not exist"),

    // ========== PAYMENT ERRORS (2200-2299) ==========
    PAYMENT_FAILED(2200, "Payment processing failed"),
    PAYMENT_METHOD_NOT_SUPPORTED(2201, "Payment method not supported"),
    INSUFFICIENT_BALANCE(2202, "Insufficient account balance"),
    PAYMENT_ALREADY_PROCESSED(2203, "Payment has already been processed"),
    INVALID_PAYMENT_AMOUNT(2204, "Invalid payment amount"),
    PAYMENT_TIMEOUT(2205, "Payment processing timeout"),
    REFUND_FAILED(2206, "Refund processing failed"),
    REFUND_NOT_ALLOWED(2207, "Refund not allowed for this transaction"),
    CARD_EXPIRED(2208, "Credit card has expired"),
    CARD_DECLINED(2209, "Credit card was declined"),

    // ========== INVENTORY ERRORS (2300-2399) ==========
    INGREDIENT_NOT_FOUND(2300, "Ingredient not found"),
    INSUFFICIENT_INGREDIENTS(2301, "Insufficient ingredients"),
    INGREDIENT_EXPIRED(2302, "Ingredient has expired"),
    INVENTORY_UPDATE_FAILED(2303, "Failed to update inventory"),
    STOCK_BELOW_MINIMUM(2304, "Stock level below minimum threshold"),

    // ========== RESTAURANT/VENDOR ERRORS (2400-2499) ==========
    RESTAURANT_NOT_FOUND(2400, "Restaurant not found"),
    RESTAURANT_CLOSED(2401, "Restaurant is currently closed"),
    RESTAURANT_NOT_DELIVERING(2402, "Restaurant not delivering to this area"),
    DELIVERY_AREA_NOT_COVERED(2403, "Delivery area not covered"),
    RESTAURANT_INACTIVE(2404, "Restaurant is inactive"),
    OPERATING_HOURS_EXCEEDED(2405, "Outside restaurant operating hours"),

    // ========== DELIVERY ERRORS (2500-2599) ==========
    DELIVERY_NOT_FOUND(2500, "Delivery not found"),
    DELIVERY_ALREADY_ASSIGNED(2501, "Delivery already assigned"),
    NO_DELIVERY_AVAILABLE(2502, "No delivery personnel available"),
    DELIVERY_CANCELLED(2503, "Delivery has been cancelled"),
    INVALID_DELIVERY_STATUS(2504, "Invalid delivery status"),
    DELIVERY_ADDRESS_INVALID(2505, "Invalid delivery address"),
    DELIVERY_DISTANCE_EXCEEDED(2506, "Delivery distance exceeds limit"),

    // ========== REVIEW & RATING ERRORS (2600-2699) ==========
    REVIEW_NOT_FOUND(2600, "Review not found"),
    ALREADY_REVIEWED(2601, "You have already reviewed this item"),
    CANNOT_REVIEW_OWN_ITEM(2602, "Cannot review your own item"),
    REVIEW_NOT_ALLOWED(2603, "Review not allowed for this order"),
    INVALID_RATING_VALUE(2604, "Invalid rating value"),

    // ========== NOTIFICATION ERRORS (2700-2799) ==========
    NOTIFICATION_NOT_FOUND(2700, "Notification not found"),
    NOTIFICATION_SEND_FAILED(2701, "Failed to send notification"),
    INVALID_NOTIFICATION_TYPE(2702, "Invalid notification type"),
    NOTIFICATION_ALREADY_READ(2703, "Notification already marked as read"),

    // ========== FILE & MEDIA ERRORS (2800-2899) ==========
    FILE_NOT_FOUND(2800, "File not found"),
    FILE_TOO_LARGE(2801, "File size exceeds limit"),
    INVALID_FILE_TYPE(2802, "Invalid file type"),
    FILE_UPLOAD_FAILED(2803, "File upload failed"),
    IMAGE_PROCESSING_FAILED(2804, "Image processing failed"),
    FILE_CORRUPTED(2805, "File is corrupted"),

    // ========== PROMOTION & DISCOUNT ERRORS (2900-2999) ==========
    COUPON_NOT_FOUND(2900, "Coupon not found"),
    COUPON_EXPIRED(2901, "Coupon has expired"),
    COUPON_ALREADY_USED(2902, "Coupon has already been used"),
    COUPON_NOT_APPLICABLE(2903, "Coupon not applicable for this order"),
    INVALID_DISCOUNT_AMOUNT(2904, "Invalid discount amount"),
    PROMOTION_INACTIVE(2905, "Promotion is not active"),
    MINIMUM_ORDER_NOT_MET(2906, "Minimum order amount not met for discount"),

    // ========== ANALYTICS & REPORTING ERRORS (3000-3099) ==========
    REPORT_GENERATION_FAILED(3000, "Report generation failed"),
    INVALID_DATE_RANGE(3001, "Invalid date range for report"),
    DATA_NOT_AVAILABLE(3002, "Data not available for requested period"),
    EXPORT_FAILED(3003, "Data export failed"),

    // ========== THIRD-PARTY INTEGRATION ERRORS (3100-3199) ==========
    EXTERNAL_SERVICE_ERROR(3100, "External service error"),
    API_RATE_LIMIT_EXCEEDED(3101, "API rate limit exceeded"),
    EXTERNAL_API_TIMEOUT(3102, "External API timeout"),
    INTEGRATION_CONFIGURATION_ERROR(3103, "Integration configuration error"),
    WEBHOOK_PROCESSING_FAILED(3104, "Webhook processing failed"),
    GENERATE_QR_FAILED(3105, "Generate QR failed"),
    // ========== BUSINESS LOGIC ERRORS (3200-3299) ==========
    BUSINESS_RULE_VIOLATION(3200, "Business rule violation"),
    OPERATION_NOT_ALLOWED(3201, "Operation not allowed"),
    DEPENDENCY_EXISTS(3202, "Cannot delete due to existing dependencies"),
    DUPLICATE_OPERATION(3203, "Duplicate operation detected"),
    RESOURCE_IN_USE(3204, "Resource is currently in use"),

    // ========== SPECIFIC APP ERRORS (3300-3399) ==========
    PHONE_OR_EMAIL_IS_REQUIRED(3300, "Phone or email is required"),
    PHONE_OR_EMAIL_INVALID(3301, "Invalid phone or email format"),
    REGISTRATION_FAILED(3302, "Registration failed"),
    EMAIL_VERIFICATION_REQUIRED(3303, "Email verification required"),
    PHONE_VERIFICATION_REQUIRED(3304, "Phone verification required"),
    VERIFICATION_CODE_INVALID(3305, "Invalid verification code"),
    VERIFICATION_CODE_EXPIRED(3306, "Verification code has expired"),
    ACCOUNT_SETUP_INCOMPLETE(3307, "Account setup is incomplete");

    private final int code;
    private final String message;
}
