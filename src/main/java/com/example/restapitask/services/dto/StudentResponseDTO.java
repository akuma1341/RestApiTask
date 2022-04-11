package com.example.restapitask.services.dto;

import com.example.restapitask.entities.ExamMark;
import com.example.restapitask.entities.Student;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class StudentResponseDTO {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private final List<Mark> marks;

    public StudentResponseDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.birthDate = student.getBirthDate();
        this.marks = student.getExamMarks().stream()
                .map(Mark::new)
                .collect(Collectors.toList());
    }

    @Getter
    public static class Mark {
        private final String subject;
        private final int mark;

        public Mark(ExamMark examMark) {
            this.subject = examMark.getSubject().getName();
            this.mark = examMark.getMark();
        }
    }
}
