package uk.co.idinetwork.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.ContactUsResponse;
import uk.co.idinetwork.core.repository.ContactUsResponseRepository;

public class ContactUsResponseServiceImpl implements ContactUsResponseService {
	@Autowired private ContactUsResponseRepository contactUsReponseRepository;

	@Override
	public boolean saveContactUsResponse(ContactUsResponse contactUsResponse) {
		return contactUsReponseRepository.saveContactUsResponse(contactUsResponse);
	}

	@Override
	public ContactUsResponse findContactUsResponse() {
		return contactUsReponseRepository.findContactUsResponse();
	}

}
