package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.projection.ProductNameStock;
import com.tranngocqui.ditusmartfoodbackend.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.id IN :ids")
    List<Product> findByIdIn(@Param("ids") List<UUID> ids);

    @Query("SELECT p FROM Product p WHERE p.id IN :ids")
    List<Product> findByIds(List<UUID> ids);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.id IN :ids AND p.deleted=false")
    List<Product> findByIdInAndDeletedIsFalse(Collection<UUID> ids);

    @Query("SELECT p.id as id, p.name as name,p.stock as stock From Product p")
    List<ProductNameStock> findProductNameStock(Sort sort);

}
