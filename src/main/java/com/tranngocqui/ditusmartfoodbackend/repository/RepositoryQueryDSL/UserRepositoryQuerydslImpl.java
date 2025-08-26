//package com.tranngocqui.ditusmartfoodbackend.repository.RepositoryQueryDSL;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.tranngocqui.ditusmartfoodbackend.mapper.UserMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//@RequiredArgsConstructor
//public class UserRepositoryQuerydslImpl implements UserRepositoryQueryDSL {
//    private final JPAQueryFactory jpaQueryFactory;
//    private final UserMapper userMapper;
//
//    @Override
//    public UserWithRolesAndPermissionsResponse findUserWithRolesAndPermissionsById(UUID userId) {
////        QUser qUser = QUser.user;
////        QRole qRole = QRole.role;
////        QPermission qPermission = QPermission.permission;
////
////        List<Tuple> result = jpaQueryFactory
////                .select(qUser.id, qUser.email, qUser.phone,
////                        qRole.name, qRole.description,
////                        qPermission.name, qPermission.description)
////                .from(qUser)
////                .leftJoin(qUser.roles, qRole)
////                .leftJoin(qRole.permissions,qPermission)
////                .where(qUser.id.eq(userId))
////                .fetch();
////
////        if (result.isEmpty()) {
////            throw new AppException(ErrorCode.USER_NOT_FOUND);
////        }
////
////        UserWithRolesAndPermissionsResponse userWithRolesAndPermissionsResponse = new UserWithRolesAndPermissionsResponse();
////
////        userWithRolesAndPermissionsResponse.setId(result.getFirst().get(qUser.id));
////        userWithRolesAndPermissionsResponse.setEmail(result.getFirst().get(qUser.email));
////        userWithRolesAndPermissionsResponse.setPhone(result.getFirst().get(qUser.phone));
////        userWithRolesAndPermissionsResponse.setRoles(new HashSet<>());
////
////        Map<String, RoleWithPermissionsResponse> roleMap = new LinkedHashMap<>();
////
////        for (Tuple tuple : result) {
////            String roleId = tuple.get(qRole.name);
////            if (roleId != null) {
////                RoleWithPermissionsResponse roleDTO = roleMap.computeIfAbsent(roleId,
////                        id -> new RoleWithPermissionsResponse(id, tuple.get(qRole.name), new HashSet<>()));
////
////                String permId = tuple.get(qPermission.name);
////
////                if (permId != null) {
////                    PermissionResponse permDTO = new PermissionResponse(permId, tuple.get(qPermission.name));
////                    if (!roleDTO.getPermissions().contains(permDTO)) {
////                        roleDTO.getPermissions().add(permDTO);
////                    }
////                }
////            }
////        }
////
////
////        userWithRolesAndPermissionsResponse.setRoles(new HashSet<>(roleMap.values()));
////        return userWithRolesAndPermissionsResponse;
//        return null;
//    }
//}
