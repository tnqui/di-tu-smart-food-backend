package com.tranngocqui.ditusmartfoodbackend.service.domain.permission;

import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PermissionDomainServiceImpl extends BaseService<Permission, UUID> implements PermissionDomainService {
    public PermissionDomainServiceImpl(JpaRepository<Permission, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.PERMISSION_NOT_FOUND;
    }


    @Override
    public Permission getByIdOrThrow(String id) {
        return findByIdOrThrow(UUID.fromString(id));
    }

    @Override
    public Page<Permission> getPermissions(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    public void deletePermissionById(String id) {
        deleteById(UUID.fromString(id));
    }

    @Override
    public Permission createPermission(Permission permission) {
        return save(permission);
    }
}
