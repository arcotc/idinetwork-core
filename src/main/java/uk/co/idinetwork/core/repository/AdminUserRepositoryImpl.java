package uk.co.idinetwork.core.repository;

import uk.co.idinetwork.core.model.AdminUser;

public class AdminUserRepositoryImpl implements AdminUserRepository {
	public Boolean validUser(String email) {
		return AdminUser.all().filter("email", email).get() != null;
	}
	
	public void initialise(String email) {
		if (AdminUser.all().count() == 0) {
			AdminUser adminUser = new AdminUser();
			
			adminUser.setEmail(email);
			
			adminUser.insert();
		}
	}
}
