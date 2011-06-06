package uk.co.idinetwork.core.model.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class AtomSchemeTerm {
	private String scheme;
	private String term;

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getScheme() {
		return scheme;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTerm() {
		return term;
	}
}
