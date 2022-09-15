package com.ironhack.authors.repository;

import com.ironhack.authors.enums.Specialty;
import com.ironhack.authors.model.authors.*;
import com.ironhack.authors.repository.authors.ArticleRepository;
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
import java.time.Month;
import java.util.List;

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

    @Autowired
    private ArticleRepository articleRepository;


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

        Author rodrigo = new Author();
        rodrigo.setFirstName("Rodrigo");
        rodrigo.setLastName("Castro");

        authorRepository.save(rodrigo);


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

        //La asociación es bidireccional
        jpaBook.getAuthors().add(esperanza);
        jpaBook.getAuthors().add(paz);
        esperanza.getPublications().add(jpaBook);
        paz.getPublications().add(jpaBook);

        bookRepository.save(jpaBook);

        BlogPost inheritancePost = new BlogPost();
        inheritancePost.setTitle("Inheritance Strategies with JPA and Hibernate – The Complete Guide");
        inheritancePost.setPublishingDate(LocalDate.of(2020, 1, 23));
        inheritancePost.setUrl("https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/#Joined");

        inheritancePost.getAuthors().add(paz);
        paz.getPublications().add(inheritancePost);

        blogPostRepository.save(inheritancePost);

        Article apiArticle = new Article();
        apiArticle.setTitle("What is an API? Application programming interfaces explained");
        apiArticle.setPublishingDate(LocalDate.of(2022, Month.APRIL, 19));
        apiArticle.setSpecialty(Specialty.IT);
        apiArticle.setCitations(3);
        apiArticle.setRevisions(1);

        Article runningArticle = new Article();
        runningArticle.setTitle("The 20 Best Running Tips for Beginners");
        runningArticle.setPublishingDate(LocalDate.of(2020, Month.JUNE, 9));
        runningArticle.setSpecialty(Specialty.SPORTS);
        runningArticle.setCitations(2);
        runningArticle.setRevisions(5);

        // Create associations on the authors table and the publications table
        apiArticle.getAuthors().add(rodrigo);
        rodrigo.getPublications().add(apiArticle);

        runningArticle.getAuthors().add(rodrigo);
        rodrigo.getPublications().add(runningArticle);


        articleRepository.save(apiArticle);
        articleRepository.save(runningArticle);


        Article developerArticle = new Article();
        developerArticle.setTitle("20 tips to find your dream job for beginners");
        developerArticle.setPublishingDate(LocalDate.of(2022, Month.SEPTEMBER, 15));
        developerArticle.setSpecialty(Specialty.IT);

        Author jose = new Author();
        jose.setFirstName("jose");

        developerArticle.getAuthors().add(jose);
        jose.getPublications().add(developerArticle);

        articleRepository.save(developerArticle);



    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
        blogPostRepository.deleteAll();
        authorRepository.deleteAll();
        articleRepository.deleteAll();
    }

    @Test
    void findByAuthors_FirstNameAndAuthors_LastNameOrderByTitleAsc_successful() {

        // Just for demonstrating purposes
        List<Author> authorList = authorRepository.findAll();

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

    @Test
    void checkCitationsTotal_successful() {
        assertEquals(6, articleRepository.findTotalRevisions());

    }
  @Test
    void find_article_by_title_successful() {

        assertEquals("20 tips to find your dream job for beginners",
                    articleRepository.findByTitle("20 tips to find your dream job for beginners").getTitle());

    }

    @Test
    void find_article_containing_word_successful() {

        assertEquals(2, articleRepository.findByTitleContaining("20").size());
        assertEquals(2, articleRepository.findByTitleContaining("beginners").size());

    }

}