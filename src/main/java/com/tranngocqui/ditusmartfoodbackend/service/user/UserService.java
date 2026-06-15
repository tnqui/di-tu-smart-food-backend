package com.tranngocqui.ditusmartfoodbackend.service.user;


import com.tranngocqui.ditusmartfoodbackend.entity.User;

public interface UserService {
    User save(User user);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    User findByEmail(String email);

    User findById(String id);
}
