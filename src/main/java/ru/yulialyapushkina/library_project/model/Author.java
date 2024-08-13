package ru.yulialyapushkina.library_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Author {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Long id;

   @Setter
   @Column(nullable = false)
    private String name;

   @Setter
    @Column (nullable = false)
    private String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
