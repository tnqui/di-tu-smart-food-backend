package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import lombok.NonNull;
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
    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);


    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    @NonNull
    Optional<User> findById(@NonNull UUID id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @EntityGraph(attributePaths = {"roles", "roles.permissions", "addresses"})
    Optional<User> findUserProfileByEmailOrPhone(String email, String phone);

    //custom
    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<User> findUserProfileById(UUID id);

    @EntityGraph(attributePaths = {"roles", "roles.permissions", "addresses"})
    Optional<User> findByEmailOrPhone(String email, String phone);

}
