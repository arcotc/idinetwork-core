package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.Config;

public interface GeneralRepository {
	boolean addConfig(String key, String value);
	List<Config> findConfig();
}
