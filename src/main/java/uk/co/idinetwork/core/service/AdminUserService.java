package uk.co.idinetwork.core.service;

public interface AdminUserService {
	public Boolean validUser(String email);
	public void initialise(String email);
}
