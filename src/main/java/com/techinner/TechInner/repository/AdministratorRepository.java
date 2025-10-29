package com.techinner.TechInner.repository;

import com.techinner.TechInner.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
