package uk.co.idinetwork.core.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.SiteNavigation;
import uk.co.idinetwork.core.service.AuthorisationService;
import uk.co.idinetwork.core.service.SiteNavigationService;
import uk.co.idinetwork.core.utils.KeyUtil;

@Controller
public class SiteNavigationController {
	private static final String CONTROLLER_MAPPING = "/management/site-navigation";
	private static final String VIEW = "management/site-navigation";
	private static final String CONFIRMATION_VIEW = "management/site-navigation-confirmation";
	
	@Autowired private SiteNavigationService siteNavigationService;
	@Autowired private AuthorisationService authorisationService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView siteNavigationList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		
		if (authorisationService.isAuthorised()) {
			modelAndView.addObject("topSiteNavigation", siteNavigationService.findTopNavigation());
			modelAndView.addObject("sideSiteNavigation", siteNavigationService.findSideNavigation());
		}
		else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/add/{type}", method=RequestMethod.POST)
	public ModelAndView addNavigation(HttpServletRequest request, @PathVariable ("type") String type, String title, Integer sortOrder) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		if (authorisationService.isAuthorised()) {
			SiteNavigation siteNavigation = new SiteNavigation();
			siteNavigation.setKey(KeyUtil.buildKey(title));
			siteNavigation.setTitle(title);
			siteNavigation.setType(type);
			siteNavigation.setSortOrder(sortOrder);
			siteNavigationService.saveSiteNavigation(siteNavigation);
			
			if (siteNavigation.getId() != null) {
				modelAndView.addObject("siteNavigationConfirmation", true);
			}
		}
		else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/delete/{type}/{id}", method=RequestMethod.POST)
	public ModelAndView deleteTopNavigation(HttpServletRequest request, @PathVariable ("type") String type, @PathVariable ("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		if (authorisationService.isAuthorised()) {
			modelAndView.addObject("siteNavigationConfirmation", siteNavigationService.deleteSiteNavigation(type, id));
		}
		else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
		}
		
		return modelAndView;
	}
}
