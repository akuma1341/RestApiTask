package com.example.restapitask.services;

import com.example.restapitask.entities.Student;
import com.example.restapitask.services.dto.StudentRequestDTO;
import com.example.restapitask.services.dto.StudentResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentsService {
    List<Student> getAll();

    List<StudentResponseDTO> getAllByLastName(String lastName, Pageable pageable);

    Student getById(Integer id);

    Student update(Integer id, StudentRequestDTO studentDTO);

    List<Student> updateAll(List<StudentRequestDTO> toUpdate);

    List<Student> createAll(List<StudentRequestDTO> toCreate);

    void delete(Integer id);

    void deleteAllById(List<Integer> ids);

    int setLastNameForStudents(String lastName);

    void deleteByLastName(String lastName);
}
