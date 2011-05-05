package uk.co.idinetwork.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class AuthorisationServiceImpl implements AuthorisationService {
	private static final String NOT_LOGGED_IN_VIEW = "management/not-authorised";

	@Autowired UserService googleUserService;
	@Autowired AdminUserService adminUserService;
	
	@Override
	public Boolean isAuthorised() {
		User user = googleUserService.getCurrentUser();
		
		if ((user != null) && (adminUserService.validUser(user.getEmail().toLowerCase()))) {
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
