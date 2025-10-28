package com.tranngocqui.ditusmartfoodbackend.service.domain.user;

import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDomainService {

    User getByIdOrThrow(String id);
    Optional<User> findOptionalById(String id);

    Page<User> getUsers(Pageable pageable);

    void deleteUserById(String id);

    User createUser(User user);


}
