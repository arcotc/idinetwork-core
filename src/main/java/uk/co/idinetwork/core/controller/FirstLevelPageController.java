package uk.co.idinetwork.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import uk.co.idinetwork.core.model.Page;

@Controller
public class FirstLevelPageController extends StandardController {
	public static final String CONTROLLER_MAPPING = "/{page}";

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView firstLevelPage(@PathVariable ("page") String page) {
		ModelAndView modelAndView = new ModelAndView();
		
		loadPage(modelAndView, page);
		loadNavigation(modelAndView);
		
		Object p = modelAndView.getModelMap().get("page");
		if ((p != null) && (p instanceof Page)) {
			Page loadedPage = (Page)p;
			if (StringUtils.isBlank(loadedPage.getTemplateName())) {
				modelAndView.setViewName("firstLevelPage");
			}
			else {
				modelAndView.setViewName(loadedPage.getTemplateName());
			}
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.POST)
	public ModelAndView postNotAvailable() {
		return new ModelAndView("errors/illegal-operation");
	}
}
