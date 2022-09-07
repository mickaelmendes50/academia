package com.example.gym_students_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gym_students_crud.entities.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {
}
