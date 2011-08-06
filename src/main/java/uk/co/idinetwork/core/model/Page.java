package uk.co.idinetwork.core.model;

import java.util.List;

import siena.Id;
import siena.Model;
import siena.Query;

public class Page extends Model {
	@Id private Long id;
	private String key;
	private String title;
	private String intro;
	private String body;
	private String metaDescription;
	private String metaKeywords;
	private String tags;
	private String linkText;
	private String templateName;
	private List<Page> linkTextAsPages;
	private String includePath;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTags() {
		return tags;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLinkText() {
		return linkText;
	}
	
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public List<Page> getLinkTextAsPages() {
		return linkTextAsPages;
	}
	
	public void setLinkTextAsPages(List<Page> linkTextAsPages) {
		this.linkTextAsPages = linkTextAsPages;
	}

	public void setIncludePath(String includePath) {
		this.includePath = includePath;
	}

	public String getIncludePath() {
		return includePath;
	}

	public static Query<Page> all() {
		return all(Page.class);
	}
}
