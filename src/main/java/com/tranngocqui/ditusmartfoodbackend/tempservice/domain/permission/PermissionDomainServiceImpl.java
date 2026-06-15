package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.permission;

import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.repository.PermissionRepository;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseService;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseServiceFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PermissionDomainServiceImpl implements PermissionDomainService {
    private final BaseService<Permission, UUID> baseService;

    public PermissionDomainServiceImpl(BaseServiceFactory factory, PermissionRepository PermissionRepository) {
        this.baseService = factory.create(PermissionRepository);
    }

    @Override
    public Permission getByIdOrThrow(String id) {
        return baseService.findByIdOrThrow(UUID.fromString(id));
    }

    @Override
    public Page<Permission> getPermissions(Pageable pageable) {
        return baseService.findAll(pageable);
    }

    @Override
    public void deletePermissionById(String id) {
        baseService.deleteById(UUID.fromString(id));
    }

    @Override
    public Permission createPermission(Permission permission) {
        return baseService.save(permission);
    }
}
