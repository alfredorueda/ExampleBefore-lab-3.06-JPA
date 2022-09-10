package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Query("select b from BlogPost b where b.publishingDate between :publishingDateStart and :publishingDateEnd")
    List<BlogPost> findByPublishingDateBetween
            (@Param("publishingDateStart") LocalDate publishingDateStart,
             @Param("publishingDateEnd") LocalDate publishingDateEnd);
}