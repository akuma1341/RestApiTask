package com.example.restapitask.services.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Getter
@RequiredArgsConstructor
public class StudentRequestDTO {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
}
