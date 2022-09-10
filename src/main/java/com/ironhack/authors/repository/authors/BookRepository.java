package com.ironhack.authors.repository.authors;

import com.ironhack.authors.model.authors.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b inner join b.authors authors " +
            "where authors.firstName = :firstName and authors.lastName = :lastName " +
            "order by b.title")
    List<Book> findByAuthors_FirstNameAndAuthors_LastNameOrderByTitleAsc
            (@Param("firstName") String firstName, @Param("lastName") String lastName);


}
