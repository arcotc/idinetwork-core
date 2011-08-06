package uk.co.idinetwork.core.repository;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import uk.co.idinetwork.core.model.Config;

public class GeneralRepositoryImpl implements GeneralRepository {

	@Override
	public List<Config> findConfig() {
		return Config.all().fetch();
	}

	@Override
	public boolean addConfig(String key, String value) {
		List<Config> configs = findConfig();
		if ((configs == null) || (configs.size() == 0)) {
			// First entry, add it
			Config config = new Config();
			config.setKey(key);
			config.setValue(value);
			config.insert();
		}
		else {
			boolean found = false;
			for (Config config : configs) {
				if (config.getKey().equalsIgnoreCase(key)) {
					// Found it
					if (StringUtils.isBlank(value)) {
						// Delete it
						config.delete();
					}
					else {
						// Update it
						config.setValue(value);
						config.update();
					}
					
					found = true;
					break;
				}
			}
			
			if (!found) {
				// Didn't find it, add it
				Config config = new Config();
				config.setKey(key);
				config.setValue(value);
				config.insert();
			}
		}

		return true;
	}
}
