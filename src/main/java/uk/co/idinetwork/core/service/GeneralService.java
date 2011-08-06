package uk.co.idinetwork.core.service;

import java.util.List;

import uk.co.idinetwork.core.model.Config;

public interface GeneralService {
	boolean addConfig(String key, String value);
	List<Config> findConfig();
}
