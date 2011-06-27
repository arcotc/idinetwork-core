package uk.co.idinetwork.core.model;

import siena.Id;
import siena.Index;
import siena.Model;
import siena.Query;

public class SiteNavigation extends Model {
	@Id private Long id;
	private String key;
	private String title;
	private String type;
	@Index("siteNav_sortOrder_index") private Integer sortOrder;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}
	
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public static Query<SiteNavigation> all() {
		return all(SiteNavigation.class);
	}
}
