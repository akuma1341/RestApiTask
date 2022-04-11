package com.example.restapitask.services.impl;

import com.example.restapitask.entities.ExamMark;
import com.example.restapitask.repositories.ExamMarksRepository;
import com.example.restapitask.services.ExamMarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamMarksServiceImpl implements ExamMarksService {
    private final ExamMarksRepository examMarksRepository;

    @Override
    public List<ExamMark> getAll() {
        return examMarksRepository.findAll();
    }

    @Override
    public ExamMark getById(Integer id) {
        return examMarksRepository.findById(id).orElse(null);
    }

    @Override
    public ExamMark save(ExamMark examMark) {
        return examMarksRepository.save(examMark);
    }

    @Override
    public void delete(Integer id) {
        examMarksRepository.deleteById(id);
    }
}
