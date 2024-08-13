package ru.yulialyapushkina.library_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yulialyapushkina.library_project.model.Genre;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookCreateDto {
    private String name;
    private Genre genre;
}
