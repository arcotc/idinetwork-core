package uk.co.idinetwork.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import uk.co.idinetwork.core.model.Page;
import uk.co.idinetwork.core.service.PageService;
import uk.co.idinetwork.core.service.SiteUserService;

@Controller
public class FirstLevelPageController extends StandardController {
	public static final String CONTROLLER_MAPPING = "/{page}";
	@Autowired private PageService pageService;
	@Autowired private SiteUserService siteUserService;

	@RequestMapping(value=CONTROLLER_MAPPING)
	public ModelAndView firstLevelPage(@PathVariable ("page") String page) {
		ModelAndView modelAndView = new ModelAndView();
		
		loadConfig(modelAndView);
		loadPage(modelAndView, "homeSubPages", "home");
		Page thePage = loadPage(modelAndView, "page", page);
		loadNavigation(modelAndView);
		
		if ((thePage != null) && (!StringUtils.isBlank(thePage.getIncludePath()))) {
			modelAndView.addObject("includePage", thePage.getIncludePath());
		}
	
		Object p = modelAndView.getModelMap().get("page");
		if ((p != null) && (p instanceof Page)) {
			Page loadedPage = (Page)p;
			if (StringUtils.isBlank(loadedPage.getTemplateName())) {
				modelAndView.setViewName("firstLevelPage");
			}
			else {
				modelAndView.setViewName(loadedPage.getTemplateName());
			}
			
			List<Page> subPages = new ArrayList<Page>();
			if (!StringUtils.isBlank(loadedPage.getLinkText())) {
				for (String subPageKey : loadedPage.getLinkText().split(",")) {
					Page subPage = pageService.loadPage(subPageKey);
	
					if (subPage == null) {
						subPage = new Page();
						subPage.setTitle("Page not found");
						subPage.setBody("<p>The requested page could not be found.</p>");
					}
					else {
						if (!StringUtils.isBlank(subPage.getLinkText())) {
							subPage.setLinkTextAsPages(getLinkTextAsPages(subPage.getLinkText()));
						}
					}
					
					subPages.add(subPage);
				}
			}
			
			modelAndView.addObject("subPages", subPages);
		}
		
		modelAndView.addObject("currentPageKey", page);
        modelAndView.addObject("currentUser", siteUserService.getCurrentUser());
		
		return modelAndView;
	}
	
	public List<Page> getLinkTextAsPages(String linkText) {
		List<Page> pages = new ArrayList<Page>();
		if (!StringUtils.isBlank(linkText)) {
			for (String s : linkText.split(",")) {
				Page p = pageService.loadPage(s);
				if (p != null) {
					pages.add(p);
				}
			}
		}
		
		return pages;
	}
}
