package uk.co.idinetwork.core.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.Article;
import uk.co.idinetwork.core.repository.BloggerRepository;

import com.google.gdata.client.GoogleService;

public class BloggerServiceImpl implements BloggerService {
	@Autowired private BloggerRepository bloggerRepository;

	@Override
	public Collection<Article> loadUserBlogs(GoogleService myService) {
		Collection<Article> userBlogs = bloggerRepository.loadUserBlogs(myService);
		Collections.sort((List<Article>) userBlogs);
		return userBlogs;
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
