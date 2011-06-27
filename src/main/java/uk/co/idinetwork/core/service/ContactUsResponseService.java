package uk.co.idinetwork.core.service;

import uk.co.idinetwork.core.model.ContactUsResponse;

public interface ContactUsResponseService {
	public boolean saveContactUsResponse(ContactUsResponse contactUsResponse);
	public ContactUsResponse findContactUsResponse();
}
