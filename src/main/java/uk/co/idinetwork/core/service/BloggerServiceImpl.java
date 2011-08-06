package uk.co.idinetwork.core.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.Article;
import uk.co.idinetwork.core.model.BloggerFeed;
import uk.co.idinetwork.core.repository.BloggerRepository;

import com.google.gdata.client.GoogleService;

public class BloggerServiceImpl implements BloggerService {
	@Autowired private BloggerRepository bloggerRepository;

	@Override
	public Map<String, Article> loadUserBlogs(GoogleService myService) {
		return bloggerRepository.loadUserBlogs(myService);
	}

	@Override
	public Map<String, Article> loadUserBlogs(GoogleService myService, String tags) {
		return bloggerRepository.loadUserBlogs(myService, tags);
	}
	
	@Override
	public Collection<Article> loadArticlesOrderedByDateLatestFirst(GoogleService myService) {
		return bloggerRepository.loadArticlesOrderedByDateLatestFirst(myService, null);
	}
	
	@Override
	public Collection<Article> loadArticlesOrderedByDateLatestFirst(GoogleService myService, String tags) {
		return bloggerRepository.loadArticlesOrderedByDateLatestFirst(myService, tags);
	}
	
	@Override
	public Article loadUserBlog(GoogleService myService, String articleKey) {
		return bloggerRepository.loadUserBlog(myService, articleKey);
	}

	@Override
	public void flushCache() {
		bloggerRepository.flushCache();
	}
	
	@Override
	public List<BloggerFeed> loadDefaultBloggerFeeds() {
		return bloggerRepository.loadDefaultBloggerFeeds();
	}
}
