package uk.co.idinetwork.core.repository;

import uk.co.idinetwork.core.model.SiteUser;

public interface SiteUserRepository {
	public SiteUser findUserByIdentifier(String identifier);
	public SiteUser saveUser(String identifier, String forename, String surname, String email, String photo, String displayName);
}
