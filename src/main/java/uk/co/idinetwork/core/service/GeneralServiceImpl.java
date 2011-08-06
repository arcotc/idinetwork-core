package uk.co.idinetwork.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.Config;
import uk.co.idinetwork.core.repository.GeneralRepository;

public class GeneralServiceImpl implements GeneralService {
	@Autowired GeneralRepository generalRepository;

	@Override
	public List<Config> findConfig() {
		return generalRepository.findConfig();
	}

	@Override
	public boolean addConfig(String key, String value) {
		return generalRepository.addConfig(key, value);
	}
}
