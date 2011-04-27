package uk.co.idinetwork.core.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.service.AuthorisationService;

@Controller
public class Management {
	private static final String CONTROLLER_MAPPING = "/management";
	private static final String VIEW = "management/menu";
	
	@Autowired AuthorisationService authorisationService;
	
	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView menu(HttpServletRequest request) {
		ModelAndView modelAndView = null;
		
		if (authorisationService.isAuthorised()) {
        	modelAndView = new ModelAndView(VIEW);
        }
        else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
        }
		
		return modelAndView;
	}
}
