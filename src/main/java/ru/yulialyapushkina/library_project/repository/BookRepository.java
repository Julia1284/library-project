package ru.yulialyapushkina.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yulialyapushkina.library_project.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
