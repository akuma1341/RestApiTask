package com.example.restapitask.controllers;

import com.example.restapitask.entities.ExamMark;
import com.example.restapitask.services.ExamMarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/examMarks")
@RequiredArgsConstructor
public class ExamMarksRestController {
    private final ExamMarksService examMarksService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExamMark> getAll() {
        return examMarksService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamMark getById(@PathVariable("id") Integer id) {
        return examMarksService.getById(id);
    }
}
