//package com.tranngocqui.ditusmartfoodbackend.tempservice.application.role;
//
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleAdminRequest;
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
//import com.tranngocqui.ditusmartfoodbackend.entity.Role;
//import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
//import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
//import com.tranngocqui.ditusmartfoodbackend.mapper.RoleMapper;
//import com.tranngocqui.ditusmartfoodbackend.repository.PermissionRepository;
//import com.tranngocqui.ditusmartfoodbackend.repository.RoleRepository;
//import com.tranngocqui.ditusmartfoodbackend.tempservice.domain.role.RoleDomainService;
//import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//@Service
//@RequiredArgsConstructor
//public class RoleServiceImpl implements RoleService {
//    private final RoleRepository roleRepository;
//    private final RoleMapper roleMapper;
//    private final PermissionRepository permissionRepository;
//    private final RoleDomainService roleDomainService;
//
//    @Override
//    public Page<RoleAdminResponse> getAll(Pageable pageable) {
//        return roleDomainService.getRoles(pageable).map(roleMapper::toRoleAdminResponse);
//    }
//
//    @Override
//    public RoleAdminResponse getById(String id) {
//        return roleMapper.toRoleAdminResponse(roleDomainService.getByIdOrThrow(id));
//    }
//
//    @Override
//    public RoleAdminResponse create(RoleAdminRequest request) {
//
//        Role role = roleMapper.toRole(request);
//
//        if (request.permissions() != null) {
//            var permissions = permissionRepository.findAllById(UUIDUtils.fromSetString(request.permissions()));
//            if (permissions.size() != request.permissions().size()) {
//                throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
//            }
//
//            role.setPermissions(new HashSet<>(permissions));
//
//        }
//        return roleMapper.toRoleAdminResponse(roleRepository.save(role));
//
//    }
//
//    @Override
//    public RoleAdminResponse update(String id, RoleAdminRequest request) {
//        Role role = roleDomainService.getByIdOrThrow(id);
//
//        roleMapper.update(request, role);
//
//        return roleMapper.toRoleAdminResponse(roleDomainService.createRole(role));
//    }
//
//    @Override
//    public void deleteById(String id) {
//        roleDomainService.deleteRoleById(id);
//    }
//
//    @Override
//    public Optional<Role> findByName(String name) {
//        return roleRepository.findByName(name);
//    }
//
//
//    @Override
//    public Page<RoleAdminResponse> getRolePagination(Pageable pageable) {
//        return roleRepository.findAll(pageable).map(roleMapper::toRoleAdminResponse);
//    }
//
//}
