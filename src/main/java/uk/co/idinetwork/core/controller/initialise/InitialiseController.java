package uk.co.idinetwork.core.controller.initialise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.service.AdminUserService;

@Controller
public class InitialiseController {
	private static final String CONTROLLER_MAPPING = "/initialise";
	private static final String CONFIRMATION_VIEW = "initialise/confirmation";

	@Autowired private AdminUserService adminUserService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView initialiseWithRootUser(String rootUserEmail) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		adminUserService.initialise(rootUserEmail);
		
		modelAndView.addObject("initialiseConfirmation", true);
		
		return modelAndView;
	}
}
