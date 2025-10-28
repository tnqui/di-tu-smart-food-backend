package com.tranngocqui.ditusmartfoodbackend.service.domain.user;

import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserDomainServiceImpl extends BaseService<User, UUID> implements UserDomainService {

    public UserDomainServiceImpl(JpaRepository<User, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.USER_NOT_FOUND;
    }

    @Override
    public User getByIdOrThrow(String id) {
        return findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Optional<User> findOptionalById(String id) {
        return findOptionalById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    public void deleteUserById(String id) {
        deleteById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public User createUser(User user) {
        return save(user);
    }
}
