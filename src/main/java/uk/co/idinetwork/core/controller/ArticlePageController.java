package uk.co.idinetwork.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.service.SiteUserService;

@Controller
public class ArticlePageController extends StandardController {
	public static final String CONTROLLER_MAPPING = "/article/{articleKey}";
	@Autowired private SiteUserService siteUserService;
	
	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView firstLevelPage(@PathVariable ("articleKey") String articleKey) {
		ModelAndView modelAndView = new ModelAndView("articlePage");

		loadConfig(modelAndView);
		loadArticle(modelAndView, articleKey);
		loadNavigation(modelAndView);
        modelAndView.addObject("currentUser", siteUserService.getCurrentUser());
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.POST)
	public ModelAndView postNotAvailable() {
		return new ModelAndView("errors/illegal-operation");
	}
}
