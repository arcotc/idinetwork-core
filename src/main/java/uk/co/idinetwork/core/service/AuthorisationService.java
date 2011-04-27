package uk.co.idinetwork.core.service;

public interface AuthorisationService {
	public Boolean isAuthorised();
	public String getLoginUrl(String returnUri);
	public String getView();
	public String getLogoutUrl(String returnUri);
}
