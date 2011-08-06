package uk.co.idinetwork.core.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
import uk.co.idinetwork.core.model.json.AtomSchemeTerm;
import uk.co.idinetwork.core.service.BloggerFeedService;
import uk.co.idinetwork.core.utils.KeyUtil;

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
	public List<BloggerFeed> loadDefaultBloggerFeeds() {
		return bloggerFeedService.loadDefaultBloggerFeeds();
	}
	
	@Override
	public Map<String, Article> loadUserBlogs(GoogleService myService, String tags) {
		Map<String, Article> articles = new TreeMap<String, Article>();
		
		List<BloggerFeed> bloggerFeeds = bloggerFeedService.loadAllBloggerFeeds();
		log.info("Blog trace: starting");
		for (BloggerFeed bloggerFeed : bloggerFeeds) {
			Boolean isDefaultBlog = false;
			try {
				if (bloggerFeed.getDefaultFeed()) {
					isDefaultBlog = true;
				}
			}
			catch (NullPointerException nullPointerException) {
				log.info("Default Feed setting for blogger feed " + bloggerFeed.getName() + " (" + bloggerFeed.getFeedUrl() + ") is not set");
			}
			try {
				// Request the feed
				final URL feedUrl = new URL(bloggerFeed.getFeedUrl());
				log.info("Blog trace: feedUrl=" + feedUrl.toString());
				
				Feed resultFeed = myService.getFeed(feedUrl, Feed.class);
				log.info("Blog trace: resultFeed == null is " + (resultFeed == null));
		
				// Print the results
				log.info("Blog trace: resultFeed.getEntries().size() = " + resultFeed.getEntries().size());
				for (int i = 0; i < resultFeed.getEntries().size(); i++) {
					Entry entry = resultFeed.getEntries().get(i);
					log.info("Blog trace: entry(" + i + ") == null is " + (entry == null));
					
					boolean processThisRecord = false;
					if (StringUtils.isBlank(tags)) {
						processThisRecord = true;
					}
					else {
						for (String tag : tags.split(",")) {
							log.info("Blog trace: tag == " + tag);
							for (Category category : entry.getCategories()) {
								log.info("Blog trace: category.getTerm() == null is " + (category.getTerm() == null));
								log.info("Blog trace: bloggerFeed.getDefaultFeed() == null is " + (isDefaultBlog == null));
								String categoryTerm = category.getTerm().toLowerCase();
								if (!isDefaultBlog && categoryTerm.equalsIgnoreCase("mysiteonly")) {
									processThisRecord = false;
									break;
								}
								else {
									if (StringUtils.contains(categoryTerm,tag.toLowerCase())) {
										processThisRecord = true;
									}
								}
							}
						}
					}
					
					if (processThisRecord) {
						log.info("Blog trace: processThisRecord");
						int x = entry.getTextContent().getContent().getPlainText().length() > 50 ? 50 : entry.getTextContent().getContent().getPlainText().length(); 
						Article article = new Article(entry.getTitle().getPlainText(), entry.getTitle().getPlainText(), entry.getTextContent().getContent().getPlainText().substring(0, x) + "...", entry.getTextContent().getContent().getPlainText());
						
						// TODO: Replace with RegEx
						String id = entry.getId();
						//tag:blogger.com,1999:blog-7745840761108268253.post-2525821190563293294
						article.setId(id.substring(id.indexOf("blog-") + 5, id.indexOf(".post")));
						article.setPostId(id.substring(id.indexOf("post-") + 5));
						article.setDateCreated(new Date(entry.getPublished().getValue()));
						article.setSourceFeed(bloggerFeed);

						articles.put(KeyUtil.buildKey(article.getKey()), article);
					}
				}
			}
			catch (ServiceException serviceException) {
				log.error("ServiceException Error occurred parsing feed URL " + bloggerFeed.getFeedUrl() + ": " + serviceException.getMessage(), serviceException);
			}
			catch (IOException ioException) {
				log.error("IOException Error occurred parsing feed URL " + bloggerFeed.getFeedUrl() + ": " + ioException.getMessage(), ioException);
			}
			catch (Exception e) {
				log.error("Exception Error occurred parsing feed URL " + bloggerFeed.getFeedUrl() + ": " + e.getMessage(), e);
			}
		}
		log.info("Blog trace: Finishing");
		
		return articles;
	}
	
	@Override
	public Collection<Article> loadArticlesOrderedByDateLatestFirst(GoogleService myService, String tags) {
		Map<String, Article> userBlogs = loadUserBlogs(myService, tags);
		Map<Long, Collection<Article>> orderedBlogs = new TreeMap<Long, Collection<Article>>();

		for (Article article : userBlogs.values()) {
			Long key = article.getDateCreated().getTime() * -1;
			Collection<Article> list = new ArrayList<Article>();
			
			if (orderedBlogs.containsKey(key)) {
				list = orderedBlogs.get(key);
			}
			else {
				orderedBlogs.put(article.getDateCreated().getTime() * -1, list);
			}
			
			list.add(article);
 		}
		
		Collection<Article> values = new ArrayList<Article>();
		for (Collection<Article> articles : orderedBlogs.values()) {
			values.addAll(articles);
		}
		
		return values;
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
				
				String tags = "";
				boolean first = true;
				for (AtomSchemeTerm atomSchemeTerm : atom.getEntry().getCategory()) {
					if (first) {
						first = false;
					}
					else {
						tags += ",";
					}
					tags += atomSchemeTerm.getTerm();
				}
				article.setTags(tags);
			}
			catch (IOException ioException) {
				log.error("IOException Error occurred parsing feed URL for articleId " + articleKey + ": " + ioException.getMessage(), ioException);
			}
		}
		
		return article;
	}
	
	private Boolean isCacheExpired() {
		cache = null;
		return ((cache == null) || (cache.size() == 0) || (new Date().after(cachedExpireDate)));
	}

	@Override
	public void flushCache() {
		cache = null;
	}
}
