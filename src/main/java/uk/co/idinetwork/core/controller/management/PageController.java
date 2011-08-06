package uk.co.idinetwork.core.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.Page;
import uk.co.idinetwork.core.service.AuthorisationService;
import uk.co.idinetwork.core.service.PageService;

@Controller
public class PageController {
	private static final String CONTROLLER_MAPPING = "/management/pages";
	private static final String VIEW = "management/page/pages";
	private static final String EDIT = "management/page/edit";
	private static final String CONFIRMATION_VIEW = "management/page/page-confirmation";
	
	@Autowired private PageService pageService;
	@Autowired private AuthorisationService authorisationService;

	@RequestMapping(value=CONTROLLER_MAPPING, method=RequestMethod.GET)
	public ModelAndView pageList(HttpServletRequest request) {
		ModelAndView modelAndView = null;
		
		if (authorisationService.isAuthorised()) {
			modelAndView = new ModelAndView(VIEW);
			modelAndView.addObject("pages", pageService.loadAllPages());
		}
		else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/add", method=RequestMethod.POST)
	public ModelAndView addPage(HttpServletRequest request, String title, String linkTitle, String linkText, String intro, String body, String metaDescription, String metaKeywords, String tags, String templateName, String includePath) {
		ModelAndView modelAndView = null;
		
		if (authorisationService.isAuthorised()) {
			modelAndView = new ModelAndView(CONFIRMATION_VIEW);
			
			Page page = pageService.savePage(title, linkTitle, linkText, intro, body, metaDescription, metaKeywords, tags, templateName, includePath);
			if (page.getId() != null) {
				modelAndView.addObject("pageConfirmation", true);
			}
		}
		else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/delete/{id}", method=RequestMethod.POST)
	public ModelAndView deletePage(HttpServletRequest request, @PathVariable ("id") Long id) {
		ModelAndView modelAndView = null;
		
		if (authorisationService.isAuthorised()) {
			modelAndView = new ModelAndView(CONFIRMATION_VIEW);
			
			modelAndView.addObject("pageConfirmation", pageService.deletePage(id));
		}
		else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editPage(HttpServletRequest request, @PathVariable ("id") Long id) {
		ModelAndView modelAndView = null;
		
		if (authorisationService.isAuthorised()) {
			modelAndView = new ModelAndView(EDIT);
			
			modelAndView.addObject("page", pageService.loadPage(id));
		}
		else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value=CONTROLLER_MAPPING + "/edit", method=RequestMethod.POST)
	public ModelAndView editPageSave(HttpServletRequest request, Long id, String title, String linkTitle, String linkText, String intro, String body, String metaDescription, String metaKeywords, String tags, String templateName, String includePath) {
		ModelAndView modelAndView = null;
		
		if (authorisationService.isAuthorised()) {
			Page page = pageService.updatePage(id, title, linkTitle, linkText, intro, body, metaDescription, metaKeywords, tags, templateName,includePath);
			if (page != null) {
				modelAndView = new ModelAndView(CONFIRMATION_VIEW);
				modelAndView.addObject("pageConfirmation", true);
			}
			else {
				modelAndView = new ModelAndView(EDIT);
				modelAndView.addObject("page", pageService.loadPage(id));
			}
		}
		else {
        	modelAndView = new ModelAndView(authorisationService.getView());
        	modelAndView.addObject("redirectUrl", authorisationService.getLoginUrl(request.getRequestURI()));
		}
		
		return modelAndView;
	}
}
