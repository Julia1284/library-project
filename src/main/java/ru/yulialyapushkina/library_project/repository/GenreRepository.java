package ru.yulialyapushkina.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yulialyapushkina.library_project.model.Genre;

public interface GenreRepository extends JpaRepository <Genre, Long> {
}
