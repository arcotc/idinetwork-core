package uk.co.idinetwork.core.controller.management;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.idinetwork.core.model.MultiPartFile;
import uk.co.idinetwork.core.service.ImageService;

import com.google.appengine.api.datastore.Blob;

@Controller
public class ImageUploadController {
	private static final String CONTROLLER_MAPPING = "/management/image";
	private static final String VIEW = "management/image/images";

	@Autowired private ImageService imageService;
	
	@RequestMapping(value = CONTROLLER_MAPPING, method = RequestMethod.GET)
	public ModelAndView showAllImages() {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		
		modelAndView.addObject("images", imageService.loadAllImages());
		
		return modelAndView;
	}

	@RequestMapping(value = CONTROLLER_MAPPING + "/upload", method = RequestMethod.POST)
	public String postUpload(MultiPartFile upload, HttpServletRequest request) {
	    if (ServletFileUpload.isMultipartContent(request)) {
	        if (upload.getFile().getSize() != 0) {
	            Blob file = new Blob(upload.getFile().getBytes());
	            
	            return "success";
	        }
	    }
	    return "fail";
	}
}
