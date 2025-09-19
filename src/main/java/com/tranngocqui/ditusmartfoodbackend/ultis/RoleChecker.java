package com.tranngocqui.ditusmartfoodbackend.ultis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class RoleChecker {
    public static boolean hasAnyRole(Collection<String> userRoles, String... requiredRoles) {
        return Arrays.stream(requiredRoles)
                .anyMatch(userRoles::contains);
    }

    public static boolean hasAllRoles(Collection<String> userRoles, String... requiredRoles) {
        return Arrays.stream(requiredRoles)
                .allMatch(userRoles::contains);
    }

    public static boolean hasAnyRoleCollection(Collection<String> userRoles, Collection<String> requiredRoles) {
        return !Collections.disjoint(userRoles, requiredRoles);
    }
}
