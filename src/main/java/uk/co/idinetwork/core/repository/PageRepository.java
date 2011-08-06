package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.Page;

public interface PageRepository {
	Page loadPage(String key);
	Page loadPage(Long id);
	List<Page> loadAllPages();
	Page savePage(String title, String linkTitle, String linkText, String intro, String body, String metaDescription, String metaKeywords, String tags, String templateName, String includePath);
	boolean deletePage(Long id);
	Page updatePage(Long id, String title, String linkTitle, String linkText, String intro, String body, String metaDescription, String metaKeywords, String tags, String templateName, String includePath);
}
