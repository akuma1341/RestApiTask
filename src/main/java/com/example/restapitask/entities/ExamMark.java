package com.example.restapitask.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exam_marks")
@Data
public class ExamMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    @JsonManagedReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    @JsonManagedReference
    private Subject subject;

    @Column(name = "mark")
    private Integer mark;
}
