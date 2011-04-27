package uk.co.idinetwork.core.model.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AtomEntry {
	private String xmlns;
	private AtomT id;
	private AtomT published;
	private AtomT updated;
	private AtomTValue title;
	private AtomTValue content;
	private AtomTTermValue term;

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public AtomT getId() {
		return id;
	}

	public void setId(AtomT id) {
		this.id = id;
	}

	public void setPublished(AtomT published) {
		this.published = published;
	}

	public AtomT getPublished() {
		return published;
	}

	public AtomT getUpdated() {
		return updated;
	}

	public void setUpdated(AtomT updated) {
		this.updated = updated;
	}

	public AtomTValue getTitle() {
		return title;
	}

	public void setTitle(AtomTValue title) {
		this.title = title;
	}

	public void setContent(AtomTValue content) {
		this.content = content;
	}

	public AtomTValue getContent() {
		return content;
	}

	public void setTerm(AtomTTermValue term) {
		this.term = term;
	}

	public AtomTTermValue getTerm() {
		return term;
	}
	
	
}
