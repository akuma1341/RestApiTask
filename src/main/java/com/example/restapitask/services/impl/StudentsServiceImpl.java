package com.example.restapitask.services.impl;

import com.example.restapitask.entities.Student;
import com.example.restapitask.repositories.StudentsRepository;
import com.example.restapitask.services.StudentsService;
import com.example.restapitask.services.dto.StudentRequestDTO;
import com.example.restapitask.services.dto.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {
    private final StudentsRepository studentsRepository;

    @Override
    public List<Student> getAll() {
        return studentsRepository.findAll();
    }

    @Override
    public List<StudentResponseDTO> getAllByLastName(String lastName, Pageable pageable) {
        return studentsRepository.findByLastName(lastName, pageable).stream()
                .map(StudentResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Student getById(Integer id) {
        return studentsRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Integer id, StudentRequestDTO studentDTO) {
        Student studentToUpdate = studentsRepository.getById(id);
        studentToUpdate.setFirstName(studentDTO.getFirstName());
        studentToUpdate.setLastName(studentDTO.getLastName());
        studentToUpdate.setBirthDate(studentDTO.getBirthDate());
        return studentsRepository.save(studentToUpdate);
    }

    @Override
    public List<Student> updateAll(StudentRequestDTO... toUpdate) {
        return studentsRepository.saveAll(Arrays.stream(toUpdate)
                .filter(studentDTO -> studentDTO.getId() != null)
                .map(studentDTO -> {
                    Student studentToUpdate = studentsRepository.getById(studentDTO.getId());
                    studentToUpdate.setFirstName(studentDTO.getFirstName());
                    studentToUpdate.setLastName(studentDTO.getLastName());
                    studentToUpdate.setBirthDate(studentDTO.getBirthDate());
                    return studentToUpdate;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public List<Student> createAll(StudentRequestDTO... toCreate) {
        return studentsRepository.saveAll(Arrays.stream(toCreate)
                .map(studentDTO -> {
                    Student studentToCreate = new Student();
                    studentToCreate.setFirstName(studentDTO.getFirstName());
                    studentToCreate.setLastName(studentDTO.getLastName());
                    studentToCreate.setBirthDate(studentDTO.getBirthDate());
                    return studentToCreate;
                })
                .collect(Collectors.toList()));
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
