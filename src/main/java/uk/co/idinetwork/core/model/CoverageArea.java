package uk.co.idinetwork.core.model;

import siena.Id;
import siena.Model;

public class CoverageArea extends Model {
	@Id private Long id;
	private String townCity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTownCity() {
		return townCity;
	}

	public void setTownCity(String townCity) {
		this.townCity = townCity;
	}
}
