package com.tranngocqui.ditusmartfoodbackend.test.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductTestRepository extends JpaRepository<Product, UUID> {
}
