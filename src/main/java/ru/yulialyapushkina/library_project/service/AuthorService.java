package ru.yulialyapushkina.library_project.service;

import ru.yulialyapushkina.library_project.dto.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById (Long id);
}
