package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.Page;

public interface PageRepository {
	Page loadPage(String key);
	Page loadPage(Long id);
	List<Page> loadAllPages();
	Page savePage(String title, String linkTitle, String body, String metaDescription, String metaKeywords);
	boolean deletePage(Long id);
	Page updatePage(Long id, String title, String linkTitle, String body, String metaDescription, String metaKeywords);
}
