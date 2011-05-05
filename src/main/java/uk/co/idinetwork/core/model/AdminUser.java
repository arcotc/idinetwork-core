package uk.co.idinetwork.core.model;

import siena.Id;
import siena.Model;
import siena.Query;

public class AdminUser extends Model {
	@Id
	private Long id;
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static Query<AdminUser> all() {
		return all(AdminUser.class);
	}
}
