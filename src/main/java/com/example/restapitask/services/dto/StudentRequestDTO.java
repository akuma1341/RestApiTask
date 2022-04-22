package com.example.restapitask.services.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Builder
@Getter
@RequiredArgsConstructor
public class StudentRequestDTO {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
}
