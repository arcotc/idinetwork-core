package uk.co.idinetwork.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.repository.AdminUserRepository;

public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Override
	public Boolean validUser(String email) {
		return adminUserRepository.validUser(email);
	}

	@Override
	public void initialise(String email) {
		adminUserRepository.initialise(email);
	}

}
