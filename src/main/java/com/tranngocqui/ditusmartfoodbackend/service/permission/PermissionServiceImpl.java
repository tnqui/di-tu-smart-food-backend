package com.tranngocqui.ditusmartfoodbackend.service.permission;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.PermissionMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public PermissionAdminResponse create(PermissionAdminRequest request) {

        Permission permission = permissionMapper.toPermission(request);

        if (permissionRepository.existsByName(permission.getName())) {
            throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
        }
        permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionAdminResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String id) {
        permissionRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Page<PermissionAdminResponse> getPermissionPagination(Pageable pageable) {
        return permissionMapper.toPermissionResponsePagination(permissionRepository.findAll(pageable));
    }

}
