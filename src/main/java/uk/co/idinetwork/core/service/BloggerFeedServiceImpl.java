package uk.co.idinetwork.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.BloggerFeed;
import uk.co.idinetwork.core.repository.BloggerFeedRepository;

public class BloggerFeedServiceImpl implements BloggerFeedService {
	@Autowired private BloggerFeedRepository bloggerFeedRepository;
	
	@Override
	public BloggerFeed loadBloggerFeed(String name) {
		return bloggerFeedRepository.loadBloggerFeed(name);
	}

	@Override
	public List<BloggerFeed> loadAllBloggerFeeds() {
		return bloggerFeedRepository.loadAllBloggerFeeds();
	}

	@Override
	public BloggerFeed saveBloggerFeed(String name, String feedUrl, Boolean defaultFeed) {
		return bloggerFeedRepository.saveBloggerFeed(name, feedUrl, defaultFeed);
	}

	@Override
	public boolean deleteBloggerFeed(Long id) {
		return bloggerFeedRepository.deleteBloggerFeed(id);
	}
}
