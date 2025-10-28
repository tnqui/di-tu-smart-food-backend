package com.tranngocqui.ditusmartfoodbackend.service.domain.role;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleDomainServiceImpl extends BaseService<Role, UUID> implements RoleDomainService {
    public RoleDomainServiceImpl(JpaRepository<Role, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.ROLE_NOT_FOUND;
    }


    @Override
    public Role getByIdOrThrow(String id) {
        return findByIdOrThrow(UUID.fromString(id));
    }

    @Override
    public Page<Role> getRoles(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    public void deleteRoleById(String id) {
        deleteById(UUID.fromString(id));
    }

    @Override
    public Role createRole(Role role) {
        return save(role);
    }
}
