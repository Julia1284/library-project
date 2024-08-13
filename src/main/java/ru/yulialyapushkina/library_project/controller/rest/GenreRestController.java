package ru.yulialyapushkina.library_project.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yulialyapushkina.library_project.dto.GenreDto;
import ru.yulialyapushkina.library_project.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreRestController {
    private final GenreService genreService;

    @GetMapping("genre/{id}")
    GenreDto getGenreById (@PathVariable ("id")Long id){
        return genreService.getGenreById(id);
    }
}
