package uk.co.idinetwork.core.model;

import siena.Id;
import siena.Model;
import siena.Query;

public class Config extends Model {
	@Id private Long id;
	String key;
	String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public static Query<Config> all() {
		return all(Config.class);
	}
}
