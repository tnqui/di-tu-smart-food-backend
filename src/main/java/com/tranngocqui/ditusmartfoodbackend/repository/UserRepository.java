package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findById(UUID id);
    boolean existsByEmail(String email);
    boolean existsByEmailOrPhone(String phone, String email);
    boolean existsByPhone(String phone);
    Optional<User> findByEmailOrPhone(String email, String phone);

    @EntityGraph(attributePaths = "roles")
    List<User> findAll();
}
