package com.ironhack.authors.model.authors;

import com.ironhack.authors.enums.Specialty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Author extends User{

	@Enumerated(EnumType.STRING)
	private Specialty specialty;

	@ManyToMany(mappedBy="authors")
	private List<Publication> publications = new ArrayList<Publication>();


	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Author author = (Author) o;
		return Objects.equals(publications, author.publications);
	}

	@Override
	public int hashCode() {
		return Objects.hash(publications);
	}



}