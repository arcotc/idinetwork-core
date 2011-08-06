package uk.co.idinetwork.core.service;

import uk.co.idinetwork.core.model.SiteUser;

public interface SiteUserService {
	public SiteUser findUserByIdentifier(String identifier);
	public SiteUser saveUser(String identifier, String forename, String surname, String email, String photo, String displayName);
	public void setCurrentUser(SiteUser siteUser);
	public SiteUser getCurrentUser();
}
