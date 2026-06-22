package com.tranngocqui.ditusmartfoodbackend.service.permission;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminGetResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.permission.PermissionMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.PermissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public void save(PermissionAdminCreateRequest request) {

        if (permissionRepository.existsByName(request.name())) {
            throw new AppException(ErrorCode.PERMISSION_ALREADY_EXISTS);
        }
        permissionRepository.save(Permission.create(request.name(), request.description()));
    }

    @Override
    public PermissionAdminGetResponse findById(String id) {
        return permissionMapper.from(findById(UUID.fromString(id)));
    }

    @Override
    public List<PermissionAdminGetResponse> findAll() {
        return permissionMapper.from(permissionRepository.findAll());
    }

    @Override
    @Transactional
    public void update(String id, PermissionAdminUpdateRequest request) {
        Permission permission = findById(UUID.fromString(id));
        permission.update(request.name(), request.description());
        permissionRepository.save(permission);
    }

    @Override
    @Transactional
    public void softDeleteById(String id) {
        Permission permission = findById(UUID.fromString(id));
        permission.softDelete();
        permissionRepository.save(permission);

    }

    private Permission findById(UUID id) {
        return permissionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND));
    }
}
