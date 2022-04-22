package com.example.restapitask.services.impl;

import com.example.restapitask.entities.Student;
import com.example.restapitask.repositories.StudentsRepository;
import com.example.restapitask.services.StudentsService;
import com.example.restapitask.services.converters.StudentConverter;
import com.example.restapitask.services.dto.StudentRequestDTO;
import com.example.restapitask.services.dto.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {
    private final StudentsRepository studentsRepository;
    private final StudentConverter studentConverter;

    @Override
    public List<Student> getAll() {
        return studentsRepository.findAll();
    }

    @Override
    public List<StudentResponseDTO> getAllByLastName(String lastName, Pageable pageable) {
        return studentsRepository.findByLastName(lastName, pageable).stream()
                .map(studentConverter::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Student getById(Integer id) {
        return studentsRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Integer id, StudentRequestDTO studentDTO) {
        return studentsRepository.save(studentConverter.toEntity(id, studentDTO));
    }

    @Override
    public List<Student> updateAll(List<StudentRequestDTO> toUpdate) {
        List<Student> studentsToUpdate = toUpdate.stream()
                .filter(studentDTO -> studentDTO.getId() != null)
                .map(studentConverter::toEntity)
                .collect(Collectors.toList());
        return studentsRepository.saveAll(studentsToUpdate);
    }

    @Override
    public List<Student> createAll(List<StudentRequestDTO> toCreate) {
        List<Student> studentsToCreate = toCreate.stream()
                .map(studentConverter::toEntity)
                .collect(Collectors.toList());
        return studentsRepository.saveAll(studentsToCreate);
    }

    @Override
    public void delete(Integer id) {
        studentsRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Integer> ids) {
        studentsRepository.deleteAllById(ids);
    }

    @Override
    public int setLastNameForStudents(String lastName) {
        return studentsRepository.setLastNameForStudents(lastName);
    }

    @Override
    public void deleteByLastName(String lastName) {
        studentsRepository.deleteByLastName(lastName);
    }
}
