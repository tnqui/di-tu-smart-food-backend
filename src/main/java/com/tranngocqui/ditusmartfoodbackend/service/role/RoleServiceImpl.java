package com.tranngocqui.ditusmartfoodbackend.service.role;

import com.tranngocqui.ditusmartfoodbackend.dto.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.role.response.RoleWithoutPermissionsResponse;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.mapper.RoleMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.PermissionRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    @Override
    public List<RoleWithoutPermissionsResponse> getAll() {
        return roleRepository.findAllWithPermissions().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public RoleWithoutPermissionsResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());

        if (permissions.size() != request.getPermissions().size()) {
            throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
        }
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public RoleWithoutPermissionsResponse update(RoleRequest request) {
        return null;
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
