package uk.co.idinetwork.core.repository;

import uk.co.idinetwork.core.model.ContactUsResponse;

public interface ContactUsResponseRepository {
	boolean saveContactUsResponse(ContactUsResponse contactUsResponse);
	ContactUsResponse findContactUsResponse();
}
