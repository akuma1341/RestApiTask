package com.example.restapitask.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Integer id;

    @Column(name = "name")
    private String name;
}
