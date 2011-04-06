package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.BloggerFeed;

public class BloggerFeedRepositoryImpl implements BloggerFeedRepository {
	@Override
	public BloggerFeed loadBloggerFeed(String name) {
		return BloggerFeed.all().filter("name", name).get();
	}

	@Override
	public List<BloggerFeed> loadAllBloggerFeeds() {
		return BloggerFeed.all().fetch();
	}

	@Override
	public BloggerFeed saveBloggerFeed(String name, String feedUrl) {
		BloggerFeed bloggerFeed = new BloggerFeed();
		bloggerFeed.setName(name);
		bloggerFeed.setFeedUrl(feedUrl);
		
		bloggerFeed.insert();
		
		return bloggerFeed;
	}

	@Override
	public boolean deleteBloggerFeed(Long id) {
		BloggerFeed bloggerFeed = BloggerFeed.all().filter("id", id).get();
		
		if (bloggerFeed == null) {
			return false;
		}
		else {
			bloggerFeed.delete();
			
			return true;
		}
	}
}
