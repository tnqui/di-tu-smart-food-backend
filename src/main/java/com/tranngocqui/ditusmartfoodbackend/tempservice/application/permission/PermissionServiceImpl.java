//package com.tranngocqui.ditusmartfoodbackend.tempservice.application.permission;
//
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminRequest;
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
//import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
//import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
//import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
//import com.tranngocqui.ditusmartfoodbackend.mapper.PermissionMapper;
//import com.tranngocqui.ditusmartfoodbackend.repository.PermissionRepository;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.permission.PermissionDomainService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PermissionServiceImpl implements PermissionService {
//    private final PermissionRepository permissionRepository;
//    private final PermissionMapper permissionMapper;
//    private final PermissionDomainService permissionDomainService;
//
//    @Override
//    public PermissionAdminResponse create(PermissionAdminRequest request) {
//
//        Permission permission = permissionMapper.toPermission(request);
//
//        if (permissionRepository.existsByName(permission.getName())) {
//            throw new AppException(ErrorCode.PERMISSION_ALREADY_EXISTS);
//        }
//
//        permissionRepository.save(permission);
//
//        return permissionMapper.toPermissionAdminResponse(permission);
//    }
//
//    @Override
//    public List<PermissionAdminResponse> getAll() {
//        var permissions = permissionRepository.findAll();
//        return permissions.stream().map(permissionMapper::toPermissionAdminResponse).toList();
//    }
//
//    @Override
//    public PermissionAdminResponse getById(String id) {
//        return permissionMapper.toPermissionAdminResponse(permissionDomainService.getByIdOrThrow(id));
//    }
//
//    @Override
//    public void deleteById(String id) {
//        permissionDomainService.deletePermissionById(id);
//    }
//
//    @Override
//    public PermissionAdminResponse update(String id, PermissionAdminRequest request) {
//        Permission permission = permissionDomainService.getByIdOrThrow(id);
//
//        permissionMapper.update(request, permission);
//        return permissionMapper.toPermissionAdminResponse(permissionDomainService.createPermission(permission));
//    }
//
//    @Override
//    public Page<PermissionAdminResponse> getPermissionPagination(Pageable pageable) {
//        return permissionMapper.toPermissionResponsePagination(permissionRepository.findAll(pageable));
//    }
//
//}
