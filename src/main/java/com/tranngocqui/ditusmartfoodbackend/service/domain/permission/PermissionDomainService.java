package com.tranngocqui.ditusmartfoodbackend.service.domain.permission;

import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionDomainService {
    Permission getByIdOrThrow(String id);

    Page<Permission> getPermissions(Pageable pageable);

    void deletePermissionById(String id);

    Permission createPermission(Permission permission);

}
