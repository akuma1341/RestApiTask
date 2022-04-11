package com.example.restapitask.controllers;

import com.example.restapitask.entities.Subject;
import com.example.restapitask.services.SubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectsRestController {
    private final SubjectsService subjectsService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subject> getAll() {
        return subjectsService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Subject getById(@PathVariable("id") Integer id) {
        return subjectsService.getById(id);
    }
}
