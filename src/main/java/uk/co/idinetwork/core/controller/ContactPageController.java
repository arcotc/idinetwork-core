package uk.co.idinetwork.core.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.utils.EmailUtil;

@Controller
public class ContactPageController extends StandardController {
	private static final Log log = LogFactory.getLog(ContactPageController.class);
	public static final String CONTROLLER_MAPPING = "/contact/";
	
	@RequestMapping(value=CONTROLLER_MAPPING + "form", method=RequestMethod.GET)
	public ModelAndView contactForm(String name, String email, String message) {
		ModelAndView modelAndView = new ModelAndView("contact/form");
		
		loadNavigation(modelAndView);
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "submit", method=RequestMethod.POST)
	public ModelAndView formSubmission(String confirmationUrl, String name, String emailAddress, String userMsg) {
		ModelAndView modelAndView = new ModelAndView(confirmationUrl);
		
		log.debug(String.format("confirmationUrl: %s, name: %s, emailAddress: %s, userMsg: %s",confirmationUrl,name,emailAddress,userMsg));

		EmailUtil.send(emailAddress, name, "Website Contact Request", userMsg);
		
	    log.debug("Finishing off by loading navigation");
	    loadNavigation(modelAndView);
        
		return modelAndView;
	}
}
