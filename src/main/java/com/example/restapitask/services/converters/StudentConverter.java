package com.example.restapitask.services.converters;

import com.example.restapitask.entities.Student;
import com.example.restapitask.services.dto.StudentRequestDTO;
import com.example.restapitask.services.dto.StudentResponseDTO;

public interface StudentConverter {

    Student toEntity(StudentRequestDTO dto);

    Student toEntity(Integer id, StudentRequestDTO dto);

    StudentResponseDTO toResponseDTO(Student entity);
}
