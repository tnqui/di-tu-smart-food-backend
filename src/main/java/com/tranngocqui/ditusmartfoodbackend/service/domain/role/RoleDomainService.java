package com.tranngocqui.ditusmartfoodbackend.service.domain.role;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleDomainService {
    Role getByIdOrThrow(String id);

    Page<Role> getRoles(Pageable pageable);

    void deleteRoleById(String id);

    Role createRole(Role role);

}
