package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select sum (revisions) from Article")
    long findTotalRevisions();

}
