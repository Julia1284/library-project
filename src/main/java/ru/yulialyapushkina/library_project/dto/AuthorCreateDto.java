package ru.yulialyapushkina.library_project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthorCreateDto {
    private String name;
    private String surname;
}
