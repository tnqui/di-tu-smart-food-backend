package com.tranngocqui.ditusmartfoodbackend.enums;

public class RoleConstants {

    public static final String SUPER_ADMIN = RoleEnums.SUPER_ADMIN.name();
    public static final String ADMIN = RoleEnums.ADMIN.name();
    public static final String STAFF = RoleEnums.STAFF.name();
    public static final String CUSTOMER = RoleEnums.CUSTOMER.name();
    public static final String DELIVERY_STAFF = RoleEnums.DELIVERY_STAFF.name();
    public static final String CHEF = RoleEnums.CHEF.name();

    public static final String[] ADMIN_ROLES = {ADMIN, SUPER_ADMIN};
    public static final String[] STAFF_ROLES = {ADMIN, STAFF, CHEF, DELIVERY_STAFF, };
    public static final String[] MANAGEMENT_ROLES = {ADMIN, STAFF};
}
