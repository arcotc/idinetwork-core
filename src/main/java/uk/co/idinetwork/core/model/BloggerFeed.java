package uk.co.idinetwork.core.model;

import siena.Id;
import siena.Model;
import siena.Query;

public class BloggerFeed extends Model {
	@Id private Long id;
	private String name;
	private String feedUrl;
	private Boolean defaultFeed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeedUrl() {
		return feedUrl;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}
	
	public Boolean getDefaultFeed() {
		return defaultFeed;
	}
	
	public void setDefaultFeed(Boolean defaultFeed) {
		this.defaultFeed = defaultFeed;
	}

	public static Query<BloggerFeed> all() {
		return all(BloggerFeed.class);
	}
}
