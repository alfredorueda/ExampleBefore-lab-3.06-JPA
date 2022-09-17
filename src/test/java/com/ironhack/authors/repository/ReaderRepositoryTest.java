package com.ironhack.authors.repository;

import com.ironhack.authors.enums.ReaderProfile;
import com.ironhack.authors.enums.Specialty;
import com.ironhack.authors.model.authors.*;
import com.ironhack.authors.repository.authors.ArticleRepository;
import com.ironhack.authors.repository.authors.BlogPostRepository;
import com.ironhack.authors.repository.authors.BookRepository;
import com.ironhack.authors.repository.authors.ReaderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReaderRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReaderRepository readerRepository;


    @BeforeEach
    void setUp() {

        Reader paz = new Reader();
        paz.setFirstName("Paz");
        paz.setLastName("Alegría");
        paz.setProfile(ReaderProfile.INTERMEDIATE);

        readerRepository.save(paz);

        Reader esperanza = new Reader();
        esperanza.setFirstName("Esperanza");
        esperanza.setLastName("Amor");
        esperanza.setProfile(ReaderProfile.PROFESSIONAL);

        readerRepository.save(esperanza);

        Reader rodrigo = new Reader();
        rodrigo.setFirstName("Rodrigo");
        rodrigo.setLastName("Castro");
        rodrigo.setProfile(ReaderProfile.PROFESSIONAL);

        readerRepository.save(rodrigo);

        Book javaBook = new Book();
        javaBook.setTitle("Java is Fun");
        javaBook.setNumPages(200);
        javaBook.setPublishingDate(LocalDate.of(2017, 4, 4));

        javaBook.getReaders().add(esperanza);
        esperanza.getPublications().add(javaBook);

        bookRepository.save(javaBook);

        Book jpaBook = new Book();
        jpaBook.setTitle("JPA is awesome!");
        jpaBook.setNumPages(300);
        jpaBook.setPublishingDate(LocalDate.of(2022, 4, 4));

        //La asociación es bidireccional
        jpaBook.getReaders().add(esperanza);
        jpaBook.getReaders().add(paz);
        esperanza.getPublications().add(jpaBook);
        paz.getPublications().add(jpaBook);

        bookRepository.save(jpaBook);

        BlogPost inheritancePost = new BlogPost();
        inheritancePost.setTitle("Inheritance Strategies with JPA and Hibernate – The Complete Guide");
        inheritancePost.setPublishingDate(LocalDate.of(2020, 1, 23));
        inheritancePost.setUrl("https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/#Joined");

        inheritancePost.getReaders().add(paz);
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
        apiArticle.getReaders().add(rodrigo);
        rodrigo.getPublications().add(apiArticle);

        runningArticle.getReaders().add(rodrigo);
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
    void dropAll(){
        bookRepository.deleteAll();
        blogPostRepository.deleteAll();
        articleRepository.deleteAll();
        readerRepository.deleteAll();
    }

    @Test
    void test_total_readers(){
        assertEquals(3, readerRepository.findAll().size());
    }
    @Test
    void test_find_all_professional_readers(){
        assertEquals(2, readerRepository.findAllByProfile(ReaderProfile.PROFESSIONAL).size());
    }

}
