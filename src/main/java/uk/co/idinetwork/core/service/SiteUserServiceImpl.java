package uk.co.idinetwork.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.SiteUser;
import uk.co.idinetwork.core.repository.SiteUserRepository;

public class SiteUserServiceImpl implements SiteUserService {
	@Autowired private SiteUserRepository siteUserRepository;
	private SiteUser currentUser;

	@Override
	public SiteUser findUserByIdentifier(String identifier) {
		return siteUserRepository.findUserByIdentifier(identifier);
	}

	@Override
	public SiteUser saveUser(String identifier, String forename, String surname, String email, String photo, String displayName) {
		return siteUserRepository.saveUser(identifier, forename, surname, email, photo, displayName);
	}

	@Override
	public void setCurrentUser(SiteUser siteUser) {
		this.currentUser = siteUser;
	}

	@Override
	public SiteUser getCurrentUser() {
		return currentUser;
	}
}
