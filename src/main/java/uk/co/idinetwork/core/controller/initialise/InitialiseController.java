package uk.co.idinetwork.core.controller.initialise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.SiteNavigation;
import uk.co.idinetwork.core.service.AdminUserService;
import uk.co.idinetwork.core.service.PageService;
import uk.co.idinetwork.core.service.SiteNavigationService;

@Controller
public class InitialiseController {
	private static final String CONTROLLER_MAPPING = "/initialise";
	private static final String CONFIRMATION_VIEW = "initialise/confirmation";

	@Autowired private AdminUserService adminUserService;
	@Autowired private SiteNavigationService siteNavigationService;
	@Autowired private PageService pageService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView initialiseWithRootUser(String rootUserEmail) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		adminUserService.initialise(rootUserEmail);

		{
			SiteNavigation siteNavigation = new SiteNavigation();
			siteNavigation.setKey("home");
			siteNavigation.setTitle("Home");
			siteNavigation.setType("top");
			siteNavigation.setSortOrder(1);
			siteNavigationService.saveSiteNavigation(siteNavigation);
		}
		
		{
			SiteNavigation siteNavigation = new SiteNavigation();
			siteNavigation.setKey("contact/form");
			siteNavigation.setTitle("Contact");
			siteNavigation.setType("top");
			siteNavigation.setSortOrder(999);
			siteNavigationService.saveSiteNavigation(siteNavigation);
		}

		pageService.savePage("Welcome", "home", "home", "", "<p>Welcome to your new IDINetwork web site.</p>", "", "", "", "", "");
		
		modelAndView.addObject("initialiseConfirmation", true);
		
		return modelAndView;
	}
}
