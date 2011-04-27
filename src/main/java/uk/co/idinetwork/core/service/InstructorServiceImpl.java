package uk.co.idinetwork.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.idinetwork.core.model.Instructor;
import uk.co.idinetwork.core.repository.InstructorRepository;

public class InstructorServiceImpl implements InstructorService {
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Override
	public Instructor findInstructor(String instructorId) {
		return instructorRepository.findInstructor(instructorId);
	}

	@Override
	public Instructor saveInstructor(String forename, String surname, String adiCode) {
		return instructorRepository.saveInstructor(forename, surname, adiCode);
	}

	@Override
	public Instructor saveInstructor(Instructor instructorToSave) {
		return instructorRepository.saveInstructor(instructorToSave);
	}

	@Override
	public Instructor findInstructorByAdiCode(String adiCode) {
		return instructorRepository.findInstructorByAdiCode(adiCode);
	}
}
