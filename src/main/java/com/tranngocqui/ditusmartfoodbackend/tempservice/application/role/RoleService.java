//package com.tranngocqui.ditusmartfoodbackend.tempservice.application.role;
//
//
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleAdminRequest;
//import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.Optional;
//
//public interface RoleService {
//    Page<RoleAdminResponse> getAll(Pageable pageable);
//
//    RoleAdminResponse getById(String id);
//
//    RoleAdminResponse create(RoleAdminRequest request);
//
//    RoleAdminResponse update(String id, RoleAdminRequest request);
//
//    void deleteById(String id);
//
//    Optional<Role> findByName(String name);
//
//    Page<RoleAdminResponse> getRolePagination(Pageable pageable);
//}
