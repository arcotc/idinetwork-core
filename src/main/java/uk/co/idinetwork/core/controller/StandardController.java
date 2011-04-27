package uk.co.idinetwork.core.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.Article;
import uk.co.idinetwork.core.model.Page;
import uk.co.idinetwork.core.service.BloggerService;
import uk.co.idinetwork.core.service.PageService;
import uk.co.idinetwork.core.service.SiteNavigationService;

import com.google.gdata.client.GoogleService;

public class StandardController {
	@Autowired private BloggerService articleService;
	@Autowired private SiteNavigationService siteNavigationService;
	@Autowired private PageService pageService;
	
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
	
	protected void loadArticle(ModelAndView modelAndView, String articleKey) {
		GoogleService myService = new GoogleService("blogger", "continuing-to-learning");
		modelAndView.addObject("article", articleService.loadUserBlog(myService, articleKey));
	}
	
	protected void loadNavigation(ModelAndView modelAndView) {
		modelAndView.addObject("sideNavigation", siteNavigationService.findSideNavigation());
		modelAndView.addObject("topNavigation", siteNavigationService.findTopNavigation());
	}
	
	protected void loadPage(ModelAndView modelAndView, String key) {
		Page page = pageService.loadPage(key);
		
		if (page == null) {
			page = new Page();
			page.setTitle("Page not found");
			page.setBody("The requested page could not be found.");
		}

		modelAndView.addObject("page", page);
	}
}
