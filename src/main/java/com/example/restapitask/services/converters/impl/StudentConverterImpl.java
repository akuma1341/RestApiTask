package com.example.restapitask.services.converters.impl;

import com.example.restapitask.entities.Student;
import com.example.restapitask.services.converters.StudentConverter;
import com.example.restapitask.services.dto.StudentRequestDTO;
import com.example.restapitask.services.dto.StudentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentConverterImpl implements StudentConverter {

    @Override
    public Student toEntity(StudentRequestDTO dto) {
        return convertToEntity(dto.getId(), dto);
    }

    @Override
    public Student toEntity(Integer id, StudentRequestDTO dto) {
        return convertToEntity(id, dto);
    }

    @Override
    public StudentResponseDTO toResponseDTO(Student entity) {
        return new StudentResponseDTO(entity);
    }

    private Student convertToEntity(Integer id, StudentRequestDTO dto) {
        return Student.builder()
                .id(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(dto.getBirthDate())
                .build();
    }
}
