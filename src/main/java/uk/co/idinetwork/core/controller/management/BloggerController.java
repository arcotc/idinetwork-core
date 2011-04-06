package uk.co.idinetwork.core.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.service.BloggerService;

@Controller
public class BloggerController {
	private static final String CONTROLLER_MAPPING = "/management/blogger";
//	private static final String VIEW = "management/blogger";
	private static final String CONFIRMATION_VIEW = "management/blogger-confirmation";
	
	@Autowired private BloggerService bloggerService;

	@RequestMapping(value=CONTROLLER_MAPPING + "/flush-cache", method=RequestMethod.POST)
	public ModelAndView siteNavigationList() {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		bloggerService.flushCache();
		
		modelAndView.addObject("bloggerConfirmation", true);
		
		return modelAndView;
	}
}
