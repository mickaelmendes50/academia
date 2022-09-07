package com.academia.repositories;

import com.academia.entities.Equipment;
import com.academia.entities.PhysicalMeasures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalMeasuresRepository extends JpaRepository<PhysicalMeasures, Long> {
}
