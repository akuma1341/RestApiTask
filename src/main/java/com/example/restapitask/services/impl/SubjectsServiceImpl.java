package com.example.restapitask.services.impl;

import com.example.restapitask.entities.Subject;
import com.example.restapitask.repositories.SubjectsRepository;
import com.example.restapitask.services.SubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectsServiceImpl implements SubjectsService {
    private final SubjectsRepository subjectRepository;

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getById(Integer id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void delete(Integer id) {
        subjectRepository.deleteById(id);
    }
}
