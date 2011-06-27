package uk.co.idinetwork.core.model;

import siena.Id;
import siena.Model;
import siena.Query;

public class ContactUsResponse extends Model {
	@Id	private Long id;
	private String fromEmailAddress;	// Must be GAE account email address
	private String toEmailAddresses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFromEmailAddress() {
		return fromEmailAddress;
	}
	
	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}

	public String getToEmailAddresses() {
		return toEmailAddresses;
	}

	public void setToEmailAddresses(String toEmailAddresses) {
		this.toEmailAddresses = toEmailAddresses;
	}

	public static Query<ContactUsResponse> all() {
		return all(ContactUsResponse.class);
	}
}
