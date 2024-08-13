package ru.yulialyapushkina.library_project.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.yulialyapushkina.library_project.dto.BookCreateDto;
import ru.yulialyapushkina.library_project.dto.BookDto;
import ru.yulialyapushkina.library_project.dto.BookUpdateDto;
import ru.yulialyapushkina.library_project.model.Book;
import ru.yulialyapushkina.library_project.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    @Override
    public BookDto getByNameV1(String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto getByNameV2(String name) {
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto getByNameV3(String name) {
        Specification <Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        });
        Book book = bookRepository.findOne(specification).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto createBook(BookCreateDto bookCreateDto) {
        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
        return convertEntityToDto(book);
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow();
        book.setName(bookUpdateDto.getName());
        book.setGenre(bookUpdateDto.getGenre());
        Book savedBook =  bookRepository.save(book);
        return convertEntityToDto(savedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    public List<BookDto> getAllBooks() {
        List <Book> books = bookRepository.findAll();
        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());

    }
    private Book  convertDtoToEntity (BookCreateDto bookCreateDto){
        return Book.builder()
                .name(bookCreateDto.getName())
                .genre(bookCreateDto.getGenre())
                .build();
    }

    private BookDto convertEntityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }
}
