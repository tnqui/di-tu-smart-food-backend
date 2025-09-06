package com.tranngocqui.ditusmartfoodbackend.repository;

import com.tranngocqui.ditusmartfoodbackend.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    @EntityGraph(attributePaths = {"categories"})
    List<Dish> findAll();

    @EntityGraph(attributePaths = {"categories"})
    Page<Dish> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"categories"})
    Page<Dish> findByCategories_Name(String categoryName, Pageable pageable);

}
