package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    long countByRevisions(int revisions);

    @Query("select sum (revisions) from Article")
    long findTotalRevisions();

    @Query("select a from Article a where a.title = :name")
    Article findByTitle(@Param("name") String title);

    List<Article> findByTitleContaining(String name);

}
