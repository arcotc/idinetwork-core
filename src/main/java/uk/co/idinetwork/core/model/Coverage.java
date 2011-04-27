package uk.co.idinetwork.core.model;

import java.util.List;

import siena.Id;
import siena.Model;

public class Coverage extends Model {
	@Id
	private Long id;
	private String postcode;
	private List<CoverageArea> coverageAreas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public List<CoverageArea> getCoverageAreas() {
		return coverageAreas;
	}

	public void setCoverageAreas(List<CoverageArea> coverageAreas) {
		this.coverageAreas = coverageAreas;
	}
}
