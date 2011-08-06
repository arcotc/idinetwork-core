package uk.co.idinetwork.core.controller;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.ContactUsResponse;
import uk.co.idinetwork.core.service.ContactUsResponseService;
import uk.co.idinetwork.core.service.SiteUserService;
import uk.co.idinetwork.core.utils.EmailUtil;


@Controller
public class ContactPageController extends StandardController {
	private static final Log log = LogFactory.getLog(ContactPageController.class);
	public static final String CONTROLLER_MAPPING = "/contact/";
	@Autowired private ContactUsResponseService contactUsResponseService;
	@Autowired private SiteUserService siteUserService;
	
	@RequestMapping(value=CONTROLLER_MAPPING + "form", method=RequestMethod.GET)
	public ModelAndView contactForm(String name, String email, String message) {
		ModelAndView modelAndView = new ModelAndView("contact/form");
		
		ContactUsResponse contactUsResponse = contactUsResponseService.findContactUsResponse();
		if (contactUsResponse != null) {
			modelAndView.addObject("fromEmail", contactUsResponse.getFromEmailAddress());
			modelAndView.addObject("toEmail", contactUsResponse.getToEmailAddresses());
		}
		else {
			modelAndView = new ModelAndView("system-error", "errorMsg", "This page has not been configure");
		}
		
		loadConfig(modelAndView);
		loadNavigation(modelAndView);
        modelAndView.addObject("currentUser", siteUserService.getCurrentUser());
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "submit", method=RequestMethod.POST)
	public ModelAndView formSubmission(HttpServletRequest request, String confirmationUrl, String usersEmail, String name, String fromEmail, String toEmail, String userMsg) {
		ModelAndView modelAndView = new ModelAndView(confirmationUrl);
		
		if (log.isDebugEnabled()) log.debug("Contact form submitted");
		
		if ((StringUtils.isBlank(confirmationUrl)) || (StringUtils.isBlank(fromEmail)) || (StringUtils.isBlank(toEmail))) {
			log.error(String.format("Contact form not configured properly, confirmationUrl: %s, fromEmail: %s, toEmail: %s",confirmationUrl,fromEmail,toEmail));
			modelAndView = new ModelAndView("system-error", "errorMsg", "This page has not been configure");

			ContactUsResponse contactUsResponse = contactUsResponseService.findContactUsResponse();
			if (contactUsResponse != null) {
				modelAndView.addObject("fromEmail", contactUsResponse.getFromEmailAddress());
				modelAndView.addObject("toEmail", contactUsResponse.getToEmailAddresses());
			}
			else {
				modelAndView = new ModelAndView("system-error", "errorMsg", "This page has not been configure");
			}
		}
		else if ((StringUtils.isBlank(usersEmail)) || (StringUtils.isBlank(name)) || (StringUtils.isBlank(userMsg))) {
			log.warn(String.format("Contact form was not submitted properly, usersEmail: %s, name: %s, userMsg: %s",usersEmail, name, userMsg));
			modelAndView = new ModelAndView("contact/form", "errorMsg", "You must provide entries for all required fields");

			ContactUsResponse contactUsResponse = contactUsResponseService.findContactUsResponse();
			if (contactUsResponse != null) {
				modelAndView.addObject("fromEmail", contactUsResponse.getFromEmailAddress());
				modelAndView.addObject("toEmail", contactUsResponse.getToEmailAddresses());
				modelAndView.addObject("name", name);
				modelAndView.addObject("userMsg", userMsg);
				modelAndView.addObject("usersEmail", usersEmail);
			}
			else {
				modelAndView = new ModelAndView("system-error", "errorMsg", "This page has not been configure");
			}
		}
		else if (!EmailUtil.isValidEmailAddress(usersEmail)) {
			modelAndView = new ModelAndView("contact/form", "errorMsg", "You must provide a valid email address");

			ContactUsResponse contactUsResponse = contactUsResponseService.findContactUsResponse();
			if (contactUsResponse != null) {
				modelAndView.addObject("fromEmail", contactUsResponse.getFromEmailAddress());
				modelAndView.addObject("toEmail", contactUsResponse.getToEmailAddresses());
				modelAndView.addObject("name", name);
				modelAndView.addObject("userMsg", userMsg);
				modelAndView.addObject("usersEmail", usersEmail);
			}
			else {
				modelAndView = new ModelAndView("system-error", "errorMsg", "This page has not been configure");
			}
		}
		else {
			String challenge = (String) request.getParameter("recaptcha_challenge_field");
			String response = (String) request.getParameter("recaptcha_response_field");
			String remoteAddr = request.getRemoteAddr();
			
			ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
			 
			reCaptcha.setPrivateKey("6Lco2cUSAAAAAO3-55M3iPPOfr3FIe_4n5XYsFBW");
			 
			ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, response);
			
			if (reCaptchaResponse.isValid()) {
				log.debug(String.format("Contact form submitted properly, confirmationUrl: %s, fromEmail: %s, toEmail: %s, name: %s, usersEmail: %s, userMsg: %s",confirmationUrl,fromEmail,toEmail,name,usersEmail,userMsg));
		
				if (!EmailUtil.send(fromEmail, toEmail, name, "Website Contact Request from " + name + " (" + usersEmail + ")", userMsg)) {
					log.error(String.format("An error occurred sending the email",""));
					modelAndView = new ModelAndView("contact/form", "errorMsg", "An error occurred sending your request, please try again later.");
				}
			}
			else {
				ContactUsResponse contactUsResponse = contactUsResponseService.findContactUsResponse();
				if (contactUsResponse != null) {
					modelAndView = new ModelAndView("contact/form", "errorMsg", "Invalid captcha");

					modelAndView.addObject("fromEmail", contactUsResponse.getFromEmailAddress());
					modelAndView.addObject("toEmail", contactUsResponse.getToEmailAddresses());
					modelAndView.addObject("name", name);
					modelAndView.addObject("userMsg", userMsg);
					modelAndView.addObject("usersEmail", usersEmail);
				}
				else {
					modelAndView = new ModelAndView("system-error", "errorMsg", "This page has not been configure");
				}
			}
		}

		log.debug("Finishing off by loading navigation");
		
		loadConfig(modelAndView);
	    loadNavigation(modelAndView);
        modelAndView.addObject("currentUser", siteUserService.getCurrentUser());
        
		return modelAndView;
	}
}
