package com.example.restapitask.controllers;

import com.example.restapitask.entities.Student;
import com.example.restapitask.services.StudentsService;
import com.example.restapitask.services.dto.StudentRequestDTO;
import com.example.restapitask.services.dto.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsRestController {
    private final StudentsService studentsService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAll() {
        return studentsService.getAll();
    }

    @GetMapping(value = "/filtered", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentResponseDTO> getAllByLastName(@RequestParam(value = "lastName", defaultValue = "") String lastName, Pageable pageable) {
        return studentsService.getAllByLastName(lastName, pageable);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> create(@RequestBody StudentRequestDTO... toCreate) {
        return studentsService.createAll(toCreate);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> updateAll(@RequestBody StudentRequestDTO... toUpdate) {
        return studentsService.updateAll(toUpdate);
    }

    @DeleteMapping(value = "")
    public void deleteAll(@RequestBody List<Integer> ids) {
        studentsService.deleteAllById(ids);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getById(@PathVariable("id") Integer id) {
        return studentsService.getById(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student update(@PathVariable("id") Integer id, @RequestBody StudentRequestDTO requestDTO) {
        return studentsService.update(id, requestDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        studentsService.delete(id);
    }

    @PutMapping(value = "/setLastNames", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> setLastNames(@RequestParam("lastName") String lastName) {
        System.out.println("studentsService.setLastNameForStudents(lastName) = " + studentsService.setLastNameForStudents(lastName));
        return studentsService.getAll();
    }

    @DeleteMapping(value = "/deleteByLastName")
    public void deleteByLastName(@RequestParam("lastName") String lastName) {
        studentsService.deleteByLastName(lastName);
    }
}
