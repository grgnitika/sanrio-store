package com.example.sanrio.Repo;

import com.example.sanrio.Entity.CustomizePizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizePizzaRepository extends JpaRepository<CustomizePizzaEntity, Long> {
    // Add custom queries or methods if needed
}
