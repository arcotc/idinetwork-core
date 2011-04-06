package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.SiteNavigation;

public class SiteNavigationRepositoryImpl implements SiteNavigationRepository {
	@Override
	public List<SiteNavigation> findTopNavigation() {
		return findNavigation("top");
	}

	@Override
	public List<SiteNavigation> findSideNavigation() {
		return findNavigation("side");
	}
	
	private List<SiteNavigation> findNavigation(String type) {
		List<SiteNavigation> siteNavigation = SiteNavigation.all().filter("type", type).fetch();
		return siteNavigation;
	}
	
	private SiteNavigation findNavigationByKey(String key) {
		return SiteNavigation.all().filter("key", key).get();
	}

	@Override
	public SiteNavigation saveSiteNavigation(SiteNavigation siteNavigation) {
		siteNavigation.insert();
		
		return siteNavigation;
	}

	@Override
	public Boolean deleteSiteNavigation(String type, Long id) {
		SiteNavigation siteNavigation = SiteNavigation.all().filter("id", id).filter("type", type).get();
		
		if (siteNavigation != null) {
			siteNavigation.delete();
			return true;
		}
		else {
			return false;
		}
	}

}
