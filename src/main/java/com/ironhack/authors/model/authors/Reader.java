package com.ironhack.authors.model.authors;

import com.ironhack.authors.enums.ReaderProfile;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Reader extends User{

    @Enumerated(EnumType.STRING)
    private ReaderProfile profile;

    @ManyToMany(mappedBy = "readers")
    private List<Publication> publications = new ArrayList<>();


    public ReaderProfile getProfile() {
        return profile;
    }

    public void setProfile(ReaderProfile profile) {
        this.profile = profile;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
