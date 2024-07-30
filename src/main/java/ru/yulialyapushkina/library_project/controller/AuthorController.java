package ru.yulialyapushkina.library_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yulialyapushkina.library_project.dto.AuthorDto;
import ru.yulialyapushkina.library_project.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping ("author/{id}")
    AuthorDto getAuthorById (@PathVariable ("id") Long id){
        return  authorService.getAuthorById(id);
    }
    @GetMapping ("/author")
    AuthorDto getAuthorByName (@RequestParam ("surname")String surname){
        return  authorService.getAuthorBySurname(surname);
    }
    @GetMapping ("/author/v2")
    AuthorDto getAuthorByNameV2 (@RequestParam ("surname") String surname){
        return authorService.getAuthorBySurnameBySql(surname);
    }

    @GetMapping ("/author/v3")
    AuthorDto getAuthorByNameV3 (@RequestParam ("surname") String surname){
        return authorService.getAuthorBySurnameV3(surname);
    }


}

