package uk.co.idinetwork.core.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.Article;
import uk.co.idinetwork.core.model.Page;
import uk.co.idinetwork.core.service.BloggerService;
import uk.co.idinetwork.core.service.GeneralService;
import uk.co.idinetwork.core.service.PageService;
import uk.co.idinetwork.core.service.SiteNavigationService;

import com.google.gdata.client.GoogleService;

public class StandardController {
	@Autowired private BloggerService articleService;
	@Autowired private SiteNavigationService siteNavigationService;
	@Autowired private PageService pageService;
	@Autowired private GeneralService generalService;
	
	protected void loadArticles(ModelAndView modelAndView) {
		GoogleService myService = new GoogleService("blogger", "continuing-to-learning");
		Collection<Article> userBlogs = articleService.loadUserBlogs(myService).values();
		modelAndView.addObject("articles", userBlogs);
	}
	
	protected void loadArticles(ModelAndView modelAndView, String tags) {
		GoogleService myService = new GoogleService("blogger", "continuing-to-learning");
		Collection<Article> userBlogs = articleService.loadUserBlogs(myService, tags).values();
		modelAndView.addObject("articles", userBlogs);
	}
	
	protected void loadArticlesOrderedByDateLatestFirst(ModelAndView modelAndView, String tags) {
		GoogleService myService = new GoogleService("blogger", "continuing-to-learning");
		Collection<Article> userBlogs = articleService.loadUserBlogs(myService, tags).values();
		
		Map<Long, Collection<Article>> orderedBlogs = new TreeMap<Long, Collection<Article>>();
		for (Article article : userBlogs) {
			Long key = article.getDateCreated().getTime() * -1;
			Collection<Article> list = new ArrayList<Article>();
			
			if (orderedBlogs.containsKey(key)) {
				list = orderedBlogs.get(key);
			}
			
			list.add(article);
 		}
		
		modelAndView.addObject("articles", orderedBlogs.values());
	}
	
	protected void loadArticle(ModelAndView modelAndView, String articleKey) {
		GoogleService myService = new GoogleService("blogger", "continuing-to-learning");
		modelAndView.addObject("article", articleService.loadUserBlog(myService, articleKey));
	}
	
	protected void loadNavigation(ModelAndView modelAndView) {
		modelAndView.addObject("sideNavigation", siteNavigationService.findSideNavigation());
		modelAndView.addObject("topNavigation", siteNavigationService.findTopNavigation());
	}
	
	protected Page loadPage(ModelAndView modelAndView, String mavName, String key) {
		Page page = pageService.loadPage(key);
		
		if (page == null) {
			page = new Page();
			page.setTitle("Page not found");
			page.setBody("<p>The requested page could not be found.</p>");
		}

		modelAndView.addObject(mavName, page);
		
		return page;
	}
	
	protected void loadConfig(ModelAndView modelAndView) {
		modelAndView.addObject("configs",generalService.findConfig());
	}
}
