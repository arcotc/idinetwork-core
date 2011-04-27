package uk.co.idinetwork.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.Article;
import uk.co.idinetwork.core.repository.BloggerRepository;

import com.google.gdata.client.GoogleService;

public class BloggerServiceImpl implements BloggerService {
	@Autowired private BloggerRepository bloggerRepository;

	@Override
	public Map<String, Article> loadUserBlogs(GoogleService myService) {
		Map<String, Article> userBlogs = bloggerRepository.loadUserBlogs(myService);
		Collection<Article> articles = (Collection<Article>) userBlogs.values();
		List<Article> articlesList = new ArrayList<Article>(articles);
		Collections.sort(articlesList);
		return userBlogs;
	}

	@Override
	public Map<String, Article> loadUserBlogs(GoogleService myService, String tags) {
		return bloggerRepository.loadUserBlogs(myService, tags);
	}
	
	@Override
	public Article loadUserBlog(GoogleService myService, String articleKey) {
		return bloggerRepository.loadUserBlog(myService, articleKey);
	}

	@Override
	public void flushCache() {
		bloggerRepository.flushCache();
	}
}
