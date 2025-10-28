package com.tranngocqui.ditusmartfoodbackend.ultis;

import com.tranngocqui.ditusmartfoodbackend.enums.RoleEnum;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class RoleChecker {
    public static boolean hasAnyRole(Collection<RoleEnum> userRoles, String... requiredRoles) {
        return Arrays.stream(requiredRoles)
                .anyMatch(userRoles::contains);
    }

    public static boolean hasAllRoles(Collection<RoleEnum> userRoles, String... requiredRoles) {
        return Arrays.stream(requiredRoles)
                .allMatch(userRoles::contains);
    }

    public static boolean hasAnyRoleCollection(Collection<RoleEnum> userRoles, Collection<String> requiredRoles) {
        return !Collections.disjoint(userRoles, requiredRoles);
    }
}
