package uk.co.idinetwork.core.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.Article;
import uk.co.idinetwork.core.model.BloggerFeed;
import uk.co.idinetwork.core.model.json.Atom;
import uk.co.idinetwork.core.service.BloggerFeedService;

import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Category;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.util.ServiceException;

public class BloggerRepositoryImpl implements BloggerRepository {
	private static final Log log = LogFactory.getLog(BloggerRepositoryImpl.class);
	private static Map<String, Article> cache = new TreeMap<String, Article>();
	private static Date cachedExpireDate = null;
	@Autowired BloggerFeedService bloggerFeedService;
	
	@Override
	public Map<String, Article> loadUserBlogs(GoogleService myService) {
		if (isCacheExpired()) {
			Map<String, Article> articles = loadUserBlogs(myService, null);
			
			cache = articles;
			GregorianCalendar cal = new GregorianCalendar();
			cal.add(Calendar.MINUTE, 15);
			cachedExpireDate = cal.getTime();
		}

		return cache;
	}
	
	
	@Override
	public Map<String, Article> loadUserBlogs(GoogleService myService, String tags) {
		Map<String, Article> articles = new TreeMap<String, Article>();
		
		List<BloggerFeed> bloggerFeeds = bloggerFeedService.loadAllBloggerFeeds();
		for (BloggerFeed bloggerFeed : bloggerFeeds) {
			try {
				// Request the feed
				final URL feedUrl = new URL(bloggerFeed.getFeedUrl());
				Feed resultFeed = myService.getFeed(feedUrl, Feed.class);
		
				// Print the results
				for (int i = 0; i < resultFeed.getEntries().size(); i++) {
					Entry entry = resultFeed.getEntries().get(i);
					
					boolean processThisRecord = false;
					if (StringUtils.isBlank(tags)) {
						processThisRecord = true;
					}
					else {
						for (String tag : tags.split(",")) {
							for (Category category : entry.getCategories()) {
								if (StringUtils.contains(category.getTerm(),tag)) {
									processThisRecord = true;
									break;
								}
							}
						}
					}
					
					if (processThisRecord) {
						Article article = new Article(entry.getTitle().getPlainText(), entry.getTitle().getPlainText(), entry.getTextContent().getContent().getPlainText().substring(0, 50) + "...", entry.getTextContent().getContent().getPlainText());
						
						// TODO: Replace with RegEx
						String id = entry.getId();
						//tag:blogger.com,1999:blog-7745840761108268253.post-2525821190563293294
						article.setId(id.substring(id.indexOf("blog-") + 5, id.indexOf(".post")));
						article.setPostId(id.substring(id.indexOf("post-") + 5));
						article.setDateCreated(new Date(entry.getPublished().getValue()));
						articles.put(article.getKey(), article);
					}
				}
			}
			catch (ServiceException serviceException) {
				log.error("Error occurred parsing feed URL " + bloggerFeed.getFeedUrl() + ": " + serviceException.getMessage());
			}
			catch (IOException ioException) {
				log.error("Error occurred parsing feed URL " + bloggerFeed.getFeedUrl() + ": " + ioException.getMessage());
			}
		}
		
		return articles;
	}

	@Override
	public Article loadUserBlog(GoogleService myService, String articleKey) {
		Article article = null;

		if (isCacheExpired()) {
			loadUserBlogs(myService);
		}

		if (cache.containsKey(articleKey)) {
			try {
				// Request the feed
				final URL feedUrl = new URL("http://www.blogger.com/feeds/" + cache.get(articleKey).getId() + "/posts/default/" + cache.get(articleKey).getPostId() + "?alt=json");
				
				ObjectMapper mapper = new ObjectMapper();
				BufferedReader br = new BufferedReader(new InputStreamReader(feedUrl.openStream()));
				Atom atom = mapper.readValue(br, Atom.class);
				
				article = new Article(atom.getEntry().getTitle().getT(), atom.getEntry().getTitle().getT(), atom.getEntry().getContent().getT(), atom.getEntry().getContent().getT());
				
				String id = atom.getEntry().getId().getT();
				article.setId(id.substring(id.indexOf("blog-") + 5, id.indexOf(".post")));
				article.setPostId(id.substring(id.indexOf(".post") + 5));
				try {
					SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String publishedDate = atom.getEntry().getPublished().getT().replaceAll("T", " ");
					article.setDateCreated(DATE_FORMAT.parse(publishedDate.substring(0,publishedDate.length() - 10)));
				}
				catch (ParseException parseException) {
					log.error("Could not parse publication date " + atom.getEntry().getPublished());
				}
			}
			catch (IOException ioException) {
				log.error("Error occurred parsing feed URL for articleId " + articleKey + ": " + ioException.getMessage());
			}
		}
		
		return article;
	}
	
	private Boolean isCacheExpired() {
		return ((cache == null) || (cache.size() == 0) || (new Date().after(cachedExpireDate)));
	}

	@Override
	public void flushCache() {
		cache = null;
	}
}
