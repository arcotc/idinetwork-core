package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.SiteNavigation;

public interface SiteNavigationRepository {
	public List<SiteNavigation> findTopNavigation();
	public List<SiteNavigation> findSideNavigation();
	public SiteNavigation saveSiteNavigation(SiteNavigation siteNavigation);
	public Boolean deleteSiteNavigation(String type, Long id);
}
