package uk.co.idinetwork.core.controller.management;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.ContactUsResponse;
import uk.co.idinetwork.core.service.ContactUsResponseService;

@Controller
public class ContactUsResponseController {
	private static final String CONTROLLER_MAPPING = "/management/contact-us";
	private static final String VIEW = "management/contact-us/contact-us";
	private static final String CONFIRMATION_VIEW = "management/contact-us/contact-us-confirmation";
	
	@Autowired private ContactUsResponseService contactUsResponseService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView contactUsResponse(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		
		ContactUsResponse contactUsResponse = contactUsResponseService.findContactUsResponse();
		if (contactUsResponse != null) {
			modelAndView.addObject("fromEmailAddress", contactUsResponse.getFromEmailAddress());
			modelAndView.addObject("toEmailAddresses", contactUsResponse.getToEmailAddresses());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/add", method=RequestMethod.POST)
	public ModelAndView saveContactUsResponse(HttpServletRequest request, String fromEmailAddress, String toEmailAddresses) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		// Validate email addresses
		boolean allValid = true;
		for (String address : toEmailAddresses.split(";")) {
			try {
				new InternetAddress(address);
			} 
			catch (AddressException e) {
				allValid = false;
				break;
			}
		}
		
		try {
			new InternetAddress(fromEmailAddress);
		}
		catch (AddressException e) {
			allValid = false;
		}
		
		if (allValid) {
			ContactUsResponse contactUsResponse = new ContactUsResponse();
			contactUsResponse.setFromEmailAddress(fromEmailAddress);
			contactUsResponse.setToEmailAddresses(toEmailAddresses);
			contactUsResponseService.saveContactUsResponse(contactUsResponse);
				
			if (contactUsResponse.getId() != null) {
				modelAndView.addObject("fromEmailAddress", contactUsResponse.getFromEmailAddress());
				modelAndView.addObject("toEmailAddresses", contactUsResponse.getToEmailAddresses());
				modelAndView.addObject("contactUsResponseConfirmation", true);
			}
		}
		else {
			modelAndView.addObject("contactUsResponseConfirmation", false);
			modelAndView.addObject("errorMsg", "At least one invalid email address provided");
		}
		
		return modelAndView;
	}
}
