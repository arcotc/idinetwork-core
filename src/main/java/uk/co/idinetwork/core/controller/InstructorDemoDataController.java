package uk.co.idinetwork.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.Coverage;
import uk.co.idinetwork.core.model.CoverageArea;
import uk.co.idinetwork.core.model.Instructor;
import uk.co.idinetwork.core.model.JsonResponse;
import uk.co.idinetwork.core.service.InstructorService;

@Controller
public class InstructorDemoDataController {
	public static final String CONTROLLER_MAPPING_PREFIX = "/instructor/demo/data";
	
	@Autowired private InstructorService instructorService;
	
	@RequestMapping(value=CONTROLLER_MAPPING_PREFIX, method=RequestMethod.GET)
	public ModelAndView setupInstructor() {
		Instructor instructorToSave = new Instructor();
		instructorToSave.setForename("Mark");
		instructorToSave.setSurname("Hunter");
		instructorToSave.setAdiCode("ADI123");
		List<Coverage> coverages = new ArrayList<Coverage>();
		{
			Coverage coverage = new Coverage();
			coverage.setPostcode("LN8");
			List<CoverageArea> coverageAreas = new ArrayList<CoverageArea>();
			{
				CoverageArea coverageArea = new CoverageArea();
				coverageArea.setTownCity("Market Rasen");
				coverageAreas.add(coverageArea);
			}
			{
				CoverageArea coverageArea = new CoverageArea();
				coverageArea.setTownCity("Lincoln");
				coverageAreas.add(coverageArea);
			}
			{
				CoverageArea coverageArea = new CoverageArea();
				coverageArea.setTownCity("Louth");
				coverageAreas.add(coverageArea);
			}
			coverage.setCoverageAreas(coverageAreas);
			coverages.add(coverage);
		}
//		instructorToSave.setCoverages(coverages);
		Instructor instructor = instructorService.saveInstructor(instructorToSave);
		
		ModelAndView modelAndView = new ModelAndView("jsonView", "response", new JsonResponse<String>(JsonResponse.OK, CONTROLLER_MAPPING_PREFIX + instructor.getAdiCode()));
		
		return modelAndView;
	}
}
