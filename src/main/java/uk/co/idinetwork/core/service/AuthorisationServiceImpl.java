package uk.co.idinetwork.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class AuthorisationServiceImpl implements AuthorisationService {
	private static final String NOT_LOGGED_IN_VIEW = "management/not-authorised";

	@Autowired UserService googleUserService;
	
	@Override
	public Boolean isAuthorised() {
		User user = googleUserService.getCurrentUser();
		
		// TODO: Make this read from a local project file
		if ((user != null) && ((user.getEmail().toLowerCase().equals("arcotc@googlemail.com")) || 
							   (user.getEmail().toLowerCase().equals("jssom@hotmail.co.uk")))) {
			return true;
		}
		
		return false;
	}

	@Override
	public String getLoginUrl(String returnUri) {
		return googleUserService.createLoginURL(returnUri);
	}
	
	@Override
	public String getView() {
		return NOT_LOGGED_IN_VIEW;
	}
	
	@Override
	public String getLogoutUrl(String returnUri) {
		return googleUserService.createLogoutURL(returnUri);
	}
}
