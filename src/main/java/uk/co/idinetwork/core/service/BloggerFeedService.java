package uk.co.idinetwork.core.service;

import java.util.List;

import uk.co.idinetwork.core.model.BloggerFeed;

public interface BloggerFeedService {
	BloggerFeed loadBloggerFeed(String key);
	List<BloggerFeed> loadAllBloggerFeeds();
	BloggerFeed saveBloggerFeed(String name, String feedUrl, Boolean defaultFeed);
	boolean deleteBloggerFeed(Long id);
	public List<BloggerFeed> loadDefaultBloggerFeeds();
}
