package uk.co.idinetwork.core.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.BloggerFeed;
import uk.co.idinetwork.core.service.BloggerFeedService;

@Controller
public class BloggerFeedController {
	private static final String CONTROLLER_MAPPING = "/management/blogger-feed";
	private static final String VIEW = "management/blogger-feed";
	private static final String CONFIRMATION_VIEW = "management/blogger-feed-confirmation";
	
	@Autowired private BloggerFeedService bloggerFeedService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView siteNavigationList() {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		
		modelAndView.addObject("bloggerFeeds", bloggerFeedService.loadAllBloggerFeeds());
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/add", method=RequestMethod.POST)
	public ModelAndView addTopNavigation(String name, String feedUrl) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		BloggerFeed bloggerFeed = bloggerFeedService.saveBloggerFeed(name, feedUrl);
		if (bloggerFeed.getId() != null) {
			modelAndView.addObject("bloggerFeedConfirmation", true);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/delete/{key}", method=RequestMethod.POST)
	public ModelAndView deleteTopNavigation(@PathVariable ("key") Long key) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		modelAndView.addObject("bloggerFeedConfirmation", bloggerFeedService.deleteBloggerFeed(key));
		
		return modelAndView;
	}
}
