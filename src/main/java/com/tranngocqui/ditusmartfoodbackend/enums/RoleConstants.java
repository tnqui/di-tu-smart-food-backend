package com.tranngocqui.ditusmartfoodbackend.enums;

public class RoleConstants {

    public static final String SUPER_ADMIN = RoleEnums.SUPER_ADMIN.name();
    public static final String ADMIN = RoleEnums.ADMIN.name();
    public static final String STAFF = RoleEnums.STAFF.name();
    public static final String USER = RoleEnums.STAFF.name();
    public static final String MODERATOR = RoleEnums.MODERATOR.name();

    public static final String[] ADMIN_ROLES = {ADMIN, SUPER_ADMIN};
    public static final String[] STAFF_ROLES = {ADMIN, STAFF};
    public static final String[] MANAGEMENT_ROLES = {ADMIN, STAFF, MODERATOR};
}
