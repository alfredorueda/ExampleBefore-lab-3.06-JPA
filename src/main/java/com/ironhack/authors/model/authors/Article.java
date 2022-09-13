package com.ironhack.authors.model.authors;

import com.ironhack.authors.enums.Specialty;
import com.sun.istack.NotNull;
import org.hibernate.type.SpecialOneToOneType;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Article extends Publication{

    private int revisions;
    private int citations;
    @NotNull
    private Specialty specialty ;

    public int getRevisions() {
        return revisions;
    }

    public void setRevisions(int revisions) {
        this.revisions = revisions;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Article article = (Article) o;
        return revisions == article.revisions && citations == article.citations && specialty == article.specialty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), revisions, citations, specialty);
    }
}
