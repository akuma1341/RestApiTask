package com.example.restapitask.services;

import com.example.restapitask.entities.ExamMark;

import java.util.List;

public interface ExamMarksService {
    List<ExamMark> getAll();

    ExamMark getById(Integer id);

    ExamMark save(ExamMark examMark);

    void delete(Integer id);
}
