package uk.co.idinetwork.core.service;

import java.util.Collection;
import java.util.Map;

import uk.co.idinetwork.core.model.Article;

import com.google.gdata.client.GoogleService;

public interface BloggerService {
	public Map<String, Article> loadUserBlogs(GoogleService myService);
	public Map<String, Article> loadUserBlogs(GoogleService myService, String tags);
	public Collection<Article> loadArticlesOrderedByDateLatestFirst(GoogleService myService);
	public Collection<Article> loadArticlesOrderedByDateLatestFirst(GoogleService myService, String tags);
	public Article loadUserBlog(GoogleService myService, String articleKey);
	public void flushCache();
}
