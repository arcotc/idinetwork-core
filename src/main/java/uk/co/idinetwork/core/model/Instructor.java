package uk.co.idinetwork.core.model;

import siena.Id;
import siena.Model;
import siena.Query;

public class Instructor extends Model {
	@Id	private Long id;
	private String forename;
	private String surname;
	private String adiCode;
//	private List<Coverage> coverages;

	public Instructor() {
		// Empty - default constructor
	}
	
	public Instructor(String forename, String surname, String adiCode) {
		this.forename = forename;
		this.surname = surname;
		this.adiCode = adiCode;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAdiCode() {
		return adiCode;
	}

	public void setAdiCode(String adiCode) {
		this.adiCode = adiCode;
	}
/*	
	public List<Coverage> getCoverages() {
		return coverages;
	}

	public void setCoverages(List<Coverage> coverages) {
		this.coverages = coverages;
	}
	*/

	@Override
	public String toString() {
		return "Instructor {" + id + ", " + forename + ", " + surname + ", " + adiCode + "}";
	}
	
	public static Query<Instructor> all() {
		return all(Instructor.class);
	}
}
