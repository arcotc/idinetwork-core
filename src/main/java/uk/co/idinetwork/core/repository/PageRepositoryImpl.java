package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.Page;
import uk.co.idinetwork.core.utils.KeyUtil;

public class PageRepositoryImpl implements PageRepository {
	@Override
	public Page loadPage(String key) {
		return Page.all().filter("key", key).get();
	}

	@Override
	public List<Page> loadAllPages() {
		return Page.all().fetch();
	}

	@Override
	public Page savePage(String title, String linkTitle, String body, String metaDescription, String metaKeywords, String tags) {
		Page page = new Page();
		page.setKey(KeyUtil.buildKey(linkTitle));
		page.setTitle(title);
		page.setBody(body);
		page.setMetaDescription(metaDescription);
		page.setMetaKeywords(metaKeywords);
		page.setTags(tags);
		
		page.insert();
		
		return page;
	}

	@Override
	public boolean deletePage(Long id) {
		Page page = Page.all().filter("id", id).get();
		
		if (page == null) {
			return false;
		}
		else {
			page.delete();
			
			return true;
		}
	}

	@Override
	public Page loadPage(Long id) {
		return Page.all().filter("id", id).get();
	}

	@Override
	public Page updatePage(Long id, String title, String linkTitle, String body, String metaDescription, String metaKeywords, String tags) {
		Page page = new Page();
		page.setId(id);
		page.setKey(KeyUtil.buildKey(linkTitle));
		page.setTitle(title);
		page.setBody(body);
		page.setMetaDescription(metaDescription);
		page.setMetaKeywords(metaKeywords);
		page.setTags(tags);
		
		page.update();
		
		return page;
	}
}
