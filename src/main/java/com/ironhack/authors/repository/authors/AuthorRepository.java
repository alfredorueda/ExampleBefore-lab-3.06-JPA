package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
