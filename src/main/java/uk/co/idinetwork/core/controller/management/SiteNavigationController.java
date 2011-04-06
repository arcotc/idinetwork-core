package uk.co.idinetwork.core.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.SiteNavigation;
import uk.co.idinetwork.core.service.SiteNavigationService;
import uk.co.idinetwork.core.utils.KeyUtil;

@Controller
public class SiteNavigationController {
	private static final String CONTROLLER_MAPPING = "/management/site-navigation";
	private static final String VIEW = "management/site-navigation";
	private static final String CONFIRMATION_VIEW = "management/site-navigation-confirmation";
	
	@Autowired private SiteNavigationService siteNavigationService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView siteNavigationList() {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		
		modelAndView.addObject("topSiteNavigation", siteNavigationService.findTopNavigation());
		modelAndView.addObject("sideSiteNavigation", siteNavigationService.findSideNavigation());
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/add/{type}", method=RequestMethod.POST)
	public ModelAndView addTopNavigation(@PathVariable ("type") String type, String title) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		SiteNavigation siteNavigation = new SiteNavigation();
		siteNavigation.setKey(KeyUtil.buildKey(title));
		siteNavigation.setTitle(title);
		siteNavigation.setType(type);
		siteNavigationService.saveSiteNavigation(siteNavigation);
		
		if (siteNavigation.getId() != null) {
			modelAndView.addObject("siteNavigationConfirmation", true);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/delete/{type}/{id}", method=RequestMethod.POST)
	public ModelAndView deleteTopNavigation(@PathVariable ("type") String type, @PathVariable ("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		modelAndView.addObject("siteNavigationConfirmation", siteNavigationService.deleteSiteNavigation(type, id));
		
		return modelAndView;
	}
}
