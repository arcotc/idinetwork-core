package uk.co.idinetwork.core.repository;

import uk.co.idinetwork.core.model.SiteUser;

public class SiteUserRepositoryImpl implements SiteUserRepository {
	@Override
	public SiteUser findUserByIdentifier(String identifier) {
		return SiteUser.all().filter("identifier", identifier).get();
	}

	@Override
	public SiteUser saveUser(String identifier, String forename, String surname, String email, String photo, String displayName) {
		SiteUser siteUser = new SiteUser();
		siteUser.setIdentifier(identifier);
		siteUser.setForename(forename);
		siteUser.setSurname(surname);
		siteUser.setEmail(email);
		siteUser.setPhoto(photo);
		siteUser.setDisplayName(displayName);
		
		siteUser.insert();
		
		return siteUser;
	}
}
