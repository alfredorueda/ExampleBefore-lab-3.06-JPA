package com.ironhack.authors.repository;

import com.ironhack.authors.model.authors.Author;
import com.ironhack.authors.model.authors.BlogPost;
import com.ironhack.authors.model.authors.Book;
import com.ironhack.authors.repository.authors.AuthorRepository;
import com.ironhack.authors.repository.authors.BlogPostRepository;
import com.ironhack.authors.repository.authors.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;


    @BeforeEach
    void setUp() {

        Author paz = new Author();
        paz.setFirstName("Paz");
        paz.setLastName("Alegría");

        authorRepository.save(paz);

        Author esperanza = new Author();
        esperanza.setFirstName("Esperanza");
        esperanza.setLastName("Amor");

        authorRepository.save(esperanza);

        Book javaBook = new Book();
        javaBook.setTitle("Java is Fun");
        javaBook.setNumPages(200);
        javaBook.setPublishingDate(LocalDate.of(2017, 4, 4));

        javaBook.getAuthors().add(esperanza);
        esperanza.getPublications().add(javaBook);

        bookRepository.save(javaBook);

        Book jpaBook = new Book();
        jpaBook.setTitle("JPA is awesome!");
        jpaBook.setNumPages(300);
        jpaBook.setPublishingDate(LocalDate.of(2022, 4, 4));

        jpaBook.getAuthors().add(esperanza);
        esperanza.getPublications().add(jpaBook);

        bookRepository.save(jpaBook);

        BlogPost inheritancePost = new BlogPost();
        inheritancePost.setTitle("Inheritance Strategies with JPA and Hibernate – The Complete Guide");
        inheritancePost.setPublishingDate(LocalDate.of(2020, 1, 23));
        inheritancePost.setUrl("https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/#Joined");

        inheritancePost.getAuthors().add(paz);
        paz.getPublications().add(inheritancePost);

        blogPostRepository.save(inheritancePost);


    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
        blogPostRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void findByAuthors_FirstNameAndAuthors_LastNameOrderByTitleAsc_successful() {
        assertEquals(2,
                bookRepository.findByAuthors_FirstNameAndAuthors_LastNameOrderByTitleAsc("Esperanza","Amor").size());

    }

    @Test
    void findByPublishingDateBetween_successful() {
        assertEquals(1,
                blogPostRepository.findByPublishingDateBetween(
                        LocalDate.of(2019, 1, 23),
                        LocalDate.of(2021, 1, 23)
                ).size());

    }



}