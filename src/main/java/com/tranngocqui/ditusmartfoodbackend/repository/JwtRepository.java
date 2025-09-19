package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JwtRepository extends JpaRepository<Jwt, UUID> {
    boolean existsByIdAndRevokedFalseAndUserEnabledTrue(UUID id);
}
