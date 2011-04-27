package uk.co.idinetwork.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.Instructor;
import uk.co.idinetwork.core.model.JsonResponse;
import uk.co.idinetwork.core.model.json.JsonInstructor;
import uk.co.idinetwork.core.service.InstructorService;

@Controller
public class InstructorJsonController {
	public static final String CONTROLLER_MAPPING_PREFIX = "/json/instructor/";
	
	@Autowired private InstructorService instructorService;
	
	@RequestMapping(value=CONTROLLER_MAPPING_PREFIX + "{adiCode}", method=RequestMethod.GET)
	public ModelAndView findInstructor(@PathVariable("adiCode") String adiCode) {
		Instructor instructor = instructorService.findInstructorByAdiCode(adiCode);
		
		ModelAndView modelAndView = new ModelAndView("jsonView", "response", new JsonResponse<JsonInstructor>(JsonResponse.OK, new JsonInstructor(instructor)));
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING_PREFIX, method=RequestMethod.POST)
	public ModelAndView saveInstructor(String forename, String surname, String adiCode) {
		Instructor instructor = instructorService.saveInstructor(forename, surname, adiCode);
		
		ModelAndView modelAndView = new ModelAndView("jsonView", "response", new JsonResponse<String>(JsonResponse.OK, CONTROLLER_MAPPING_PREFIX + instructor.getAdiCode()));
		
		return modelAndView;
	}
}
