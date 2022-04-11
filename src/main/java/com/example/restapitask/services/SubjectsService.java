package com.example.restapitask.services;

import com.example.restapitask.entities.Subject;

import java.util.List;

public interface SubjectsService {
    List<Subject> getAll();

    Subject getById(Integer id);

    Subject save(Subject subject);

    void delete(Integer id);
}
