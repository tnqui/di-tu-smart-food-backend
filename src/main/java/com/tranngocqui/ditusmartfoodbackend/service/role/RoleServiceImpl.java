package com.tranngocqui.ditusmartfoodbackend.service.role;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.mapper.RoleMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.PermissionRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.RoleRepository;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    @Override
    public List<RoleAdminResponse> getAll() {
        return roleRepository.findAllWithPermissions().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public RoleAdminResponse create(RoleRequest request) {

        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(UUIDUtils.fromSetString(request.getPermissions()));

        if (permissions.size() != request.getPermissions().size()) {
            throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
        }
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public RoleAdminResponse update(RoleRequest request) {
        return null;
    }

    @Override
    public void delete(String id) {
        roleRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }


    @Override
    public Page<RoleAdminResponse> getRolePagination(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::toRoleResponse);
    }

}
