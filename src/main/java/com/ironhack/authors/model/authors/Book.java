package com.ironhack.authors.model.authors;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Book extends Publication {

	private int numPages;

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), numPages);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (getId() != null) {
			if (!getId().equals(other.getId())) {
				return false;
			}
		}
		return true;
	}
}