package uk.co.idinetwork.core.repository;

import uk.co.idinetwork.core.model.Instructor;

public interface InstructorRepository {
	public Instructor findInstructor(String instructorId);
	public Instructor saveInstructor(String forename, String surname, String adiCode);
	public Instructor saveInstructor(Instructor instructorToSave);
	public Instructor findInstructorByAdiCode(String adiCode);
}
