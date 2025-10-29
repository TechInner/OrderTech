package com.techinner.TechInner.repository;

import com.techinner.TechInner.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<Table, Integer> {
//    Método padrão que recebe o username de Table (EX: Mesa 1). Sendo usado para atualizar password de Table
    Optional<Table> findByUsername(String username);
}
