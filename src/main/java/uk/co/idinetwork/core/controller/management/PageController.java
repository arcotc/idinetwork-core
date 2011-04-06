package uk.co.idinetwork.core.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.Page;
import uk.co.idinetwork.core.service.PageService;

@Controller
public class PageController {
	private static final String CONTROLLER_MAPPING = "/management/page";
	private static final String VIEW = "management/page";
	private static final String CONFIRMATION_VIEW = "management/page-confirmation";
	
	@Autowired private PageService pageService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView siteNavigationList() {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		
		modelAndView.addObject("pages", pageService.loadAllPages());
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/add", method=RequestMethod.POST)
	public ModelAndView addTopNavigation(String title, String linkTitle, String body, String metaDescription, String metaKeywords) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		Page page = pageService.savePage(title, linkTitle, body, metaDescription, metaKeywords);		
		if (page.getId() != null) {
			modelAndView.addObject("pageConfirmation", true);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/delete/{id}", method=RequestMethod.POST)
	public ModelAndView deleteTopNavigation(@PathVariable ("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		modelAndView.addObject("pageConfirmation", pageService.deletePage(id));
		
		return modelAndView;
	}
}
