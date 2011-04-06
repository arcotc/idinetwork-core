package uk.co.idinetwork.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.SiteNavigation;
import uk.co.idinetwork.core.repository.SiteNavigationRepository;

public class SiteNavigationServiceImpl implements SiteNavigationService {
	@Autowired private SiteNavigationRepository siteNavigationRepository;
	
	@Override
	public List<SiteNavigation> findTopNavigation() {
		return siteNavigationRepository.findTopNavigation();
	}
	
	@Override
	public List<SiteNavigation> findSideNavigation() {
		return siteNavigationRepository.findSideNavigation();
	}

	@Override
	public SiteNavigation saveSiteNavigation(SiteNavigation siteNavigation) {
		return siteNavigationRepository.saveSiteNavigation(siteNavigation);
	}

	@Override
	public Boolean deleteSiteNavigation(String type, Long id) {
		return siteNavigationRepository.deleteSiteNavigation(type, id);
	}
}
