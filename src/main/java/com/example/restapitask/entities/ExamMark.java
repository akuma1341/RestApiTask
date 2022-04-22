package com.example.restapitask.entities;

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
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @Column(name = "mark")
    private Integer mark;
}
