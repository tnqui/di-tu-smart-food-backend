package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @EntityGraph(attributePaths = {"categories"})
    List<MenuItem> findAll();

    @EntityGraph(attributePaths = {"categories"})
    Page<MenuItem> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"categories"})
    Page<MenuItem> findByCategories_Name(String categoryName, Pageable pageable);

    // Client use-case
    Page<MenuItem> findByEnabledTrueAndStockGreaterThan(Integer stock, Pageable pageable);

    @Query("""
            SELECT m FROM MenuItem m JOIN m.categories c
            WHERE m.enabled = true 
              AND m.stock > :stock 
              AND c.id = :categoryId""")
    Page<MenuItem> findByEnabledTrueAndStockGreaterThanAndCategoryId(
            @Param("stock") Integer stock,
            @Param("categoryId") Long categoryId,
            Pageable pageable
    );
}
