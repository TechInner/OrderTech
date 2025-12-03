package com.techinner.TechInner.repository;

import com.techinner.TechInner.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByName(String name);
    boolean existsByName(String name);
    boolean existsByNameIgnoreCase(String name);
}
