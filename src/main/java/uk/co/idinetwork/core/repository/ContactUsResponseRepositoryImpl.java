package uk.co.idinetwork.core.repository;

import uk.co.idinetwork.core.model.ContactUsResponse;

public class ContactUsResponseRepositoryImpl implements ContactUsResponseRepository {

	@Override
	public boolean saveContactUsResponse(ContactUsResponse contactUsResponse) {
		ContactUsResponse loaded = findContactUsResponse();
		if (findContactUsResponse() == null) {
			contactUsResponse.insert();
		}
		else {
			loaded.setToEmailAddresses(contactUsResponse.getToEmailAddresses());
			loaded.update();
		}
		
		return true;
	}

	@Override
	public ContactUsResponse findContactUsResponse() {
		return ContactUsResponse.all().get();
	}
}
