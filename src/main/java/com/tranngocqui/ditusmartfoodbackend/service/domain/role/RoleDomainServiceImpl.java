package com.tranngocqui.ditusmartfoodbackend.service.domain.role;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.repository.RoleRepository;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.service.BaseServiceFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleDomainServiceImpl implements RoleDomainService {
    private final BaseService<Role, UUID> baseService;

    public RoleDomainServiceImpl(BaseServiceFactory factory, RoleRepository RoleRepository) {
        this.baseService = factory.create(RoleRepository);
    }

    @Override
    public Role getByIdOrThrow(String id) {
        return baseService.findByIdOrThrow(UUID.fromString(id));
    }

    @Override
    public Page<Role> getRoles(Pageable pageable) {
        return baseService.findAll(pageable);
    }

    @Override
    public void deleteRoleById(String id) {
        baseService.deleteById(UUID.fromString(id));
    }

    @Override
    public Role createRole(Role role) {
        return baseService.save(role);
    }
}
