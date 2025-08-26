package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<User> findUserProfileByEmailOrPhone(String email, String phone);

    //custom
    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<User> findUserProfileById(UUID id);

}
