package com.example.restapitask.controllers;

import com.example.restapitask.entities.Student;
import com.example.restapitask.repositories.StudentsRepository;
import com.example.restapitask.services.dto.StudentRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentRestControllerTests {
    @Autowired
    private StudentsRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private final String urlTemplate = "/api/v1/students";

    @Test
    void whenGetAll_thenStatus200() throws Exception {
        mockMvc.perform(
                get(urlTemplate))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void givenListStudents_whenPost_thenStatus200() throws Exception {
        List<StudentRequestDTO> students = new ArrayList<>();
        StudentRequestDTO student1 = new StudentRequestDTO(null, "first", "test1", Date.valueOf("2000-01-01"));
        StudentRequestDTO student2 = new StudentRequestDTO(null, "second", "test2", Date.valueOf("1995-10-10"));
        StudentRequestDTO student3 = new StudentRequestDTO(null, "third", "test3", Date.valueOf("1990-12-20"));
        students.add(student1);
        students.add(student2);
        students.add(student3);

        mockMvc.perform(
                post(urlTemplate)
                        .content(objectMapper.writeValueAsString(students))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void givenListStudents_whenPut_thenStatus200() throws Exception {
        List<StudentRequestDTO> students = new ArrayList<>();
        StudentRequestDTO student1 = new StudentRequestDTO(1, "first", "test1", Date.valueOf("2000-01-01"));
        StudentRequestDTO student2 = new StudentRequestDTO(10, "second", "test2", Date.valueOf("1995-10-10"));
        StudentRequestDTO student3 = new StudentRequestDTO(20, "third", "test3", Date.valueOf("1990-12-20"));
        students.add(student1);
        students.add(student2);
        students.add(student3);

        mockMvc.perform(
                put(urlTemplate)
                        .content(objectMapper.writeValueAsString(students))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void givenListIds_whenDelete_thenStatus200() throws Exception {
        List<Integer> ids = List.of(1, 2, 13, 15);

        mockMvc.perform(
                delete(urlTemplate)
                        .content(objectMapper.writeValueAsString(ids))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(
                get(urlTemplate))
                .andDo(print());
    }

    @Test
    void whenGetById_thenStatus200AndIdEqualsAndFirstNameEquals() throws Exception {
        Student student = repository.findById(1).get();
        Integer studentId = student.getId();

        mockMvc.perform(
                get(urlTemplate + "/{id}", studentId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.firstName").value("Alex"));
    }

    @Test
    @Transactional
    void givenStudent_whenPutById_thenStatus200AndFirstNameEqualsAndBirthDateIsEmpty() throws Exception {
        StudentRequestDTO student = new StudentRequestDTO(1, "TEST", "Test", null);

        mockMvc.perform(
                put(urlTemplate + "/{id}", student.getId())
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("TEST"))
                .andExpect(jsonPath("$.birthDate").isEmpty());
    }

    @Test
    @Transactional
    void whenDeleteById_thenStatus200() throws Exception {
        mockMvc.perform(
                delete(urlTemplate + "/{id}", 5))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void givenLastNameParam_whenSetLastNames_thenStatus200() throws Exception {
        mockMvc.perform(
                put(urlTemplate + "/setLastNames")
                        .param("lastName", "test"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void givenLastName_whenDeleteByLastName_thenStatus200() throws Exception {
        String lastName = "it";

        mockMvc.perform(
                delete(urlTemplate + "/deleteByLastName")
                        .param("lastName", lastName))
                .andExpect(status().isOk());

        mockMvc.perform(
                get(urlTemplate))
                .andDo(print());
    }
}
