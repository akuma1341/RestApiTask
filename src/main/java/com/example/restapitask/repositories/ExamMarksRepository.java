package com.example.restapitask.repositories;

import com.example.restapitask.entities.ExamMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamMarksRepository extends JpaRepository<ExamMark, Integer> {
}
