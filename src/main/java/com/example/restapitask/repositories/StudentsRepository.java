package com.example.restapitask.repositories;

import com.example.restapitask.entities.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s join fetch s.examMarks where s.lastName like %:lastName%")
    List<Student> findByLastName(String lastName, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Student s set s.lastName = :lastName")
    int setLastNameForStudents(String lastName);

    @Modifying
    @Transactional
    @Query(value = "delete from Student s where s.lastName like %:lastName%")
    void deleteByLastName(String lastName);
}
