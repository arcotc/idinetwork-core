package uk.co.idinetwork.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.Page;
import uk.co.idinetwork.core.repository.PageRepository;

public class PageServiceImpl implements PageService {
	@Autowired private PageRepository pageRepository;
	
	@Override
	public Page loadPage(String key) {
		return pageRepository.loadPage(key);
	}

	@Override
	public List<Page> loadAllPages() {
		return pageRepository.loadAllPages();
	}

	@Override
	public Page savePage(String title, String linkTitle, String linkText, String intro, String body, String metaDescription, String metaKeywords, String tags, String templateName) {
		return pageRepository.savePage(title, linkTitle, linkText, intro, body, metaDescription, metaKeywords, tags, templateName);
	}

	@Override
	public boolean deletePage(Long id) {
		return pageRepository.deletePage(id);
	}

	@Override
	public Page loadPage(Long id) {
		return pageRepository.loadPage(id);
	}

	@Override
	public Page updatePage(Long id, String title, String linkTitle, String linkText, String intro, String body, String metaDescription, String metaKeywords, String tags, String templateName) {
		return pageRepository.updatePage(id, title, linkTitle, linkText, intro, body, metaDescription, metaKeywords, tags, templateName);
	}
}
