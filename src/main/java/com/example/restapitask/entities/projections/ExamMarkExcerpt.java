package com.example.restapitask.entities.projections;

import com.example.restapitask.entities.ExamMark;
import com.example.restapitask.entities.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineSubject", types = {ExamMark.class})
public interface ExamMarkExcerpt {
    Integer getMark();

    Student getStudent();

    @Value("#{target.subject.name}")
    String getSubject();
}
