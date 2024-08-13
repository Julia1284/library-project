package ru.yulialyapushkina.library_project.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.yulialyapushkina.library_project.dto.AuthorCreateDto;
import ru.yulialyapushkina.library_project.dto.AuthorDto;
import ru.yulialyapushkina.library_project.dto.AuthorUpdateDto;
import ru.yulialyapushkina.library_project.dto.BookDto;
import ru.yulialyapushkina.library_project.model.Author;
import ru.yulialyapushkina.library_project.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();

        return convertEntityToDto(author);
    }

    @Override
    public AuthorDto getAuthorBySurname(String surname) {
        Author author = authorRepository.findAuthorBySurname(surname).orElseThrow();
        return convertEntityToDto(author);
    }

    @Override
    public AuthorDto getAuthorBySurnameBySql(String surname) {
        Author author = authorRepository.findAuthorBySurnameBySql(surname).orElseThrow();
        return convertEntityToDto(author);
    }

    @Override
    public AuthorDto getAuthorBySurnameV3(String surname) {
        Specification <Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("surname"), surname);
            }
        });
        Author author = authorRepository.findOne(specification).orElseThrow();
        return convertEntityToDto(author);
    }

    @Override
    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto) {
        Author author = authorRepository.save(convertDtoToEntity(authorCreateDto));
        return convertEntityToDto(author);
    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto) {
        Author author = authorRepository.findById(authorUpdateDto.getId()).orElseThrow();
        author.setName(authorUpdateDto.getName());
        author.setSurname(authorUpdateDto.getSurname());
        Author savedAuthor = authorRepository.save(author);
        return convertEntityToDto(savedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List <Author> authors = authorRepository.findAll();
        return authors.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private Author convertDtoToEntity (AuthorCreateDto authorCreateDto){
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }

    private AuthorDto convertEntityToDto(Author author) {
        List<BookDto> bookDtoList = null;
        if (author.getBooks() != null){
            bookDtoList = author.getBooks()
                    .stream()
                    .map(book -> BookDto.builder()
                            .genre(book.getGenre().getName())
                            .name(book.getName())
                            .id(book.getId())
                            .build()
                    ).toList();
        }

        return AuthorDto.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}
