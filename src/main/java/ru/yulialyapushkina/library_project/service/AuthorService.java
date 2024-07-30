package ru.yulialyapushkina.library_project.service;

import ru.yulialyapushkina.library_project.dto.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById (Long id);

    AuthorDto getAuthorBySurname (String surname);
    AuthorDto getAuthorBySurnameBySql (String surname);
    AuthorDto getAuthorBySurnameV3 (String surname);

}
