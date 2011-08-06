package uk.co.idinetwork.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.SiteUser;
import uk.co.idinetwork.core.service.SiteUserService;

import com.googlecode.janrain4j.api.engage.EngageFailureException;
import com.googlecode.janrain4j.api.engage.EngageService;
import com.googlecode.janrain4j.api.engage.EngageServiceFactory;
import com.googlecode.janrain4j.api.engage.ErrorResponeException;
import com.googlecode.janrain4j.api.engage.response.UserDataResponse;
import com.googlecode.janrain4j.api.engage.response.profile.Profile;

@Controller
public class LoginController {
	private static final Log log = LogFactory.getLog(LoginController.class);
	public static final String CONTROLLER_MAPPING = "/login/response";
	@Autowired private SiteUserService siteUserService;
	private SiteUser siteUser;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.POST)
	public ModelAndView contactForm(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("login/success");
		
		// Retrieve the token sent by Janrain 
        String token = request.getParameter("token");
        
        // Get the EngageService
        EngageService engageService = EngageServiceFactory.getEngageService();

        try {
            // Retrieve the Janrain user data
            UserDataResponse userDataResponse = engageService.authInfo(token);
            Profile profile = userDataResponse.getProfile();

            siteUser = siteUserService.findUserByIdentifier(profile.getIdentifier());
            if (siteUser == null) {
            	siteUser = siteUserService.saveUser(profile.getIdentifier(), profile.getName().getGivenName(), profile.getName().getFamilyName(), profile.getEmail(), profile.getPhoto(), profile.getDisplayName());
            }
            
            siteUserService.setCurrentUser(siteUser);
            modelAndView.addObject("currentUser", siteUserService.getCurrentUser());
        }
        catch (EngageFailureException e) {
        	log.error("EngageFailureException Failure during login", e);
        	modelAndView = new ModelAndView("login/failure");
        }
        catch (ErrorResponeException e) {
        	log.error("ErrorResponeException Failure during login", e);
        	modelAndView = new ModelAndView("login/failure");
        }
		
		return modelAndView;
	}

}
