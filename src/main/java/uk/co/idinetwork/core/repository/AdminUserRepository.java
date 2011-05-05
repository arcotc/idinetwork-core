package uk.co.idinetwork.core.repository;

public interface AdminUserRepository {
	public Boolean validUser(String email);
	public void initialise(String email);
}
