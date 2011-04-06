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
	public Page savePage(String title, String linkTitle, String body, String metaDescription, String metaKeywords) {
		return pageRepository.savePage(title, linkTitle, body, metaDescription, metaKeywords);
	}

	@Override
	public boolean deletePage(Long id) {
		return pageRepository.deletePage(id);
	}
}
