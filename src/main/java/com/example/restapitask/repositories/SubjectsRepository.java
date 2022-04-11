package com.example.restapitask.repositories;

import com.example.restapitask.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository<Subject, Integer> {
}
