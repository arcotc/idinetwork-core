package uk.co.idinetwork.core.service;

import java.util.Collection;

import uk.co.idinetwork.core.model.Article;

import com.google.gdata.client.GoogleService;

public interface BloggerService {
	public Collection<Article> loadUserBlogs(GoogleService myService);
	public Article loadUserBlog(GoogleService myService, String articleKey);
	public void flushCache();
}
