package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserAdminController {
//    private final UserService userService;
//
//    @PostMapping
//    public ResponseEntity<ApiResponse<UserAdminResponse>> create(@RequestBody @Valid UserAdminRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(userService.create(request)));
//    }
//
//    @GetMapping("/me/{userId}")
//    public ResponseEntity<ApiResponse<UserAdminProfileResponse>> getUser(@PathVariable("userId") String id) {
//        return ResponseEntity.ok(ApiResponse.success(userService.getUser(id)));
//    }
//
//    @PatchMapping("/{userId}")
//    public ResponseEntity<ApiResponse<UserAdminProfileResponse>> update(@PathVariable String userId, @RequestBody UserAdminUpdateRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(userService.update(userId, request)));
//    }
//
//    @GetMapping
//    public ResponseEntity<ApiResponse<Page<UserAdminResponse>>> getUsersPagination(
//            @PageableDefault(size = 10, page = 0, sort = "fullName") Pageable pageable) {
//        return ResponseEntity.ok(ApiResponse.success(userService.getAll(pageable)));
//    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userId) {
//        userService.deleteById(userId);
//        return ResponseEntity.ok(ApiResponse.success("Successfully deleted user"));
//    }
}
