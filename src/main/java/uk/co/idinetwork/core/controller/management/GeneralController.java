package uk.co.idinetwork.core.controller.management;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.Config;
import uk.co.idinetwork.core.service.GeneralService;

@Controller
public class GeneralController {
	private static final String CONTROLLER_MAPPING = "/management/general";
	private static final String VIEW = "management/general/config";
	private static final String CONFIRMATION_VIEW = "management/general/config-confirmation";
	
	@Autowired private GeneralService generalService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView findConfig(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		
		List<Config> configs = generalService.findConfig();
		if (configs != null) {
			modelAndView.addObject("config", configs);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/add", method=RequestMethod.POST)
	public ModelAndView saveConfig(HttpServletRequest request, String key, String value) {
		ModelAndView modelAndView = new ModelAndView(CONFIRMATION_VIEW);
		
		if (generalService.addConfig(key, value)) {
			List<Config> configs = generalService.findConfig();
			modelAndView.addObject("config", configs);
			modelAndView.addObject("generalConfirmation", true);
		}
		
		return modelAndView;
	}
}
