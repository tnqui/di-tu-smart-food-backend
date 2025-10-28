package com.tranngocqui.ditusmartfoodbackend.enums;

public class RoleConstants {

    public static final String SUPER_ADMIN = RoleEnum.SUPER_ADMIN.name();
    public static final String ADMIN = RoleEnum.ADMIN.name();
    public static final String STAFF = RoleEnum.STAFF.name();
    public static final String CUSTOMER = RoleEnum.CUSTOMER.name();
    public static final String DELIVERY_STAFF = RoleEnum.DELIVERY_STAFF.name();
    public static final String CHEF = RoleEnum.CHEF.name();

    public static final String[] ADMIN_ROLES = {ADMIN, SUPER_ADMIN};
    public static final String[] STAFF_ROLES = {ADMIN, STAFF, CHEF, DELIVERY_STAFF, };
    public static final String[] MANAGEMENT_ROLES = {ADMIN, STAFF};
}
