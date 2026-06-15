package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.user;

import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseService;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseServiceFactory;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    private final BaseService<User, UUID> userService;

    public UserDomainServiceImpl(BaseServiceFactory factory, UserRepository userRepository) {
        this.userService = factory.create(userRepository);
    }

    @Override
    public User getByIdOrThrow(String id) {
        return userService.findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Optional<User> findOptionalById(String id) {
        return userService.findOptionalById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @Override
    public void deleteUserById(String id) {
        userService.deleteById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public User createUser(User user) {
        return userService.save(user);
    }
}
