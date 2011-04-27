package uk.co.idinetwork.core.repository;

import java.util.List;

import uk.co.idinetwork.core.model.Instructor;

public class InstructorRepositoryImpl implements InstructorRepository {
	@Override
	public Instructor findInstructor(String instructorId) {
		List<Instructor> instructors = Instructor.all().filter("id", instructorId).fetch();
		return instructors.size() > 0 ? instructors.get(0) : null;
	}
	
	@Override
	public Instructor saveInstructor(String forename, String surname, String adiCode) {
		Instructor instructor = new Instructor(forename, surname, adiCode);
		
		instructor.insert();
		
		return instructor;
	}

	@Override
	public Instructor saveInstructor(Instructor instructorToSave) {
		instructorToSave.insert();
		
		return instructorToSave;
	}

	@Override
	public Instructor findInstructorByAdiCode(String adiCode) {
		List<Instructor> instructors = Instructor.all().filter("adiCode", adiCode).fetch();
		return instructors.size() > 0 ? instructors.get(0) : null;
	}
}
