package com.ironhack.authors.repository.authors;

import com.ironhack.authors.enums.ReaderProfile;
import com.ironhack.authors.model.authors.Reader;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

    List<Reader> findAllByProfile(ReaderProfile profile);


}
